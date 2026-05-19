# 数据类型

字符串 set get
h哈希 set get del vals keys
l链表 push range pop len
s集合 add card inter union rem
z有序集合(关联一个double类型的分数) add range incrby rem
通用命令 keys *  exits type del

# Redis命令

## 通用命令

查看符合模板的所有key

```
keys *
keys a*
```

删除指定的key

```
del key
del key1 key2 ...  返回删除key的数量
```

判断key是否存在

```
exists key 返回1或者0
```

给一个key设置有效期，到期自动删除

```
expire key [seconds]不加代表永久有效
```

查看key的剩余有效期

```
ttl key
```

## String类型命令

```
set
```

```
get
```

```
mset key value 批量添加多个key value
```

```
mget 批量得到多个key的value
```

```
incr key 让key自增1
```

```
incrby key 自增步长
```

```
incrbyfloat key 步长 浮点数自增
```

```
setnx key value  存在就不修改，不存在才修改
```

```
setex key 有效期 value  set和expire的组合命令
```



key的格式

```
项目名：业务名：类型：id
```

## Hash类型命令

它的值是一个无序字典

```
hset key filed value
```

```
hget key filed
```

```
hmset key filed value key filed value ... 批量添加
```

```
hmget key filed filed 批量得到key的多个filed值
```

```
hgetall key 返回所有的键值对
```

```
hkeys key 返回所有key
```

```
hvals value 返回所有value
```

```
hincrby key filed 自增的值
```

```
hsetnx key filed value filed存在不修改，不存在才能修改
```

## List类型命令

双向链表结构(保存一些对顺序有要求的数据)

```
lpush key element  向左侧插入一个或多个元素  返回元素的个数
```

```
lpop key 移除并返回左侧的第一个元素，没有则返回null
```

```
rpush ..
rpop ...
```

```
lrange key start end 返回【start,end】范围内的所有元素
```

```
blpop key 等待时间  等不到就结束
```

## Set类型命令

```
sadd key member 添加一个或多个元素
```

```
srem key member 移除set中的指定元素
```

```
scard key 返回set中元素的个数
```

```
sismember key member 判断一个元素是否存在于set中
```

```
smembers key 获取set中的所有元素
```

```
sinter key1 key2 求key1与key2的交集
```

```
sdiff key1 key2 求key1与key2的差集
```

```
sunion key1 key2 求key1与key2的并集
```

## SortedSet类型命令

可排序的set集合

![image-20260517222651060](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20260517222651060.png)

# SpringDataRedis入门

![image-20260519101843291](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20260519101843291.png)



