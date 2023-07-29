document.getElementById('displayBtn').addEventListener('click', function() {
    console.log(document.getElementById("text").value);
    console.log(encodeURIComponent(document.getElementById("text").value));
    fetch('http://localhost:8080/api/v1/notebook/getPdf?text=' + encodeURIComponent(document.getElementById("text").value))
      .then(response => response.blob())
      .then(blob => {
        document.getElementById('pdfEmbed').src = URL.createObjectURL(blob);;
      });
  });