/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.exceptions;

/**
 *
 * @author chihoang
 */
public class InvalidAmountException extends Exception {

  public InvalidAmountException() {
    super("Invalid amount for transaction.");
  }
  
}
