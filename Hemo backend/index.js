const express=require('express');
const createError = require('http-errors');
const app=express();
require('dotenv').config();
const mongoose=require('mongoose')
const userRoute = require('./routes/user');
const bloodRoute=require('./routes/blood')

mongoose.connect(process.env.DB_CONNECT,{useNewUrlParser: true}, ()=>{
    console.log('Mongo DB running')
})
app.use(express.json())

//authentication route
app.use('/api/user', userRoute);

//data route
app.use('/api/blood', bloodRoute);




app.use((req, res, next)=>{
    next(createError(404, 'Not found'))
})

app.use((err, req, res, next) =>{
    res.status(err.status || 500);
    res.send({
            status: err.status || 500,
            message: err.message
    })
})

app.listen(3000, ()=>{
    console.log('Server up and running')
})