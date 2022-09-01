package com.wyd.vbloglearn.modules.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@Getter
@Setter
@ApiModel(value = "Category对象", description = "")
// 序列化是指把一个Java对象变成二进制内容，本质上就是一个byte[]数组。
// 为什么要把Java对象序列化呢？因为序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。
// 有了反序列化，保存到文件中的byte[]数组又可以“变回”Java对象，或者从网络上读取byte[]并把它“变回”Java对象。
// 本质：
//      序列化： java对象--> byte[]数组
//      反序列化： byte[]数组-->java对象
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String cateName;

    private Date date;


}
