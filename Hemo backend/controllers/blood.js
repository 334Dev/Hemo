const mongoose = require('mongoose')
const createError = require('http-errors');
const Blood = require('../model/Blood')
const {bloodValidation, scheduleValidation} = require("../validation");
const Schedule = require('../model/schedule');

exports.blood_all_req = async (req, res, next)=>{
    try{
        location=req.query.location
        blood = req.query.blood
        const mblood = await Blood.find({location, blood})
        res.status(200).send(mblood);
    }catch(error){
        next(error)
        return
    }
}

exports.blood_one_req = async (req, res, next)=>{
    try{
        const {id} = req.params
        const blood = await Blood.findOne({_id: id});
        if(blood){
            res.status(200).send(blood)
        }else{
            throw createError(404, 'Not found')
        }
    }catch(error){
        next(error)
        return;
    }
}

exports.blood_save_req = async (req, res, next)=>{
    const {valid, error} = bloodValidation(req.body)
    if(!valid){
        next(createError(400, error))
        return
    }
    try{
        const blood = new Blood(req.body);
        await blood.save();
        res.status(200).send({
            status: 200,
            message: 'Saved'
        })
    }catch(error){
        next(error)
        return
    }
}

exports.blood_schedule = async (req, res, next)=>{
    const {valid, error} = scheduleValidation(req.body)
    if(!valid){
        next(createError(400, error))
        return;
    }

    try{
        const schedule = new Schedule(req.body)
        await schedule.save();
        res.status(200).send({
            status: 200,
            message: 'Saved'
        })
    }catch(error){
        next(error)
        return
    }

}