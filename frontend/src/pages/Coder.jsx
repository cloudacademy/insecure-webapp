import React, { useState } from 'react';
import Editor from 'react-simple-code-editor';
import { highlight, languages } from 'prismjs/components/prism-core';
import 'prismjs/components/prism-clike';
import 'prismjs/components/prism-javascript';
import 'prismjs/themes/prism.css';
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export const CoderPage = () => {
    const [code, setCode] = useState(
`number_list = [x ** 2 for x in range(10) if x % 2 == 0]
print(number_list)`
    );

    const [output, setOutput] = useState('');

    var APIHOSTPORT = `${window._env_.REACT_APP_APIHOSTPORT}`;

    async function runCode() {
        var URL = `http://${APIHOSTPORT}/execute`;
        var token = window.localStorage.getItem("user");
        console.log("t1:" + token);
        const response = await fetch(URL, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "x-auth-token": token
          },
          body: JSON.stringify({code: code})
        });

        const stdout = await response.json();
        let base64ToString = Buffer.from(stdout.output, "base64").toString();
        console.log(base64ToString);
        setOutput(base64ToString);
    };

    function handleClick(event) {
        event.preventDefault();
        runCode();
    }

    return (
        <>
            <Box sx={{ m: 2, fontFamily: 'default' }} >
                <Typography component="h1" variant="h5">
                    Python Editor:
                </Typography>
            </Box>
            <Box
                sx={{
                m: 2,
                marginTop: 2,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                width: 1200,
                bgcolor: "#fafafa"
                }}
            >
                <Editor
                    value={code}
                    onValueChange={code => setCode(code)}
                    highlight={code => highlight(code, languages.js)}
                    padding={10}
                    style={{
                        fontFamily: '"Fira code", "Fira Mono", monospace',
                        fontSize: 12,
                        width: 1200,
                        height: 400
                    }}
                />
            </Box>
            <Box sx={{ m: 2 }} >
                <Button variant="contained" onClick={handleClick}>Execute</Button>
            </Box>
            <Box sx={{ m: 2, fontFamily: 'default' }} >
                Output:
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