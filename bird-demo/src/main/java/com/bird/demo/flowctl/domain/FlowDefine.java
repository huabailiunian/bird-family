package com.bird.demo.flowctl.domain;

import lombok.Data;

import java.util.List;

/**
 * 流程定义
 *
 * @author master
 * @date 2020-03-31 09:05
 */
@Data
public class FlowDefine {

    String id;

    List<HandlerDefine> handlers;

}
