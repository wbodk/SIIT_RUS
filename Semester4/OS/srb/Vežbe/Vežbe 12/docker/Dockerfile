# Use an official Python runtime as the base image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Python script to the container
COPY app.py .

# Install Flask and its dependencies
RUN pip install --no-cache-dir flask

# Expose port 8000 for the Flask web server
EXPOSE 8000

# Define the command to run the web app when the container starts
CMD ["python", "app.py"]
