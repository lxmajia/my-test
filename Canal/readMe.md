cancel配置时，canal.ip不能配置，不然连接被拒绝

监听某个库：mjxy\\..*

binlog文件名和位置也别配置，避免找不到之前的报错
canal.instance.master.journal.name=
canal.instance.master.position=