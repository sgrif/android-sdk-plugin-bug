package com.obsidian
package banking

case class Wallet(
  balances: Balances,
  transactions: IndexedSeq[Transaction]
)
