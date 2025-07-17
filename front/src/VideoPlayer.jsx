import React, { useRef, useState, useEffect } from 'react';
import './VideoPlayer.css';

const VideoPlayer = ({ src }) => {
  const videoRef = useRef(null);
  const [seekTime, setSeekTime] = useState('');
  const [currentTime, setCurrentTime] = useState(0);
  const [duration, setDuration] = useState(0);

  const handlePlay = () => {
    videoRef.current?.play();
  };

  const handlePause = () => {
    videoRef.current?.pause();
  };

  const handleSeek = () => {
    const seconds = parseFloat(seekTime);
    if (!isNaN(seconds) && videoRef.current) {
      videoRef.current.currentTime = seconds;
    }
  };



  useEffect(() => {
    const video = videoRef.current;

    const updateTime = () => {
      setCurrentTime(video.currentTime);
    };

    const setVideoDuration = () => {
      setDuration(video.duration);
    };

    if (video) {
      video.addEventListener('timeupdate', updateTime);
      video.addEventListener('loadedmetadata', setVideoDuration);
    }

    return () => {
      if (video) {
        video.removeEventListener('timeupdate', updateTime);
        video.removeEventListener('loadedmetadata', setVideoDuration);
      }
    };
  }, []);

  const formatTime = (seconds) => {
    const min = Math.floor(seconds / 60);
    const sec = Math.floor(seconds % 60).toString().padStart(2, '0');
    return `${min}:${sec}`;
  };

  return (
    <div className="video-player">
      <video
        ref={videoRef}
        width="100%"
        controls={false}
        src={src}
      />
      <div className='range'>
        <span>{formatTime(currentTime)} / {formatTime(duration)}</span>
        <input
          type="range"
          className="progress-bar"
          min="0"
          max={duration}
          step="0.1"
          value={currentTime}
          onChange={(e) => handleSeek(parseFloat(e.target.value))}
        />
      </div>

      <div className="controls-row">
        <button onClick={handlePlay}>▶ Play</button>
        <button onClick={handlePause}>⏸ Pause</button>
      </div>

      <div className="seek-controls">
        <input
          type="number"
          placeholder="seconds"
          value={seekTime}
          onChange={(e) => setSeekTime(e.target.value)}
        />
        <button onClick={handleSeek}>Go to</button>
      </div>
    </div>
  );
};

export default VideoPlayer;
