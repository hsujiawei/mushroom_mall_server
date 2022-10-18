package com.mushroom.admin.domain;

// 账号分为 Admin 账号和普通的 User 账号，Admin 账号可以对 User 账号进行管理

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "admin_user")
@Entity
@JsonIncludeProperties({"id", "username", "phoneNumber", "status", "avatar", "createTime", "roles"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                     // 管理员 ID
    @Column(nullable = false, columnDefinition = "VARCHAR(30) UNIQUE")
    private String username;                // 管理员名称
    @Column(nullable = false)
    private String password;                // 管理员密码
    @Column(columnDefinition = "BIGINT UNIQUE")    // 管理员的手机号是唯一的
    private Long phoneNumber;               // 管理员密码
    @Column(nullable = true)
    private byte status;                    // 管理员账号状态：-1 -> 禁用, 1 -> 正常
    private String avatar;                  // 管理员头像
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date createTime;                // 管理员创建时间

    @Transient // 该注解指定该字段不是数据库字段
    private List<Role> roles = new ArrayList<>();   // 管理员的角色

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 存放 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
}
