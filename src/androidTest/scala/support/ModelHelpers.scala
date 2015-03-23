package com.obsidian
package test

import banking.{Balances, Transaction, Wallet}

trait ModelHelpers {
  def wallet(
    balances: Balances = this.balances(),
    transactions: IndexedSeq[Transaction] = IndexedSeq.empty
  ) =
    Wallet(balances = balances, transactions = transactions)

  def balances(
    bitpeso: Int = 0,
    bitdollar: Int = 0,
    bitcoin: Int = 0
  ) =
    Balances(bitpeso = bitpeso, bitdollar = bitdollar, bitcoin = bitcoin)
}
