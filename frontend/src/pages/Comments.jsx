import React, { useEffect, useState } from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export const CommentsPage = () => {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    getComments();
  }, []);

  var APIHOSTPORT = `${window._env_.REACT_APP_APIHOSTPORT}`;

  async function getComments() {
    var URL = `http://${APIHOSTPORT}/comments`;
    var token = window.localStorage.getItem("user");
    console.log("t1:" + token);
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        "x-auth-token": token
      }
    });

    const comments = await response.json();
    setComments(comments);
  }

  async function addComment(newComment) {
    var URL = `http://${APIHOSTPORT}/comments`;
    var token = window.localStorage.getItem("user");
    console.log("t2:" + token);
    const response = await fetch(URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "x-auth-token": token
      },
      body: JSON.stringify({username: "jeremy", body: newComment})
    });

    getComments();
    setComment('');
  }

  const [comment, setComment] = useState('');

  function handleSubmit(event) {
    event.preventDefault();
    console.log( 'Comment:', comment);
    addComment(comment);
  }

  return (
    <>
      <Box sx={{ m: 2, fontFamily: 'default' }} >
        <Typography component="h1" variant="h5">
          Comments Board:
        </Typography>
      </Box>
      <Box
          sx={{
          m: 2,
          marginTop: 2,
          marginBottom: 2,
          bgcolor: "#fafafa",
          width: 1200
          }}
      >
        {comments.map(c => (
          <Box
            key={c.id}
            sx={{
            m: 2,
            marginTop: 1,
            marginBottom: 1,
            fontFamily: 'default'
            }}
          >
            <div key={c.id} dangerouslySetInnerHTML={{__html:c.body}}/>
          </Box>
        ))}
      </Box>
      <Box sx={{ m: 2 }} >
        <form onSubmit={handleSubmit} >
          <TextField 
            id="outlined-basic" 
            label="New Comment" 
            variant="outlined"
            value={comment}
            style = {{width: 400}}
            onInput={ e=>setComment(e.target.value) }
          />
        </form>
      </Box>
      <Box sx={{ m: 2 }} >
        <Button variant="contained" onClick={handleSubmit}>Save</Button>
      </Box>
    </>
  );
};
