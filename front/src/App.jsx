import { useState } from 'react';
import VideoPlayer from './VideoPlayer';
import './App.css';

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <h1>WebPlayer using React+Vite and SpringBoot</h1>
      <VideoPlayer src="http://localhost:8080/videos/example.mp4" />
    </div>
  );
};

export default App;
