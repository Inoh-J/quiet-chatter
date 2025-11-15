# 시스템 아키텍처

## 인프라스트럭처 아키텍쳐

```mermaid
    C4Context
    title Quiet Chatter : 인프라스트럭처 아키텍쳐
    Person(user, "사용자")

    Boundary(ststem, "클라우드 서버", "AWS Light Sail") {
        Container(ws, "웹서버", "Nginx")
        Container(db, "데이터 베이스", "PostgreSQL")
        Container(app, "어플리케이션 서버", "Spring boot")
        Container(wt, "배포 자동화 툴", "Watchtower")
    }

    Boundary(git, "VCS", "GitHub") {
        Component(gitAction, "CI/CD", "Git Hub Action")
        ComponentDb(repository, "프로젝트 저장소", "Git Hub Repository")
        Person(developer, "개발자")
    }
    System(dockerhub, "이미지 저장소", "Docker Hub")
    BiRel(user, ws, "")
    BiRel(ws, app, "")
    Rel(dockerhub, wt, "이미지 Pull")
    Rel(wt, app, "배포")
    Rel(developer, repository, "Push to")
    Rel(gitAction, dockerhub, "Docker 이미지 푸시")
    Rel(repository, gitAction, "Run Git Hub Action")
    BiRel(app, db, "")

```
## 애플리케이션 아키텍쳐 (Hexagonal Architecture)

```mermaid
---
config:
  class:
    hideEmptyMembersBox: true
---
classDiagram
    direction LR
    namespace hexagon {
        class domain {
        }
        class `application service`{
        }
        class `inbound port` {
            <<interface>>
        }
        class `outbound port` {
            <<interface>>
        }
    }
    class `web adaptor` {
    }
    class `infra adaptor` {
    }

    `web adaptor` ..> `inbound port`
    `inbound port` <|.. `application service`
    `application service` ..> domain
    `outbound port` <.. `application service`
    `infra adaptor` ..|> `outbound port`

```
