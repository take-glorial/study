const request = (options={}) => {
	const fetchConfig = {
        method : options.method || 'GET',
        body : JSON.stringify(options.data),
        headers: {
            'Content-Type': 'application/json'
        },
        ...options
    };

	/*
	method: method || 'GET', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, cors, *same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json',	// 'application/x-www-form-urlencoded'
        },
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    */
	return fetch(options.url, fetchConfig);
};

function fetchPost(data) {
  return request({
    url: CONTEXTPATH + "fetchPostTest",
    method: "POST",
    data
  }).then((res) => {
  	if (res.ok) return res.text();		//res.json, blob, formdata
  	else alert('오류\r\n' + res.statusText);
  });
};

function fetchGet(data) {
  const queryString = Object.keys(data)
    .map(key => `${encodeURIComponent(key)}=${encodeURIComponent(data[key])}`)
    .join('&');

  return request({
    url: CONTEXTPATH + "fetchGetTest?" + queryString
  }).then((res) => {
  	if (res.ok) return res.text();		//res.json, blob, formdata
  	else alert('오류\r\n' + res.statusText);
  });
};