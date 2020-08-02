import { Service } from './Service.js'
export function UserLogin() {
  //UserLogin-登录
  return Service({
    url: '/user/login',
    method: 'post'
  })
}
export function UserSignUP() {
  //UserSignUp-注册
  return Service({
    url: '/user/signup',
    method: 'post'
  })
}
export function UserDelete() {
  //UserDelete-删除
  return Service({
    url: '/user/delete',
    method: 'post'
  })
}
export function UserDeleteSelective() {
  //UserDeleteSelective-删除多个用户
  return Service({
    url: '/user/delete/selective',
    method: 'post'
  })
}
export function UserUpdate() {
  //UserUpdate-更新
  return Service({
    url: '/user/update',
    method: 'post'
  })
}
export function UserEnable() {
  //UserEnable-更新用户状态
  return Service({
    url: '/user/enable',
    method: 'post'
  })
}
export function UserUpdatePassword() {
  //UserUpdatePassword-更新用户密码
  return Service({
    url: '/user/password',
    method: 'post'
  })
}
export function ListAllUser() {
  //ListAllUser-查询所有用户
  return Service({
    url: '/user/all',
    method: 'get'
  })
}
export function GetUserInfo() {
  //GetUserInfo-查询单个用户
  return Service({
    url: '/user/88888',
    method: 'get'
  })
}
export function addArticle() {
  //addArticle-添加一篇文章
  return Service({
    url: '/article/add',
    method: 'post'
  })
}
export function deleteArticle() {
  //deleteArticle-删除一篇文章
  return Service({
    url: '/article/delete',
    method: 'post'
  })
}
export function updateArticleState() {
  //updateArticleState-更新文章状态（删除、草稿、正常）
  return Service({
    url: '/article/state',
    method: 'post'
  })
}
export function updateSelectiveArticleState() {
  //updateSelectiveArticleState-更新所选文章状态
  return Service({
    url: '/article/state/selective',
    method: 'post'
  })
}
export function updateArticle() {
  //updateArticle-更新文章
  return Service({
    url: '/article/update',
    method: 'post'
  })
}
export function updateArticlePublicStatus() {
  //updateArticlePublicStatus-更新文章公开状态
  return Service({
    url: '/article/publicStatus',
    method: 'post'
  })
}
export function listAllArticles() {
  //listAllArticles-列出所有文章
  return Service({
    url: '/article/all',
    method: 'post'
  })
}
export function listAllArticlesFromUser() {
  //listAllArticlesFromUser
  return Service({
    url: '/article/fromUser',
    method: 'post'
  })
}
export function listAllArticlesFromTag() {
  //listAllArticlesFromTag
  return Service({
    url: '/article/fromTag',
    method: 'post'
  })
}
export function listAllArticlesFromUserAndCategory() {
  //listAllArticlesFromUserAndCategory
  return Service({
    url: '/article/fromUserCategory',
    method: 'post'
  })
}

export function listAllArticlesFromUserAndTag() {
  //listAllTagsFromArticle
  return Service({
    url: '/article/fromUserTag',
    method: 'post'
  })
}
export function listAllTagsFromArticle() {
  //listAllTagsFromArticle
  return Service({
    url: '/article/allTags',
    method: 'post'
  })
}
export function listAllCommentsFromArticle() {
  //listAllTagsFromArticle
  return Service({
    url: '/article/allTags',
    method: 'post'
  })
}
export function deleteCategory() {
  //deleteCategory-删除某目录
  return Service({
    url: '/category/delete',
    method: 'post'
  })
}
export function AddCategory() {
  //AddCategory-某用户添加目录
  return Service({
    url: '/category/add',
    method: 'post'
  })
}
export function updateCategory() {
  //updateCategory-更新目录名
  return Service({
    url: '/article/allTags',
    method: 'post'
  })
}
export function listAllCategoriesByUid() {
  //listAllCategoriesByUid-某用户所有目录
  return Service({
    url: '/category/update',
    method: 'post'
  })
}
export function listArticlesByUidAndCid() {
  //listArticlesByUidAndCid某目录所有文章
  return Service({
    url: '/category/articles',
    method: 'post'
  })
}
export function addComment() {
  //addComment-添加评论
  return Service({
    url: '/comment/add',
    method: 'post'
  })
}
export function deleteComment() {
  //deleteComment-删除评论
  return Service({
    url: '/comment/delete',
    method: 'post'
  })
}
export function deleteAllCommentsByAid() {
  //deleteAllCommentsByAid-删除某文章所有评论
  return Service({
    url: '/comment/delete/all',
    method: 'post'
  })
}
export function updateComment() {
  //updateComment-更新评论内容
  return Service({
    url: '}/comment/update',
    method: 'post'
  })
}
export function listAllCommentsByAid() {
  //listAllCommentsByAid-某文章所有评论
  return Service({
    url: '/comment/all',
    method: 'post'
  })
}
export function listAllLevel1CommentsByAid() {
  //listAllCommentsByAid-某文章所有一级评论
  return Service({
    url: '/comment/all/level1',
    method: 'post'
  })
}
export function listAllChildrenComments() {
  //listAllChildrenComments-某评论所有子评论
  return Service({
    url: '/comment/children',
    method: 'post'
  })
}
export function listNextLevelComments() {
  //listNextLevelComments-某评论所有直接子评论
  return Service({
    url: '/comment/children',
    method: 'post'
  })
}
export function AddTag() {
  //AddTag-给某文章添加一个标签
  return Service({
    url: '/tag/add',
    method: 'post'
  })
}
export function AddMultipleTags() {
  //AddMultipleTags-给某文章添加多个标签
  return Service({
    url: '/tag/add/multiple',
    method: 'post'
  })
}
export function DeleteTagInArticle() {
  //DeleteTagInArticle-删除某文章的某个标签
  return Service({
    url: '/tag/delete',
    method: 'post'
  })
}
export function DeleteAllTagsInArticle() {
  //DeleteAllTagsInArticle-删除某文章的所有标签
  return Service({
    url: '/tag/deleteAll',
    method: 'post'
  })
}
export function isAdmin() {
  //isAdmin判断用户是不是管理员
  return Service({
    url: '/role/isAdmin',
    method: 'post'
  })
}
export function setAdmin() {
  //设置用户为管理员
  return Service({
    url: '/role/setAdmin',
    method: 'post'
  })
}
export function setOrdinary() {
  //设置用户为普通用户员
  return Service({
    url: '/role/setOrdinary',
    method: 'post'
  })
}
export function pvByUid() {
  //设置用户为管理员
  return Service({
    url: '/data/pv',
    method: 'post'
  })
}
export function likeNumByUid() {
  //likeNumByUid
  return Service({
    url: '/data/like',
    method: 'post'
  })
}
export function commentsByUid() {
  //likeNumByUid
  return Service({
    url: '/data/comment',
    method: 'post'
  })
}
export function pvByUidN() {
  //likeNumByUid
  return Service({
    url: '/data/pvNDay',
    method: 'post'
  })
}
export function likeNumByUidN() {
  //likeNumByUidN
  return Service({
    url: '/data/likeNDay',
    method: 'post'
  })
}
export function commentsByUidN() {
  //likeNumByUid
  return Service({
    url: '/data/commentNDay',
    method: 'post'
  })
}
export function uploadFile() {
  //likeNumByUid
  return Service({
    url: '/file/upload',
    method: 'post'
  })
}
