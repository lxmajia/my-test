## Seq发号表
CREATE TABLE `seq_order_id` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `id_unique` char(1) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idUnique` (`id_unique`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

update seq_order_id set id = 10000000 where id_unique = 'u';