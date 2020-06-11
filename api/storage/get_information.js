#!/usr/bin/node
const connection = require('./conection_database');

//Function that checks is if the email is in the DB
const findUser = data => {
  const userType = data.userType + 's';
  return new Promise((resolve, reject) => {
    const userExists = (err, result) => {
      if (err) {
        reject(err);
        return;
      }
      if (result && result.length > 0) {
        resolve({'status': 'exists'});
      } else {
        resolve({'status' : 'not exists'});
      }
  }
  connection.query('SELECT * FROM ' + userType + ' WHERE emailAddress = ?', data.emailAddress, userExists);
});
}

//Function that gets an user and checks if the user exists
const  getUser= (email, userType) => {
  console.log(userType);
  return new Promise((resolve, reject)=> {
  const callbackDB =  (err, result) => {
    if (err) {
      reject(err);
      return;
    }

    if (result && result.length > 0) {
      console.log("User exists");
      result[0]['status'] = 'exists';
      result[0]['userType'] = userType;
      console.log(result[0]);
      resolve(result[0]);
    } else {
        console.log("User does not exist");
        resolve({"status": "not exists"});
    }
  }
  connection.query("SELECT * FROM "+  userType  +" WHERE emailAddress = ?", email , callbackDB);
});
}

//Funtion that verifies worker's debt status
const availableNeeds = data => {
  return new Promise ((resolve, reject) => {
    const callbackAvailable = (err ,result) => {
      if (err) {
        reject(err);
        return;
      } else {
        resolve(result);
        return;
      }
    }
    connection.query("SELECT * FROM needs WHERE status IN ('pending') LIMIT 3", callbackAvailable);
  });
}

//Function that gets loan by worker's id
const checkLoan = id => {
  return new Promise((resolve, reject) => {
    const callbackCheckStatus = (err, result) => {
      if (err) {
        reject(err);
        return;
      } else {
        result[0]['loanStatus'] = 'active';
        resolve(result);
      }
    }
    connection.query("SELECT * FROM needs WHERE idWorker=?", id, callbackCheckStatus);
  });
}

//Function that gets loan by need's id
const checkStatus = id => {
  console.log(id)
  return new Promise((resolve, reject) => {
    const callbackCheckStatus = (err, result) => {
      if (err) {
        reject(err);
        return;
      } else {
        resolve(result);
      }
    }
    connection.query("SELECT * FROM needs WHERE idNeed=?", id, callbackCheckStatus);
  });
}

//Function that updates
const changeStatus = id => {
  const callbackStatus = (err, result) => {
    if (err) {
      return;
    } else {
      return ({status:'Changed'});
    }
  }
  connection.query("UPDATE needs SET status='resolved' WHERE idNeed=?", id, callbackStatus)
}

module.exports = { findUser, getUser, availableNeeds, checkLoan, checkStatus, changeStatus };
