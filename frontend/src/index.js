import ReactDOM from 'react-dom';
import React from 'react';
import initStore from 'config/store';
import RouterComponent from 'config/router';
import axios from 'axios';
import axiosConfig from 'config/axios';

require('stylus/main.styl');

axiosConfig();

var render = (session) => {
  const initialState = {
    authentication: {
      token: session.token || null,
      isAuthenticated: session.isAuthenticated || false,
      username: session.username || null
    }
  };
  const store = initStore(initialState);

  ReactDOM.render(
    <RouterComponent store={store}/>,
    document.getElementById('root')
  );
};

axios.get('/api/session')
  .then(res => render(res.data))
  .catch(err => render());

