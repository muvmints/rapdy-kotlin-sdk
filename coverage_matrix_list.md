### Compact coverage matrix — services and tested public methods

Below is a concise matrix of every service under `com.ideazlab.jeie.muvmints.rapyd.sdk.services` and the public methods
covered by unit tests (Mockito + JUnit 5). Method names follow the service source APIs exactly as tested.

- AddressService
    - `createAddress`, `retrieveAddress`, `updateAddress`, `deleteAddress`

- CardEligibilityService
    - `retrieveCardEligibility`

- CardTokenService
    - `createCardTokenHostedPage`

- CheckoutService
    - `createCheckoutPage`, `retrieveCheckoutPage`

- CnlsService
    - `initiateMerchantQuery`, `retrieveQueryResults`

- CouponService
    - `listCoupons`, `createCoupon`, `retrieveCoupon`, `updateCoupon`, `deleteCoupon`

- CustomerPaymentMethodService
    - `listCustomerPaymentMethods` (params filtering/ordering + empty case), `addCustomerPaymentMethod`,
      `retrieveCustomerPaymentMethod`, `updateCustomerPaymentMethod`, `deleteCustomerPaymentMethod`

- CustomerService
    - `listCustomers`, `createCustomer`, `retrieveCustomer`, `updateCustomer`, `deleteCustomer`, `retrieveDiscount`,
      `deleteCustomerDiscount`

- DigitalWalletService
    - `retrieveApplePaySession(body)`, `retrieveApplePaySession(displayName, initiativeContext)`

- DisputeService
    - `listDisputes` (params filtering/ordering + empty case), `retrieveDispute`

- EscrowService
    - `listEscrowReleases`, `releaseFundsFromEscrow`, `getEscrow`

- GroupPaymentService
    - `createGroupPayment`, `retrieveGroupPayment`, `cancelGroupPayment`, `refundGroupPayment`

- InvoiceService
    - `listInvoices` (params filtering/ordering + empty case), `createInvoice`, `retrieveInvoice`, `updateInvoice`,
      `deleteInvoice`, `voidInvoice`, `finalizeInvoice`, `payInvoice`, `getInvoiceLines`, `getUpcomingInvoice`,
      `getUpcomingInvoiceLines`

- IssuingService
    - `displayIssuedCardDetailsToCustomer`, `listCards` (full query set + all-null case), `createCard`, `retrieveCard`,
      `activateCard`, `personalizeCard`, `changeCardStatus`, `listCardTransactions` (full query set + all-null case),
      `retrieveCardTransaction`, `setCardPin`, `createGooglePayCardToken`, `issueVirtualAccountNumber`, simulations:
      `simulateBlockCard`, `simulateCardAuthorizationEEA`, `simulateCardReversalEEA`,
      `simulateClearingCardTransactionEEA`, `simulateCardRefundEEA`, `simulateCardAdjustmentEEA`

- LocalizationService
    - `getFxRate` (uppercases buy/sell currencies and stringifies amount), `listLanguages`, `listCountries`,
      `listCurrencies`

- OrderService
    - `listOrders` (params filtering/ordering + empty case), `createOrder`, `getOrder`, `updateOrder`,
      `createOrderReturn`, `listOrderReturns` (params filtering/ordering + empty case), `payOrder`

- PaymentLinkService
    - `createPaymentLink`, `retrievePaymentLink`

- PaymentService
    - `createPayment`, `listPayments` (params filtering/ordering + empty case), `retrievePayment`, `updatePayment`,
      `cancelPayment`, `capturePayment(body)`, `capturePayment()` (null body pass-through), `completePayment`

- PaymentsMethodService
    - `listPaymentMethodsByCountry` (lowercases `country`, optional `currency` passthrough, null-currency case),
      `getRequiredFieldsForType`

- PayoutMethodTypeService
    - `listPayoutMethodTypes` (params filtering/ordering + empty case), `getPayoutRequiredFields` (uppercases
      `beneficiary_country` and `payout_currency`, stringifies `payout_amount`)

- PayoutService
    - `listPayouts` (params filtering/ordering + empty case), `createPayout`, `getPayout`, `updatePayout`, beneficiary:
      `createBeneficiary`, `validateBeneficiary`, `getBeneficiary`; sender: `createSender`, `getSender`, `deleteSender`;
      flow actions: `confirmPayout`, `completePayout`, `payout3DSResponse`, `returnPayout`; documents:
      `uploadPayoutDocument`, `listPayoutDocuments`, `deleteAllPayoutDocuments`, `getPayoutDocument`,
      `deletePayoutDocument`; batch: `createMassPayout`

- PlanService
    - `listPlans` (params filtering/ordering + empty case), `createPlan`, `retrievePlan`, `updatePlan`, `deletePlan`

- ProductService
    - `listProducts` (params filtering/ordering + empty case), `createProduct`, `getProduct`, `updateProduct`,
      `deleteProduct`

- RefundService
    - `listRefunds` (params filtering/ordering + empty case), `createRefund`, `completeRefund`, `retrieveRefund`,
      `updateRefund`

- SkuService
    - `listSkus` (params filtering/ordering + empty case), `createSku`, `getSku`, `updateSku`, `deleteSku`

- SubscriptionService
    - Subscriptions: `listSubscriptions` (params filtering/ordering + empty case), `createSubscription`,
      `retrieveSubscription`, `updateSubscription`, `cancelSubscription`, `deleteSubscriptionDiscount`,
      `createSubscriptionByHostedPage`
    - Subscription Items: `listSubscriptionItems` (filters/ordering/null), `createSubscriptionItem`,
      `retrieveSubscriptionItem`, `updateSubscriptionItem`, `deleteSubscriptionItem`
    - Usage Records: `listSubscriptionItemUsageRecords` (filters/ordering/null), `createSubscriptionItemUsageRecord`
    - Invoice Items: `listInvoiceItems` (filters/ordering/null), `createInvoiceItem`, `retrieveInvoiceItem`,
      `updateInvoiceItem`, `deleteInvoiceItem`

- VerifyService
    - `verifyIdentity`, `listIdDocuments` (with-country and without-country cases), `getApplicationTypesByCountry`,
      `getApplicationStatus`, `createHostedApplication`, `getHostedApplication`

- VirtualAccountService
    - `createVirtualAccount`, `simulateBankTransferToVirtualAccount`, `retrieveVirtualAccount`,
      `updateRequestedCurrency`, `closeVirtualAccount`, `capabilitiesOfVirtualAccounts`, `getRemitterDetails`,
      `getTransactionDetails`

- WalletService
    - `listWallets` (all optional query params + all-null case), `createWallet`, `getWalletAccounts`, `retrieveWallet`,
      `updateWallet`, `deleteWallet`, `changeWalletStatus`, `listContacts`, `createContact`, `getContact`,
      `updateContact`, `deleteContact`, `getContactComplianceLevels`

- WebhookService
    - `listWebhooks` (params filtering/ordering + empty case), `getWebhook`, `resendWebhook`

If you’d like this exported as a CSV/Markdown table or annotated with file paths and line numbers, tell me your
preferred format and I’ll generate it.