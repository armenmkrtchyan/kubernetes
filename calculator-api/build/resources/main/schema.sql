CREATE TABLE `project` (
  `id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `time_created_ns` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `name`            VARCHAR(1000)                DEFAULT NULL,
  PRIMARY KEY (`id`, `time_created_ns`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
