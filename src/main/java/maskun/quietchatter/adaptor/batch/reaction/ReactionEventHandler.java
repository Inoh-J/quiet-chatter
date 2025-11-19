package maskun.quietchatter.adaptor.batch.reaction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import maskun.quietchatter.hexagon.application.value.ReactionTarget;
import maskun.quietchatter.hexagon.outbound.ReactionEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReactionEventHandler implements ReactionEventPublisher {
    private final ReactionBatchWorker batchWorker;
    private final BlockingQueue<ReactionEvent> eventQueue;
    private volatile boolean shuttingDown;
    private final Thread eventConsumer;

    public ReactionEventHandler(ReactionBatchWorker batchWorker,
                                @Value("${app.reaction.batch-size:100}") int batchSize) {
        if (batchSize < 1) {
            throw new IllegalStateException("배치사이즈는 1보다 작을 수 없습니다");
        }

        this.batchWorker = batchWorker;
        this.eventQueue = new LinkedBlockingQueue<>();
        this.shuttingDown = false;
        this.eventConsumer = new Thread(() -> this.consume(batchSize), "reaction-event-consumer");
    }

    @Override
    public void add(ReactionTarget target) {
        ReactionEvent event = getEvent(target, Action.INSERT);
        offer(event);
    }

    @Override
    public void remove(ReactionTarget target) {
        ReactionEvent event = getEvent(target, Action.DELETE);
        offer(event);
    }

    private void offer(ReactionEvent event) {
        if (shuttingDown) {
            log.warn("애플리케이션 종료 중에는 리액션을 추가할 수 없습니다. 무시합니다= {}", event);
            return;
        }

        if (!eventQueue.offer(event)) {
            throw new IllegalStateException("큐가 꽉 찼습니다");
        }
    }

    private static ReactionEvent getEvent(ReactionTarget target, Action action) {
        return new ReactionEvent(target.talkId(), target.memberId(), target.type(), action);
    }

    @PostConstruct
    protected void beginConsume() {
        eventConsumer.start();
    }

    @PreDestroy
    protected void endConsume() {
        shuttingDown = true;
        eventConsumer.interrupt();
    }

    private void consume(int batchSize) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    processBatch(batchSize);
                } catch (InterruptedException e) {
                    log.info("{} 스레드가 인터럽트 신호를 받아 종료를 시작합니다.", Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    log.error("{} 스레드가 배치 처리 중 예상치 못한 오류 발생. 스레드를 유지합니다.",
                            Thread.currentThread().getName(), e);
                }
            }
        } finally {
            processRemainingEvents();
            log.info("{} 스레드가 남은 작업을 끝내고 종료합니다.", Thread.currentThread().getName());
        }
    }

    private void processBatch(int batchSize) throws InterruptedException {
        ReactionEvent headEvent = eventQueue.take();
        List<ReactionEvent> batch = new ArrayList<>(List.of(headEvent));
        eventQueue.drainTo(batch, batchSize - 1); //total 100
        executeBatch(batch);
    }

    private void processRemainingEvents() {
        List<ReactionEvent> remains = new ArrayList<>();
        eventQueue.drainTo(remains);
        executeBatch(remains);
    }

    private void executeBatch(List<ReactionEvent> batch) {
        ReactionRequestAggregator aggregator = new ReactionRequestAggregator(batch);
        batchWorker.process(aggregator);
    }
}
