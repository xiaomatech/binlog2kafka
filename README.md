## 消息的类型
canal的binlog 会被解析成以下3中类型的消息。其他的类型被过滤掉了。
### insert

```
{
    "data": {
        "need_sub": {
            "type": "int(11)",
            "updated": true,
            "value": "0"
        },
        "order_description": {
            "type": "varchar(1024)",
            "updated": true,
            "value": ""
        },
        "pay_amount": {
            "type": "int(11)",
            "updated": true,
            "value": "0"
        },
        "pay_order": {
            "type": "varchar(30)",
            "updated": true,
            "value": ""
        }
    },
    "type": "insert"
}
```

### delete
```
{
    "data": {
        "need_sub": {
            "type": "int(11)",
            "updated": true,
            "value": "0"
        },
        "order_description": {
            "type": "varchar(1024)",
            "updated": true,
            "value": ""
        },
        "pay_amount": {
            "type": "int(11)",
            "updated": true,
            "value": "0"
        },
        "pay_order": {
            "type": "varchar(30)",
            "updated": true,
            "value": ""
        }
    },
    "type": "delete"
}
```
### update
data对象是各字段类型、是否被更新、值。olddata对象是之前的状态。

```
{
    "data": {
        "Quota": {
            "type": "tinyint(4)",
            "updated": false,
            "value": "0"
        },
        "ReqAmount": {
            "type": "int(11)",
            "updated": true,
            "value": "100"
        }
    },
    "olddata": {
        "Quota": {
            "type": "tinyint(4)",
            "updated": false,
            "value": "0"
        },
        "ReqAmount": {
            "type": "int(11)",
            "updated": false,
            "value": "0"
        }
    },
    "type": "update"
}
```