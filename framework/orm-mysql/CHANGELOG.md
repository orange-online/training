#4.4.0 (2021-04-19)
Features
The IdGenerator is replaced with the snowflake algorithm.
Delete 'IdStringEntity' file.

#4.3.0 (2021-04-14)
Features
Upgrade the 'hibernateVersion' to '5.4.17.Final'.
Upgrade the 'ehcache' to '3.9.2'.

#4.2.0 (2021-04-14)
Features
Upgrade the 'springBootVersion' to '2.3.1.RELEASE'.

#4.1.0 (2020-09-07)
Features
Rename the `IdEntity` to `IdStringEntity`.
Add a new Entity named `IdLongEntity`.

#4.0.0 (2020-07-14)
Features
EntityBase基类属性命名全部重构
@EntityListeners(AuditingEntityListener.class)配置上升到EntityBase

#3.0.0 (2020-06-09)
Features
IdEntity中id更名为entityId，并修改相应代码

#2.0.0 (2020-06-09)
Features
升级utils组件版本至2.0.0
调整包层级

#1.0.2 (2020-05-26)
Features
DatasourceConfig中DataSource Bean增加@Primary注解，以兼容第三方组件自定义数据源

#1.0.1 (2020-05-25)
Features
DatasourceConfig中移除可选配置，在新组件的语境下，可选配置意义已经不大

#1.0.3 -> 1.0.0 (2020-05-25)
Features
项目更名为deepdraw-training-framework-orm-mysql，新组件版本从1.0.0开始

#1.0.3 (2020-05-21)
Features
加入DataSource路由设计，为以后扩展预留位置

#1.0.2 (2020-05-21)
Features
DataSourceYamlFileIntializer更名为DatasourceConfigContextIntializer

#1.0.1 (2020-05-21)
Features
id生成策略配置错误修正

#1.0.0 (2020-05-20)
Features
初始化项目代码
