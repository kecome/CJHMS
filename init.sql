drop table if exists homework;
create table `homework` (
    `id` bigint unsigned not null comment 'id号',
    `teacherId` bigint unsigned not null comment '教师id号',
    `subjectId` bigint unsigned not null comment '所属科目id号',
    `title` varchar(255) not null comment '作业名称',
    `knowledge` varchar(255) comment '相关知识点',
    `spentTime` int comment '预估完成时间,单位/分钟',
    `status` tinyint not null default 0 comment '作业状态：【0：未发布；1：已发布】',
    `publicTime` datetime comment '预约发布时间',
    `endTime` datetime comment '截止提交时间',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '作业表';

drop table if exists homework_class;
create table `homework_class` (
    `id` bigint unsigned not null comment 'id号',
    `homeworkId` bigint unsigned not null comment '作业id',
    `classId` bigint unsigned not null comment '所属班级id号',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
    primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '作业班级关联表';

drop table if exists question;
create table `question` (
    `id` bigint unsigned not null comment 'id号',
    `homeworkId` bigint unsigned not null comment '作业id',
    `type` tinyint not null comment '题型：【0：选择题；1：主观题】',
    `title` text not null comment '题干',
    `seq` int comment '题目序号，作业展示时用于排序',
    `answer` text not null comment '题目答案',
    `item` varchar(128) not null comment '答案选项',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '题目表';

drop table if exists studentwork;
create table `studentwork` (
    `id` bigint unsigned not null comment 'id号',
    `homeworkId` bigint unsigned not null comment '作业id',
    `studentId` bigint unsigned not null comment '学生id号',
    `submit` tinyint not null default 0 comment '学生是否提交：【0：未提交；1：已提交】',
    `mark` tinyint not null default 0 comment '老师是否批阅：【0：未批阅；1：已批阅】',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '学生作业表';

drop table if exists studentanswer;
create table `studentAnswer` (
    `id` bigint unsigned not null comment 'id号',
    `questionId` bigint unsigned not null comment '所属题目id号',
    `studentId` bigint unsigned not null comment '学生id号',
    `answer` varchar(256) not null comment '题目答案,主观题为照片url',
    `isRight` tinyint not null comment '正确与否：【0：正确；1：错误】',
    `comment` text comment '老师批阅',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '学生答题表';

drop table if exists message;
create table `message` (
    `id` bigint unsigned not null comment 'id号',
    `studentId` bigint unsigned not null comment '消息接收者id号，学生',
    `teacherId` bigint unsigned not null comment '消息发送者id号，老师',
    `type` tinyint not null comment '消息类型：【0：作业批阅消息】',
    `content` text comment '消息内容',
    `isRead` tinyint not null default 0 comment '是否查看：【0：未查看；1：已查看】',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '消息表';

drop table if exists attachment;
create table `attachment` (
    `id` bigint unsigned not null comment 'id号',
    `fileName` varchar(256) not null comment '文件名称',
    `extension` varchar(32) not null comment '文件后缀',
    `url` varchar(256) not null comment '相对路径',
    `size` bigint not null comment '文件大小',
    `md5` varchar(128) not null comment '文件md5值',
    `created` datetime not null comment '创建时间',
    `updated` datetime not null comment '修改时间',
     primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '附件表';