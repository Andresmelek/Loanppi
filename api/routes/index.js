/**
 * This module connects rendering modules to routes
 */
const express = require('express');
const router = express.Router()
const { helloWorld, searchUSer, NewUser, update, newNeed, options } = require('./gets')


router.get('/app/api/v1/', helloWorld)
router.get('/app/api/v1/user', searchUSer)
router.post('/app/api/v1/new_user', NewUser)
router.put('/app/api/v1/update', update)
router.post('/app/api/v1/lend', newNeed)
router.get('/app/api/v1/options', options)

module.exports = router
