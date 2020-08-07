package com.weizhiblog.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private Integer id;

    /**
     * 用户名
     */
    @Size(max = 20, min = 3)
    @NotNull(message = "用户名不能为空！")
    private String username;

    /**
     * 用户昵称
     */
    @Size(max = 20, min = 3)
    @NotNull(message = "昵称不能为空！")
    private String nickname;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空！")
    private String password;

    /**
     * 用户状态
     */
    private Boolean enabled;

    /**
     * 用户邮箱
     */
    @Email
    @NotNull
    private String email;

    /**
     * 用户头像地址
     */
    private String userface;

    /**
     * 注册时间
     */
    private Date regTime;

    private List<String> roles;

    private Boolean status;

    public Boolean getStatus() {
        return enabled;
    }

    public void setStatus(Boolean status) {
        this.enabled = status;
        this.status = status;
    }

    /**
     * 设置密码
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEnabled(Boolean status) {
        this.enabled = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", userface='" + userface + '\'' +
                ", regTime=" + regTime +
                ", roles=" + roles +
                '}';
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled == null || enabled;
    }
}