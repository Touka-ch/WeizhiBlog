import { Service } from './Service.js'
/*
 * User
 * post   注册                {{base}}/users
 * delete 删除                {{base}}/users/:id
 * put    更新(全部)           {{base}}/users/:id
 * patch  更新（部分）          {{base}}/users/:id
 * get    所有用户             {{base}}/users
 * get    单个用户             {{base}}/users/:id
 */
export function userRequest(method, id, data) {
  return Service({
    method: method,
    url: '/users' + (id ? '/' + id : ''),
    data: JSON.stringify(data)
  })
}
/*
 * Article
 * post   增加                {{base}}/articles
 * delete 删除                {{base}}/articles/:id
 * put    更新(全部)属性        {{base}}/articles/:id
 * patch  更新（部分）          {{base}}/articles/:id
 * get    某篇文章             {{base}}/articles/:id
 * get    所有文章             {{base}}/articles
 * *********************************************************************
 * get    某用户所有文章        {{base}}/articles/user/:uid
 * *********************************************************************
 * get    某标签所有文章        {{base}}/articles/tag/:tid
 * *********************************************************************
 * get    某用户某目录所有文章    {{base}}/articles/user/:uid/category/:cid
 * *********************************************************************
 * get    某用户某标签所有文章    {{base}}/articles/user/:uid/tag/:tid
 * *********************************************************************
 * get    某文章所有标签         {{base}}/articles/:id/tags
 */
export function articleRequest(method, id, data) {
  return Service({
    method: method,
    url: '/articles/' + (id ? id : ''),
    data: JSON.stringify(data)
  })
}
export function articleUserRequest(uid) {
  return Service({
    method: 'get',
    url: '/articles/user/' + (uid ? uid : '')
  })
}
export function tagArticleRequest(tid) {
  return Service({
    method: 'get',
    url: '/articles/tag/' + (tid ? tid : '')
  })
}
export function articleUserCategoryRequest(uid, cid) {
  return Service({
    method: 'get',
    url: '/articles/user/' + (uid ? uid : '') + '/category/' + (cid ? cid : '')
  })
}
export function articleUserTagRequest(uid, tid) {
  return Service({
    method: 'get',
    url: '/articles/user/' + (uid ? uid : '') + '/tag/' + (tid ? tid : '')
  })
}
export function articleTagRequest(id) {
  return Service({
    method: 'get',
    url: '/articles/' + (id ? id : '') + '/tags'
  })
}
/*
 * Category
 * post   添加用户目录          {{base}}/categories   //post里面要输入用户id 是第二个位置
 * delete 删除                {{base}}/categories/:id
 * put    更新(全部)属性        {{base}}/categories/:id
 * patch  更新（部分）          {{base}}/categories/:id
 * *********************************************************************
 * get    某用户所有目录        {{base}}/categories/user/:uid
 */
export function categoryRequest(method, id, data) {
  return Service({
    method: method,
    url: '/categories/' + (id ? id : ''),
    data: JSON.stringify(data)
  })
}
export function categoryUserRequest(uid) {
  return Service({
    method: 'get',
    url: '/categories/user/' + (uid ? uid : '')
  })
}
/*
 * Tag
 * post   添加文章标签          {{base}}/tags/article/:aid
 * delete 删除所有标签          {{base}}/tags/article/:aid
 * get    文章所有标签          {{base}}/tags/article/:aid
 * *********************************************************************
 * post   添加多个标签          {{base}}/tags/article/:aid/multi
 * *********************************************************************
 * delete 删除文章某个标签       {{base}}/tags/:tid/article/:aid
 */
export function tagRequest(method, aid, data) {
  return Service({
    method: method,
    url: '/articles/' + (aid ? aid : ''),
    data: JSON.stringify(data)
  })
}
export function mulTagRequest(aid, data) {
  return Service({
    method: 'post',
    url: '/tags/article/' + (aid ? aid : '') + '/multi',
    data: JSON.stringify(data)
  })
}
export function tagDeleteRequest(tid, aid, data) {
  return Service({
    method: 'delete',
    url: '/tags/' + (tid ? tid : '') + '/article/' + (aid ? aid : ''),
    data: JSON.stringify(data)
  })
}
/*
 * Role
 * get    判断权限              {{base}}/role/isAdmin/:uid
 * put    设置管理员             {{base}}/role/setAdmin/:uid
 * put    设置普通用户           {{base}}/role/setOrdinary/:uid
 */
export function isAdminRequest(uid) {
  return Service({
    method: 'get',
    url: '/role/isAdmin/' + (uid ? uid : '')
  })
}
export function setAdminRequest(uid) {
  return Service({
    method: 'put',
    url: '/role/setAdmin/' + (uid ? uid : '')
  })
}
export function setOrdinaryRequest(uid) {
  return Service({
    method: 'put',
    url: '/role/setOrdinary/' + (uid ? uid : '')
  })
}
/*
 * Data
 * get    浏览量                {{base}}/data/pv/:uid
 * get    喜欢数                {{base}}/data/like/:uid
 * get    评论数                {{base}}/data/like/:uid
 * get    喜欢数                {{base}}/data/like/:uid
 * get    喜欢数                {{base}}/data/like/:uid
 * get    喜欢数                {{base}}/data/like/:uid
 * get    喜欢数                {{base}}/data/like/:uid
 * put    用户评论               {{base}}/role/setOrdinary/:uid
 */
//暂未完成
export function pvRequest(uid, n) {
  return Service({
    method: 'get',
    url: '/data/pv/' + uid + (n ? '/' + n : '')
  })
}
export function likeRequest(uid, n) {
  return Service({
    method: 'get',
    url: '/data/like/' + uid + (n ? '/' + n : '')
  })
}
export function commentRequest(uid, n) {
  return Service({
    method: 'get',
    url: '/data/comment/' + uid + (n ? '/' + n : '')
  })
}
/*
 * File
 * post    传文件               {{local}}/file
 */
export function fileRequest(file) {
  return Service({
    method: 'post',
    url: '/file',
    data: file
  })
}
/*
 * Login
 * post   登陆                  {{base}}/doLogin
 * get    登出                  {{base}}/logout
 */
export function loginRequest(data) {
  return Service({
    method: 'post',
    url: '/login',
    data: JSON.stringify(data)
  })
}
export function logoutRequest() {
  return Service({
    method: 'get',
    url: '/logout/'
  })
}
