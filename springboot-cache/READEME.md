# springboot-cache

@[TOC](目录)

## 一、简介

**缓存介绍**

> **缓存**，在我们的日常开发中用的非常多，是我们应对各种性能问题支持高并发的一大利器。
>
> * Spring 从 `3.1` 开始就引入了缓存的支持。定义了如下两个接口来统一支持不同的缓存技术。
>   * `org.springframework.cache.Cache`
>   * `org.springframework.cache.CacheManager`
>
> * 我们熟知的缓存有：堆缓存（`Ehcache3.x`、`Guava Cache`、`Caffeine`等）、堆外缓存（`Ehcache3.x`、`MapDB`等）、分布式缓存（`Redis`、`Memcached`等）等等。
> * 常用的缓存注解：`@EnableCaching`、`@Cacheable`、`@CachePut`、`@CacheEvict`、

**Cache 和 CacheManager 接口说明**

* Cache 接口包含缓存的各种操作集合，你操作缓存就是通过这个接口来操作的。
* Cache 接口下 Spring 提供了各种 xxxCache 的实现，比如：RedisCache、EhCache、ConcurrentMapCache等。
* CacheManager 定义了创建、配置、获取、管理和控制多个唯一命名的 Cache。这些 Cache 存在于 CacheManager 的上下文中。
---
## 二、缓存实战

### 1.开启缓存

在 SpringBoot 的启动类上添加注解`@EnableCaching`。


### 2.@Cacheable

> `@Cacheable` 的作用 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存。

**常用属性：**

* `cacheNames`、`value`：用来指定缓存组件名称。

  ```java
  @Cacheable(cacheNames = "users", key="#id")
  public User getUser(Integer id) {}
  ```

* `key`：缓存数据的 key，可以用它来指定。默认使用所有参数的值进行组合。（key可以使用 spEL 表达式来编写）。

  ```java
  @Cacheable(cacheNames = "usersBySpEL", key="#root.methodName + '[' + #id + ']'")
  public User getUserBySpEL(Integer id) {}
  ```

* `keyGenerator`：key 的生成器。key 和 keyGenerator 二选一使用。

  ```java
  @Cacheable(cacheNames = "userByKeyGenerator", keyGenerator = "myKeyGenerator")
  public User getUserByKeyGenerator(Integer id) {}
  ```

* `condition`：指定符合条件的情况下才缓存。

  ```java
  @Cacheable(cacheNames = "userByCondition", condition = "#id > 1")
  public User getUserByCondition(Integer id) {}
  ```

* `unless`：指定不符合条件的情况下才缓存。（可以获取到结果进行判断，通过 #result 获取方法结果，unless，汉语意思，除非，指`会缓存，除了。。。之外`）。

  ```java
  @Cacheable(cacheNames = "userByUnless", unless = "#id > 1")
  public User getUserByUnless(Integer id) {}
  ```

* `sync`：是否使用异步模式。

### 3.@CachePut

> `@CachePut` 的作用 主要针对配置，能够根据方法的请求参数对其结果进行缓存。
>
> * 区别于 `@Cacheable`，它`每次都会触发真实方法的调用`，可以保证缓存的一致性。
> * 属性与 `@Cacheable` 类同。

```java
@CachePut(cacheNames = "users" , key = "#user.id")
public User addUser(User user) {}
```

### 4.@CacheEvict

> `@CacheEvict` 的作用 主要针对方法配置，能够`根据一定的条件对缓存进行清空`。

**常用属性**

* `cacheNames`、`value`：用来指定缓存组件名称。
* `key`：缓存数据的 key，可以用它来指定。默认使用所有参数的值进行组合。（key可以使用 spEL 表达式来编写）。
* `condition`：指定符合条件的情况下的缓存。
* `allEntries`：是否清空所有缓存，缺省为false。
* `beforeInvocation`：是否在方法执行前就清空，缺省为false，缺省情况下，如果方法执行抛异常，则不会清空缓存。

```java
@CacheEvict(cacheNames = "users", key = "#id")
public void delUserCache(Integer id) {}
```

### 5.@CacheConfig

> `@CacheConfig` 的作用 主要针对类配置，能够`设置当前类中 @Cacheable 的 value 属性默认值`。当然如果 `@Cacheable` 设置了 value，还是以设置的值为准。

**常用属性**

* `cacheNames`： 指定缓存名称默认值。

### 6.@Caching

> `@Caching` 的作用 主要针对方法配置，能够`组合多个Cache注解`。比如用户新增成功后，我们可能需要添加 id -> user、username -> user、email -> user 的缓存，此时就需要 `@Caching` 组合多个注解标签了。

**常用属性**

* `cacheable`：组合多个 `@Cacheable` 注解
* `put`：组合多个 `@CachePut` 注解
* `evict`：组合多个 `@CacheEvict` 注解

```java
@CacheConfig(cacheNames = "users")
public class CacheTestServiceImpl implements CacheTestService {
    /**
     * @Cacheable 的 cacheNames 默认为 "users"
     */
    @Cacheable(key="#id")
    public User getUser(Integer id) {...}
}
```

---

## 三、spEL表达式

![spEL表达式](https://img-blog.csdnimg.cn/c9486246cfb546abb3cf558c83002f82.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAQUNHa2FrYV8=,size_20,color_FFFFFF,t_70,g_se,x_16)

