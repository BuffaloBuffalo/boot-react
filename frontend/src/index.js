import ReactDOM from 'react-dom';
import React from 'react';
import axios from 'axios';
  
  class DataPoint extends React.Component{
    render(){
      return <li>{this.props.value}</li>
    }
  }

  class DataList extends React.Component{
    render(){
      return <ul>
              {this.props.data.map(function(dataPoint) {
                return <DataPoint key={dataPoint} value={dataPoint}/>
              })}
            </ul>
    }
  }

  class App extends React.Component{
    constructor(props) {
      super(props);
      this.state = {data: props.initialData};
    }


   render(){
      var self = this;
      return <div>
                <DataList data={this.state.data}/>
                <button onClick={self.handleClick.bind(self)}>Generate New Value</button>
              </div>
    }
    handleClick(event){
      console.log('addValue',event);
      var app = this;
      axios.get('/api/simple').then(function(newVal){
        console.log('generated new value ',newVal);
        app.setState({data:app.state.data.concat(newVal.data)});
      });
    }
  }

  var initialData = ['Alpha','Beta','Gamma']

  ReactDOM.render(
    <App initialData={initialData}/>,
    document.getElementById('root')
  );