#!/usr/bin/node
const connection = require('./conection_database');


//Function that reduces the amount avialable to invest
const newBalanceInvestor = data => {
  connection.query('UPDATE investors SET investStack = investStack - ' + data.moneyInvestment + ' WHERE idInvestor = ' + data.idInvestor, (err,rows) => {
    if(err) throw err;
  
    console.log('Amount available changed');
  });
}

//Function updates amount ramining of the need
const updatemoneyNeed = data => {
  connection.query('UPDATE needs SET amountRemaining = amountRemaining - ' + data.moneyInvestment + ' WHERE idNeed = ' + data.idNeed, (err, rows) => {
    if(err) throw err;

    console.log("New investment in the need");
  })
}

//Function that changes the status of the need when is solved
const checkStatusNeed = data => {
  const id = data.idNeed
  connection.query("UPDATE needs SET status='resolved' WHERE idNeed = " + id + " AND amountRemaining = 0", (err, rows) => {
    if(err) throw err;

    console.log("A needs is solved");
  })
}

//Function that sums all the payments
const pay = data => {
  connection.query("UPDATE needs SET amountRemaining = " + data.pay + " WHERE idWorker = " + data.idWorker, (err, rows) => {
    if(err) throw err;

    console.log("Payment effective");
  })
}


module.exports = { newBalanceInvestor, updatemoneyNeed, checkStatusNeed };
