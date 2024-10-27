package com.jichi.subject.infra.basic.es;

import lombok.Data;
import java.io.Serializable;

/**
 * es集群类
 *
 * @author ZhuFan
 * @data 2024/10/13/013 23:23
 */
@Data
public class EsClusterConfig implements Serializable {
    /**
     * 集群名称
     */
    private String name;

    /**
     * 集群节点
     */
    private String nodes;
}
