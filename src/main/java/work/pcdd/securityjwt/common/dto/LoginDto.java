package work.pcdd.securityjwt.common.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户登录实体类
 *
 * @author 1907263405@qq.com
 * @date 2021/3/24 19:56
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 30, message = "密码长度不能超过30位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(max = 16, message = "密码长度不能超过16位")
    private String password;

}
