import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/manager/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/manager/info',
    method: 'post',
    header: {"satoken": token}
  })
}

export function logout() {
  return request({
    url: '/manager/logout',
    method: 'post'
  })
}
