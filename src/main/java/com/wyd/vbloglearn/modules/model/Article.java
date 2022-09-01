package com.wyd.vbloglearn.modules.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wyd
 * @since 2022-08-31 22:09:07
 */
@Data
@ApiModel(value = "Article对象", description = "博客文章对象")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    @ApiModelProperty("md文件源码")
    private String mdContent;

    @ApiModelProperty("html源码")
    private String htmlContent;

    private String summary;

    private Integer cid;

    private Integer uid;

    private Date publishDate;

    private Date editTime;

    @ApiModelProperty("0表示草稿箱，1表示已发表，2表示已删除")
    private Integer state;

    private Integer pageView;


}
