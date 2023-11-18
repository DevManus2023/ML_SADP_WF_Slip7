/*Slip 7:
Create a Node.js file that writes an HTML form, with an upload field.
Note: With some improvement
 */
const express = require('express');
const multer = require('multer');
const upload = multer({ dest: 'uploads/' }); // Specify the directory where uploaded files will be stored
const app = express();
const path = require('path');

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'uploadForm.html'));
});

app.post('/upload', upload.single('file'), (req, res) => {
  const uploadedFile = req.file;
  if (!uploadedFile) {
    return res.send('Error: No file selected for upload.');
  }

  res.send(`File "${uploadedFile.originalname}" uploaded successfully.`);
});

const port = 3000;
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
