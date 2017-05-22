--SYSTEM 11 and SYS 12 B config.
/* compare config value copied from requirement with values in DB*/
ALTER SESSION SET CURRENT_SCHEMA=live_mo;
 SELECT *
FROM   (SELECT name,namespace,
               value,
               CASE name
                 WHEN
       'finance.export.processor.<ID of Chart of Account Location>' THEN
                 ( CASE
                 value
               WHEN
'com.reserveamerica.system.finance.export.impl.FundDetailsExportProcessor'
THEN
'True'
ELSE 'False'
END )
  WHEN 'LineOfBusiness' THEN ( CASE value
                                 WHEN '2' THEN 'True'
                                 ELSE 'False'
                               END )
  WHEN 'PrivilegeVoidReversalTransactionCoverage' THEN ( CASE value
                                                           WHEN '1' THEN 'True'
                                                           ELSE 'False'
                                                         END )
  WHEN 'PrivilegeReplacementTransactionCoverage' THEN ( CASE value
                                                          WHEN '1' THEN 'True'
                                                          ELSE 'False'
                                                        END )
  WHEN 'ReplacementTextIndicator' THEN ( CASE Upper(value)
                                           WHEN 'DUPLICATE' THEN 'True'
                                           ELSE 'False'
                                         END )
  WHEN 'GeneratingHarvestDocument' THEN ( CASE value
                                            WHEN '1' THEN 'True'
                                            ELSE 'False'
                                          END )
  WHEN 'SupersedingHarvestDocument' THEN ( CASE value
                                             WHEN '2' THEN 'True'
                                             ELSE 'False'
                                           END )
  WHEN 'GracePeriodForReturningPrivilegeDocuments' THEN ( CASE value
  WHEN '60' THEN 'True'
  ELSE 'False'
                                                          END )
  WHEN 'LogicallyDeleteReversedVehicle' THEN ( CASE Upper(value)
                                                 WHEN 'FALSE' THEN 'True'
                                                 ELSE 'False'
                                               END )
  WHEN 'GiftMessageIndicator' THEN ( CASE Upper(value)
                                       WHEN 'TRUE' THEN 'True'
                                       ELSE 'False'
                                     END )
  WHEN 'BankAccountRoutingNumberChecksumLogicID' THEN ( CASE value
                                                          WHEN '1' THEN 'True'
                                                          ELSE 'False'
                                                        END )
  WHEN 'RetainTraceNumber' THEN ( CASE value
                                    WHEN '0 - No' THEN 'True'
                                    ELSE 'False'
                                  END )
  WHEN 'eft.return.download.auto' THEN ( CASE Upper(value)
                                           WHEN 'TRUE' THEN 'True'
                                           ELSE 'False'
                                         END )
  WHEN 'PromptDonationInPrivilegeSale' THEN ( CASE Upper(value)
                                                WHEN 'TRUE' THEN 'True'
                                                ELSE 'False'
                                              END )
  WHEN 'HIPStateAbbr' THEN ( CASE value
                               WHEN 'ID' THEN 'True'
                               ELSE 'False'
                             END )
  WHEN 'PrintMailingLabel' THEN ( CASE Upper(value)
                                    WHEN 'TRUE' THEN 'True'
                                    ELSE 'False'
                                  END )
  WHEN 'UserPINPrompt' THEN ( CASE Upper(value)
                                WHEN 'NO' THEN 'True' --Check if NO or False
                                ELSE 'False'
                              END )
  WHEN 'UppercaseOutput' THEN ( CASE Upper(value)
                                  WHEN 'FALSE' THEN 'True'
                                  ELSE 'False'
                                END )
  WHEN 'EnforceSearchBeforeAddCustomer' THEN ( CASE Upper(value)
                                                 WHEN 'TRUE' THEN 'True'
                                                 ELSE 'False'
                                               END )
  WHEN 'GenerateTAN' THEN ( CASE namespace 
                                  when 'CallManager' then (case  Upper(value)
                                                        WHEN 'TRUE' THEN 'True'
                                                        ELSE 'False'
                                                        END )
                                   when 'LicenseIVR' then (case  Upper(value)
                                                        WHEN '' THEN 'True'
                                                        ELSE 'False'
                                                        END
                                                        )
                                   when 'LicenseInetVerifone' then (case  Upper(value)
                                                        WHEN '' THEN 'True'
                                                        ELSE 'False'
                                                        END
                                                        )
                                   when 'LicenseManager' then  (case  Upper(value)
                                                        WHEN 'TRUE' THEN 'True'
                                                        ELSE 'False'
                                                        END
                                                        )
                                   when 'LicenseWebPOS' then (case  Upper(value)
                                                        WHEN '' THEN 'True'
                                                        ELSE 'False'
                                                        END
                                                        )
                                   when 'OperationsManager' then (case  Upper(value)
                                                        WHEN '' THEN 'True'
                                                        ELSE 'False'
                                                        END
                                                        )
                                  end
                                   )   
  WHEN 'DocumentDidNotPrint' THEN ( CASE value
                                      WHEN '2' THEN 'True'
                                      ELSE 'False'
                                    END )
  WHEN 'HIPFilesRecipients' THEN ( CASE value
                                     WHEN
'<emails separated by semicolons if more than one>' THEN 'True'
                                     ELSE 'False'
                                   END )
  WHEN 'CleanPrivilegeLotteryQuotaTimeOut' THEN (
  CASE value
    WHEN '<number in minutes>' THEN 'True'
    ELSE 'False'
  END )
  WHEN 'IncludeNonCreditCardTransactions' THEN ( CASE value
  WHEN '<No or Yes>' THEN 'True'
  ELSE 'False'
                                                 END )
  WHEN 'IncludeNonEFTTransactions' THEN ( CASE value
                                            WHEN '<No or Yes>' THEN 'True'
                                            ELSE 'False'
                                          END )
  WHEN 'InventoryFulfillmentStore' THEN ( CASE value
                                            WHEN
'<Store ID of Fulfillment Store>' THEN 'True'
                                            ELSE 'False'
                                          END )
  WHEN 'finance.eft.transmission.file.baff.paymentOffsetCode' THEN (
  CASE value
    WHEN 'S' THEN 'True'
    ELSE 'False'
  END )
  WHEN 'finance.eft.transmission.file.baff.finChargesAllocationCode' THEN ( CASE
  value
WHEN '15' THEN 'True'
ELSE 'False'
END )
  WHEN 'finance.eft.transmission.file.baff.paymentDueDateDelayPeriod' THEN (
  CASE value
WHEN '2' THEN 'True'
ELSE 'False'
END )
  WHEN 'finance.eft.transmission.file.clrdEFTRemittTransmOffsetDays' THEN ( CASE
  value
WHEN '2' THEN 'True'
ELSE 'False'
END )
  WHEN 'TPOSDeviceAuthorizationConfig' THEN ( CASE value
                                                WHEN
'2 - Register Sharing Disallowed' THEN 'True'
                                                ELSE 'False'
                                              END )
  WHEN 'ProductsPrintLocLaRonge' THEN ( CASE value
                                          WHEN
'<Comma-delimited list of product codes>' THEN 'True'
                                          ELSE 'False'
                                        END )
  WHEN 'hfDocumentPrintWatermark' THEN ( CASE value
                                           WHEN '<Watermark Text>' THEN 'True'
                                           ELSE 'False'
                                         END )
  WHEN 'PrivilegeProductForCustomerEducationImport' THEN ( CASE value
                                                             WHEN
'<prd id of privilege product>' THEN 'True'
                                                             ELSE 'False'
                                                           END )
  WHEN 'SalesLocationForCustomerEducationImport' THEN ( CASE value
                                                          WHEN
'<store id of sales location>' THEN 'True'
                                                          ELSE 'False'
                                                        END )
  WHEN 'LandownerDeclaration' THEN ( CASE value
                                       WHEN '<Declaration Text>' THEN 'True'
                                       ELSE 'False'
                                     END )
  WHEN 'WildlifeProductSalesTaxRevenueAccounts' THEN (
                                                       CASE value
                                                         WHEN
'<pipe delimited list of revenue account code(s) for tax revenue account>' THEN
  'True'
  ELSE 'False'
END )
  WHEN 'ProductsCableRestraintTrained' THEN ( CASE value
                                                WHEN
'<comma delimited list of product codes for Cable Restraint Trained>'
                                              THEN 'True'
                                                ELSE 'False'
                                              END )
  WHEN 'import.retention.days.customeridentifier' THEN ( CASE value
                                                           WHEN
'<number of days to retain file>' THEN 'True'
                                                           ELSE 'False'
                                                         END )
  WHEN 'DefaultVerifoneCustomerClassId' THEN ( CASE value
                                                 WHEN '1' THEN 'True'
                                                 ELSE 'False'
                                               END )
  WHEN 'DefaultVerifoneVehicleTypeId' THEN ( CASE value
  WHEN '<vehicle type id>' THEN 'True'
  ELSE 'False'
                                             END )
  WHEN 'VerifoneSupplyOrderCode' THEN ( CASE value
                                          WHEN '900' THEN 'True'
                                          ELSE 'False'
                                        END )
  WHEN 'VerifoneHipProductCode' THEN ( CASE value
                                         WHEN '052' THEN 'True'
                                         ELSE 'False'
                                       END )
  WHEN 'VerifoneHunterEdTypeID' THEN ( CASE value
                                         WHEN '1' THEN 'True'
                                         ELSE 'False'
                                       END )
  WHEN 'VerifoneAutoReverseReason' THEN ( CASE value
                                            WHEN '16' THEN 'True'
                                            ELSE 'False'
                                          END )
  WHEN 'VerifoneUnconfirmedAutoReverseReason' THEN ( CASE value
                                                       WHEN '28' THEN 'True'
                                                       ELSE 'False'
                                                     END )
  WHEN 'DefaultVerifoneInspectionReasonIds' THEN ( CASE value
                                                     WHEN
'<inspection reason ids>' THEN 'True'
                                                     ELSE 'False'
                                                   END )
  WHEN 'VerifoneUnconfirmedTimeoutMinutes' THEN ( CASE value
                                                    WHEN '15' THEN 'True'
                                                    ELSE 'False'
                                                  END )
  WHEN 'VerifoneMaxLineLengths' THEN ( CASE value
                                         WHEN '21,18' THEN 'True'
                                         ELSE 'False'
                                       END )
  WHEN 'VerifoneProcessingType' THEN ( CASE value
                                         WHEN '1' THEN 'True'
                                         ELSE 'False'
                                       END )
  WHEN 'VerifonePiggybackSupport' THEN ( CASE value
                                           WHEN '2' THEN 'True'
                                           ELSE 'False'
                                         END )
  WHEN 'VerifoneATPSupport' THEN ( CASE value
                                     WHEN '1' THEN 'True'
                                     ELSE 'False'
                                   END )
  WHEN 'VerifoneDuplicateRequestHandling' THEN ( CASE value
                                                   WHEN '2' THEN 'True'
                                                   ELSE 'False'
                                                 END )
END AS configtest
 FROM   x_prop)
WHERE  configtest in('True' ,'False')
ORDER  BY 
          name,configtest desc  ;
          
          
         