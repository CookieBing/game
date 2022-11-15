# 蛇梯游戏

### 项目说明

- 配置项`spring.open-snake-ladder`表示是否开启游戏道具蛇吻与梯子，`true`表示开启，`false`表示关闭。
- 游戏流程：首先调用开始游戏，初始化游戏数据，然后通过不同角色调用掷骰子，直到有一方游戏胜利；也可以调用开始游戏重新开始一局游戏。

#### 参考文档

[蛇梯游戏-在线API](https://www.apifox.cn/apidoc/shared-05a56e54-fc4a-4eb1-bdbc-be451a5c7e71)
> 访问密码 : 1xhznwUb

#### 项目结构

```
.
├── src
│   ├── main  
│     ├── java.com.bing.game                  主要业务程序包
│       ├── constant                          静态文件包
│       ├── controller                        控制器文件包
│       ├── entity                            实体类文件包
│       ├── service                           服务包，核心业务逻辑
│       ├── SnakeLadderGameApplication.java   项目启动的核心文件
│     ├── resource                            资源与配置文件包
│       ├── application.yml                   项目配置文件
│       ├── logback-default.xml               日志配置文件
│   ├── test                                  测试包
├── pom.xml                                   项目依赖
└── README.md                                 说明文档
```

- 注：当前项目只支持单机，后续可将对局添加唯一标识(如：uuid)，可将不同对局的数据存放到不同对局的标识中，并使用`redis`来减小内存压力，来使游戏可以同时多人游玩。
