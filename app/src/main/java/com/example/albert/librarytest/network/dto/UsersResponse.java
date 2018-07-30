package com.example.albert.librarytest.network.dto;

import java.io.Serializable;

public class UsersResponse extends CommonResponse implements Serializable {

    public int id;
    public String avatar_url;
    public String name;
    public String company;
    public int public_repos;
    public int followers;
    public int following;

    @Override
    public String toString() {
        return "UsersResponse{" +
                "id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", public_repos='" + public_repos + '\'' +
                ", followers='" + followers + '\'' +
                ", following='" + following + '\'' +
                '}';
    }
}
