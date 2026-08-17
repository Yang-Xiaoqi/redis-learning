package com.hmdp.service.impl;

import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryList() {
        // 1.在redis中查询店铺类型缓存
        String key = CACHE_SHOP_TYPE_KEY;
        List<String> stringTypeList = stringRedisTemplate.opsForList().range(key, 0, -1);
        if (!stringTypeList.isEmpty()) {
            // 3.命中，直接返回
            // List<String>  ===> List<ShopType>
            List<ShopType> shopTypeList = stringTypeList.stream()
                    .map(jsonStr -> JSONUtil.toBean(jsonStr, ShopType.class))
                    .collect(Collectors.toList());
            return Result.ok(shopTypeList);
        }

        // 4.没有命中，查询数据库
        List<ShopType> shopTypeList = query().orderByAsc("sort").list();
        // 5.判断数据库中是否存在
        if (shopTypeList == null) {
            // 6.不存在，返回错误信息
            return Result.fail("店铺类型不存在！");
        }
        // 7.存在，存入redis
        // List<ShopType>  ===> List<String>
        stringTypeList = shopTypeList.stream()
                .map(shopType -> JSONUtil.toJsonStr(shopType))
                .collect(Collectors.toList());
        stringRedisTemplate.opsForList().rightPushAll(key, stringTypeList);
        // 8.返回
        return Result.ok(shopTypeList);
    }
}
