package com.bird.commons.object;

import lombok.Data;

/**
 * @author master
 * @date 2020-04-23 13:27
 */
@Data
public class PageVo {

    private int pageNum;

    private int pageSize;

    private long totalPage;

    private long totalSize;
}
