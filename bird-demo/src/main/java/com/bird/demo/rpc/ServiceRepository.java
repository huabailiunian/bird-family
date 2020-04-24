package com.bird.demo.rpc;

/**
 * 服务仓库
 *
 * @author master
 * @date 2020-04-22 17:20
 */
public interface ServiceRepository {

    /**
     * 根据ID查询服务
     *
     * @param serviceId 服务ID
     * @return 服务信息
     */
    ServiceVo get(String serviceId);

}
