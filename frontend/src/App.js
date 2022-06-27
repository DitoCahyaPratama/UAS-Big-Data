import logo from './logo.svg'
import './App.css'
import React from 'react'
import axios from 'axios'

const baseURL = 'http://localhost:8082/api/hadoop/'

const App = () => {
  const [mapReduceResult, setMapReduceResult] = React.useState(null)

  React.useEffect(() => {
    axios.get(baseURL).then((response) => {
      console.log(response)
      setMapReduceResult(response)
    })
  }, [])

  if (!mapReduceResult) return null

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Data dari hasil map reduce hadoop</p>
        <p>{mapReduceResult.data}</p>
        <a
          className="App-link"
          href="http://192.168.0.12:9870/explorer.html#/DitoCahyaPratama/Hasil"
          target="_blank"
          rel="noopener noreferrer"
        >
          Untuk detail bisa klik link berikut ini
        </a>
      </header>
    </div>
  )
}

export default App
