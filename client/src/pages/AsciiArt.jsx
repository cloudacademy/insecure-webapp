import React, { useEffect, useState } from 'react';
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export const AsciiArtPage = () => {
    const [text, setText] = useState('');
    const [output, setOutput] = useState('');

    var APIHOSTPORT = `${window._env_.REACT_APP_APIHOSTPORT}`;

    async function createArt() {
        var URL = `http://${APIHOSTPORT}/asciiart`;
        var token = window.localStorage.getItem("user");
        console.log("t1:" + token);
        const response = await fetch(URL, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "x-auth-token": token
          },
          body: JSON.stringify({text: text})
        });

        const stdout = await response.json();
        let base64ToString = Buffer.from(stdout.output, "base64").toString();
        console.log(base64ToString);
        setOutput(base64ToString);
    };

    function handleClick(event) {
        event.preventDefault();
        createArt();
    }

    return (
        <>
            <Box sx={{ m: 2, fontFamily: 'default' }} >
                <Typography component="h1" variant="h5">
                    Ascii Art Generator:
                </Typography>
            </Box>
            <Box
                sx={{
                m: 2,
                marginTop: 2,
                }}
            >
                <TextField 
                    id="outlined-basic" 
                    label="Text" 
                    variant="outlined"
                    value={text}
                    style = {{width: 400}}
                    onInput={ e=>setText(e.target.value) }
                />
            </Box>
            <Box sx={{ m: 2 }} >
                <Button variant="contained" onClick={handleClick}>Generate</Button>
            </Box>
            <Box sx={{ m: 2 }} >
                <pre>
                    <code>
                        {output}
                    </code>
                </pre>
            </Box>
        </>
    );
}