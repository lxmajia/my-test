import request from '@/utils/request'



export function getConfigInfo(data) {
  return request({
    url: '/manager/getConfigInfoList',
    method: 'post',
    data
  })
}


export function updateConfigInfo(data) {
  return request({
    url: '/manager/updateConfigInfo',
    method: 'post',
    data
  })
}
