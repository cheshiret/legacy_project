package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.apiclient.util.AwoCryptoUtil;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationType;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.OutfitterAllocation;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.lottery.PointTypesAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.CleanUpSwitch;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTPaymentAllocationRecord;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FinancialConfig;
import com.activenetwork.qa.awo.datacollection.legacy.orms.HarvestReportQuestionnaires;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BadgeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CarrierInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentFulfillmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentUploadInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EFTAdjustmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InterstateContactInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaTransfer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LotteryExecutionConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.OfficerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleTypeManufacturer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsLogoutWarningPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinLocationPage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsFinSessionSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.license.OrmsRequestHFConfirmLetterPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsEnterPinNumPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsRequestReportPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPrintDocumentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddBondIssuerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddCertificationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddDocumentUploadPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddDocumentUploadTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddSuspensionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuersChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmEducationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAddNoteAlertPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAddPointsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHistoricalOrdersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerInputDateOfBirthWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeOptionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPointsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPurchaseAuthorizationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerUnlockedPrivilegesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerVehiclesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrDeactiveIdentifierWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrDocumentUploadDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrDocumentUploadHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrDonationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditCertificationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditIdentifierPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditSuspensionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrHarvestQuestionsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIncludePrdRevenueSummaryConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCandidatesList;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCustomerDetails;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrPrivilegePurchaseAuthorizationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRefundWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRemoveIdentifierDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRemoveIndentifierConfirmationDialog;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrResidencyStatusSelectionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrSelectCustomerClassWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrSelectTransactionReasonWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrViewEducationDeferralRecordsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrWarehouseInventoryListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityRegistrationOrderDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityRegistrationOrdersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityRegistrationSalePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddActivityPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddActivityProductGroupWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddFacilityProductTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddFacilityProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddInstructorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddInstructorTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddInstructorWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddNewBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddNewFacilityWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddressesContactsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrBusinessRuleListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrEditFacilityProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityAttributesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityPrdChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorActivityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrNewFacilitySetupPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddNoteAlertWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAlertPopupWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCustomerMgrTabPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEndCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrFileImportsListCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAlertDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivAllocationsCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductTaxWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrStoreDaliySalesActivityCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrAddProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrProductAssignedStoresWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrProductStoreWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationDocumentTemplateDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.LicMgrProductConfigurationQuestionsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddDocTemplateWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddQuestionsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSeasonWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddWeaponWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDocumentTemplatesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDocumentTemplatesdetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrEditSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrProductQuestionsConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSeasonsConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrWeaponsConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrAddEFTScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrAddLienCompanyWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrAddVendorStatusReasonWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrBondIssuersConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrEFTSchedulesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompaniesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrLienCompanyDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrVendorStatusReasonMgtConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.LicMgrCustomerLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAddPropertyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnerDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment.LicMgrDocFulfillInventoryDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment.LicMgrDocFulfillPrintWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment.LicMgrDocFulfillRetryPrintWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment.LicMgrDocFulfillmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrRAFeeScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrRAFeeScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrSelectFeeTypePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrImportConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrImportFileInfoWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrImportFilePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrPointImportsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrUnlockedPriImportsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrAddHarvestQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHarvestQuestionsDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.harvestquestions.LicMgrHavestQuestionsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrAddHIPReportingScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrEditHIPReportingScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrHIPReportingCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrHIPReportingJobsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrHIPReportingSchedulesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.interstatecontact.LicMgrInterstateContactDetailWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.interstatecontact.LicMgrInterstateContactListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrAddCountyLicenseYearQtyWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrEditLandownerCountyQtyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerCountyQtyChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.landowner.LicMgrLandownerViewCountyQtyListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAcceptDeclineAwardWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddDatePeriodCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddDatePeriodLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddDatePeriodsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntLocationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntSelectSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddLotteryParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntDatePeriodWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntLocationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntQuotaWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddQuotaPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrApplicationOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrApplicationOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrApplicationOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAssignHuntWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrCreateNewLotteryProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodHuntsAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditDatePeriodLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLocationDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLocationImagePopupPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLocationsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntQuotaListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesCommonPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesProductListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryContractFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigSelectAlgorithmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigSelectWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryParametersListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryQuantityControlDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryQuantityControlsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPointTypeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPointTypesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPrivilegeLotteryManageChoicePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPrivilegeLotterySelectApplicantTypePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrPrivilegeLotterySelectHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingExceptionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingResultsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaHuntsAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaTransferListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaTransferWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrReverseAppOrderRefundWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrReverseApplicationOrderWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrViewHuntAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrAddBadgesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrAddOrChangeBadgeAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrBadgeSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficerBadgesAssignPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficerDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficersAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.officer.LicMgrOfficersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrAdjustPOSProductInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductContractFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewConsumablePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewSupplyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrOrderSuppliesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSubscriberInfoWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesApproveOrderPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesOrdersFulfillPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSuppliesOrdersFulfillSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyInventoryTrackingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyProductContractFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddAllocationTypeLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddAllocationTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddOutfitterAllocationsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationTypeLicYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationTypeListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationsOrdersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrOutfitterAllocationsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrChooseHowToReleaseInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrEditProductQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerDeclarationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAuthorizationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeContractorFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditDisplayOrderPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEducationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryReplaceReasonWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderItemsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeRenewalConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeReturnDocumentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeReturnDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeSelectHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReplacePrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReprintPrivilegeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrRevokeLicenceAwardWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrRevokePrivilegeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectAllocationLicenseWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectFulfillmentMethodWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectPrimaryPrivilegeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrValidFromDateTime;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrVoidUndoVoidPrivilegePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInvTypeLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInventoryTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAllocatePrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAssignUnassignInventoryTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrCreateInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrEditInvTypeLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInvTypeLicenseYearChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrReallocateInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrReinstateInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrAddStoreEFTAdjustmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrOutfitterAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivityCustomerSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivityPaymentTypeSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivityRegisterSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivitySummarySubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivityTransactionSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreActivityUserSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddStatusReasonWidge;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddressesAndContactsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTAdjustmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoiceDetailBreakdownPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoiceDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoiceListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoicePaymentAllocationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoicePaymentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreFinancialConfigPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreInventoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreSelectCountyWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreStatusReasonMgtPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewSoldPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewUnusablePrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicmgrApplyPaymentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxDetialPg;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrAssignRoleLocationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserRolesLocationsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAddMotorWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAddVehicleLienDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAddVehicleLienWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAddVehicleTypeManufacturerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAlertDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCorrectDuplicateVehicleTitleProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleLienDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrInspectionAssignOfficerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrInspectionDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrInspectionVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegisterVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrTitleVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrTransactionGroupsForChildVehiclesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrTransferDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrTransferMotorWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleChangeConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleContractFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleCustomerConfirmPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleInspectionSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienCompanyDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclePricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleReleaseLienWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleAddCoOwnerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDocumentUploadPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleOrderSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehiclePreviousOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleStatusChangeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleViewPreviousCoOwnersWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBankAccountWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBondsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrChangeVendorBankAccountStoreAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrDeactiveVendorBankAccountWarningWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrEFTReportingDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrEditVendorBondsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorApplicationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountStoreAssignmentsDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorChangeStoreBondAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailAddAndContractsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDocumentUploadSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorEFTInvoiceListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewStoreBondAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrViewEFTReportingPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.ConstantsInterpreter;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.ExcelParser;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.verification.CheckIdentifier;
import com.ibm.icu.util.Calendar;

public class LicenseManager extends Orms {
	static LicenseManager _instance = null;

	public static LicenseManager getInstance() {
		if (null == _instance)
			_instance = new LicenseManager();
		return _instance;
	}

	protected LicenseManager() {
	}

	public enum Check_ID implements CheckIdentifier {
		editHuntAssignmentDetails;
	}
	
	public void loginLicenseManager(LoginInfo login) {
		loginLicenseManager(login, true);
	}

	/**
	 * This method executes the work flow of logging into License Manager home
	 * page
	 * 
	 * @param login
	 * @param newBrowser
	 *            ---determine if open a new browser or enter license manager in
	 *            the old browser
	 */
	public void loginLicenseManager(LoginInfo login, boolean newBrowser) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		login(login, "License Manager", newBrowser);
		homePg.waitLoading();
	}

	/**
	 * Delete all Print Document records owned by product by product oder
	 * 
	 * @param pCode
	 * @param type
	 *            privilege or Vehicle
	 * @param parkName
	 * @param schema
	 */
	public void deleteAllProductDocFormDBForOneProduct(String pCode,
			String type, String parkName, String schema) {
		String proCatId;

		logger.info("Delete all Document records owned by product which code="
				+ pCode);
		if (type.equalsIgnoreCase("privilege")) {
			proCatId = "10";
		} else if (type.equalsIgnoreCase("Vehicle")) {
			proCatId = "11";
		} else {
			throw new ErrorOnDataException("Unknown Product Type:" + type);
		}
		String sql = "update P_PRD_FULFILLMENT_DOC set ACTIVE_IND=0,DELETED_IND=1 "
				+ "WHERE PRD_ID= (SELECT PRD_ID FROM P_PRD WHERE PRODUCT_CAT_ID="
				+ proCatId
				+ " AND PRD_CD='"
				+ pCode
				+ "' and LOC_ID=(select id from D_LOC where name = '"
				+ parkName + "'))";
		logger.info(sql);
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	/**
	 * Delete all Print Document records owned by product by product oder
	 * 
	 * @param pCode
	 * @param type
	 *            privilege or Vehicle
	 * @param parkName
	 * @param schema
	 */
	public void deleteAllQuestionsFormDBForOneProduct(String pCode,
			String type, String parkName, String schema) {
		String proCatId;

		logger.info("Delete all Document records owned by product which code="
				+ pCode);
		if (type.equalsIgnoreCase("privilege")) {
			proCatId = "10";
		} else if (type.equalsIgnoreCase("Vehicle")) {
			proCatId = "11";
		} else if(type.equalsIgnoreCase("Consumable")) {
			proCatId = "4";
		} else {
			throw new ErrorOnDataException("Unknown Product Type:" + type);
		}
		String sql = "update P_Prd_question set ACTIVE_IND=0,DELETED_IND=1 "
				+ "WHERE PRD_ID= (SELECT PRD_ID FROM P_PRD WHERE PRODUCT_CAT_ID="
				+ proCatId + " AND PRD_CD='" + pCode
				+ "' and LOC_ID=(select id from D_LOC where name like '"
				+ parkName + "'))";
		logger.info(sql);
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	public List<String> getPrintDocumentsIdsFromDb(LicMgrDocumentInfo document,
			String parkName, String schema, boolean active_ind) {

		logger
				.info("Select all document ids by Print_order,ParkName,ProductCode,Product Type,EFFECTIVE_FROM...");

		String proType;
		if (document.prodType.equalsIgnoreCase("privilege")) {
			proType = "10";
		} else if (document.prodType.equalsIgnoreCase("vehicle")) {
			proType = "11";
		} else {
			throw new ErrorOnDataException("UNknown Product Type:"
					+ document.prodType);
		}
		String selectSql = "select ID from P_PRD_FULFILLMENT_DOC "
				+ "where "
				+ "PRINT_ORDER="
				+ document.printOrd
				+ " and PRD_ID = (select PRD_ID from P_PRD where LOC_ID=(select id from D_LOC where name like '"
				+ parkName
				+ "') and PRODUCT_CAT_ID="
				+ proType
				+ " and PRD_CD='"
				+ document.prodCode
				+ "') "
				+ "and ACTIVE_IND="
				+ (active_ind ? "0 " : "1 ")
				+ (document.effectiveFromDate.trim().length() > 0 ? (" and EFFECTIVE_FROM=TO_DATE('"
						+ document.effectiveFromDate + "','MM/dd/yyyy') ")
						: "")
				+ (document.effectiveToDate.trim().length() > 0 ? "and EFFECTIVE_TO = TO_DATE('"
						+ document.effectiveToDate + "','MM/dd/yyyy')"
						: "")
				+ "and DOC_TEMPLATE_ID=(select id from p_document_template where DOC_TEMPLATE_NAME = '"
				+ document.docTepl.trim() + "')";

		db.resetSchema(schema);
		return db.executeQuery(selectSql, "ID");
	}

	/**
	 * deactivate the document which
	 * 
	 * @param schema
	 * @return
	 */
	public void activateOrInactivateTheDocument(LicMgrDocumentInfo document,
			String parkName, String schema, boolean active_ind) {
		List<String> ids = this.getPrintDocumentsIdsFromDb(document, parkName,
				schema, active_ind);
		String idsString = "";
		if (ids.size() > 0) {

			for (int i = 0; i < ids.size(); i++) {
				idsString += (ids.get(i) + (i == ids.size() - 1 ? "" : ","));
			}
			logger.info(active_ind ? "Activate " : "Deactivate "
					+ "Document which id is " + idsString);

			String updateSql = "update P_PRD_FULFILLMENT_DOC set ACTIVE_IND= "
					+ (active_ind ? "1 " : "0 ") + "where ID in("
					+ ids.toString().replaceAll("(\\[|\\])", "") + ")";

			db.resetSchema(schema);
			db.executeUpdate(updateSql);
		}
	}

	/**
	 * delete quantity Control records from db
	 * 
	 * @param privilegeCode
	 * @param schema
	 */
	public void deleteQuantityControlsFromDB(String privilegeCode, String schema) {
		String sql = "delete from P_PRD_LOC_CLASS_CONFIG "
				+ "where PRIVILEGE_PRD_ID=(select PRD_ID from P_PRD where PRD_CD='"
				+ privilegeCode + "' and PRODUCT_CAT_ID=10)";
		logger
				.info("delete quantity Control records from db which privilege code="
						+ privilegeCode);
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	/**
	 * Get rev_loc_id by product code from db.
	 * 
	 * @param proCOde
	 */
	public String get_REV_LOC_ID_ByProductCode(String proCode, String schema) {
		logger.info("Get rev_loc_id by product code:" + proCode + "from db.");
		String sql = "SELECT REV_LOC_ID FROM P_PRD where PRD_CD='" + proCode
				+ "'";
		db.resetSchema(schema);
		return db.executeQuery(sql, "REV_LOC_ID").get(0);
	}

	/**
	 * query product DOC ID from DB by
	 * productCode,Type(Vehicle,Privilege),ParkName, other optional
	 * conditions:DOC Template,purchase type,equipTyp,LicFrom/To Year Don't add
	 * other optional conditions
	 * 
	 * @param document
	 * @return
	 */
	public List<String> queryProductDOCIdFromDB(LicMgrDocumentInfo document,
			String schema) {
		String proTyp = "";

		String purchaseTyps = "";

		if (document.prodType.equalsIgnoreCase("privilege")) {
			proTyp = "10";
		} else if (document.prodType.equalsIgnoreCase("vehicle")) {
			proTyp = "11";
		} else {
			throw new ErrorOnDataException("Unknown product type");
		}
		if (document.purchaseType.length() > 0) {
			purchaseTyps += LicMgrDocumentInfo.purchaseTypesValueInDb
					.get(document.purchaseType)
					+ " ";
		}
		for (String purchaseTyp : document.purchaseTypes) {
			purchaseTyps += LicMgrDocumentInfo.purchaseTypesValueInDb
					.get(purchaseTyp)
					+ " ";
		}
		purchaseTyps.trim().replaceAll(" ", ",");
		List<String> list = new ArrayList<String>();
		String sql = "SELECT Id "
				+ "FROM P_PRD_FULFILLMENT_DOC "
				+ " WHERE PRD_ID=(SELECT PRD_ID FROM P_PRD WHERE PRODUCT_CAT_ID="
				+ proTyp
				+ " and  PRD_CD='"
				+ document.prodCode
				+ "' and  LOC_ID = (select ID from D_LOC where name like '"
				+ document.location
				+ "'))"
				+ "and DELETED_IND !=1 And ACTIVE_IND="
				+ (document.status.trim().length() > 0
						&& document.status.equalsIgnoreCase("Active") ? "1"
						: "0")
				+ (document.docTepl.trim().length() > 0 ? (" AND DOC_TEMPLATE_ID= "
						+ " (SELECT ID FROM P_DOCUMENT_TEMPLATE WHERE DOC_TEMPLATE_NAME='"
						+ document.docTepl + "')")
						: "")
				+ ((purchaseTyps.length() > 0) ? (" AND PURCHASE_TYPE_ID in ("
						+ purchaseTyps + ")") : "")
				+ ((document.equipType.trim().length() > 0)
						&& !document.equipType.equals("All") ? (" AND EQUIP_TYPE_ID =( select ID from D_EQUIPMENT_TYPE where NAME='"
						+ document.equipType + "')")
						: "")
				+ ((document.licYearFrom.trim().length() > 0)
						&& (!document.licYearFrom.equals("All")) ? (" AND LF_YEAR_FROM=" + document.licYearFrom)
						: "");
		logger.info("query product DOC ID from DB.");
		db.resetSchema(schema);
		list.addAll(db.executeQuery(sql, "ID"));
		return list;
	}

	/**
	 * get print document information from 'LicMgrEditPrintDocumentWidget'. Work
	 * flow start from: Product Print Documents sub-tab End in:Product Print
	 * Documents sub-tab
	 * 
	 * @param document
	 * @return
	 */
	public LicMgrDocumentInfo getPrintDocumentInfoFromEditPrintDocuemntWidget(
			String docId) {
		ILicMgrProductPrintDocumentPage printDocSubTab = LicMgrVehiclePrintDocumentsPage
				.getInstance();
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();
		LicMgrDocumentInfo document = new LicMgrDocumentInfo();

		this.searchPrintDocumentsInProductSubTab("", "", "", false);
		printDocSubTab.clickPrintDocumentID(docId);
		ajax.waitLoading();
		editWidget.waitLoading();
		document = editWidget.getDocmentInfo();
		editWidget.clickCancel();
		ajax.waitLoading();
		printDocSubTab.waitLoading();
		return document;
	}

	/**
	 * Get document template queried by product code and purchase type
	 * 
	 * @param schema
	 * @param prodCode
	 * @param purchaseType
	 *            : Original-1; Replacement-2; Transfer-3; Renewal-4;
	 *            Corrected-6
	 * @return
	 */
	public String getProductDocumentTemplate(String schema, String prodCode,
			String purchaseType) {
		logger.info("Get product(Code#=" + prodCode
				+ ") document template for purchase type-" + purchaseType);

		String sql = "select pdt.DOC_TEMPLATE_NAME as DOCUMENT from P_PRD pp, P_DOCUMENT_TEMPLATE pdt, P_PRD_FULFILLMENT_DOC ppfd where pp.PRD_ID=ppfd.PRD_ID "
				+ "and pp.PRD_CD='"
				+ prodCode
				+ "' and pdt.id=ppfd.DOC_TEMPLATE_ID and ppfd.PURCHASE_TYPE_ID="
				+ purchaseType;
		db.resetSchema(schema);
		logger.info("Execute SQL - " + sql);
		List<String> result = db.executeQuery(sql, "DOCUMENT");
		if (result.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find any Document Template record.");
		}

		return result.get(0);
	}

	/**
	 * Go to privilege search list from top menu
	 */
	public void gotoPrivilegeSearchListPageFromTopMenu() {
		this.gotoProductSearchListPageFromTopMenu("Privileges");
	}

	/**
	 * Go to vehicle search list page from top menu
	 */
	public void gotoVehicleSearchListPageFromTopMenu() {
		this.gotoProductSearchListPageFromTopMenu("Vehicles");
	}

	/**
	 * Go to consumable search list page from top menu
	 */
	public void gotoConsumableSearchListPageFromTopMenu() {
		this.gotoProductSearchListPageFromTopMenu("Consumables");
	}

	/**
	 * Goto Batch Add License Year page from top menu, starting from top menu
	 * ends at privileges license year batch add page.
	 */
	public void gotoPrivilegeLicenseYearBatchAddPageFromTopMenu() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeLicenseYearBatchAddPage batchAddPage = LicMgrPrivilegeLicenseYearBatchAddPage
				.getInstance();

		this.gotoProductSearchListPageFromTopMenu("Privileges");
		privilegeListPage.clickBatchAddLicYearButton();
		batchAddPage.waitLoading();
	}

	/**
	 * Goto Batch Add license year page from privilege search list page.
	 */
	public void gotoPrivilegeLicenseYearBatchAddPageFromPrivilegeSchPage() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeLicenseYearBatchAddPage batchAddPage = LicMgrPrivilegeLicenseYearBatchAddPage
				.getInstance();

		privilegeListPage.clickBatchAddLicYearButton();
		batchAddPage.waitLoading();
	}

	/*
	 * go to product search list page from top
	 */
	public LicMgrProductPage gotoPrivilegeListPageFromTopMenu() {
		return this.gotoProductSearchListPageFromTopMenu("Privilege");
	}

	/**
	 * go to LM Product page from top drop down list.Work flow start from top
	 * drop down list End:the page which specified products are listed.
	 * 
	 * @param product
	 *            in-sensitive on case and (Singular or plural ) for
	 *            Privileges(privilege
	 *            (s),Privilege,)/Consumables(consumable,Consumable
	 *            )/Vehicles/Harvest Questions/Supplies
	 * @Return LicMgrProductPage
	 * @Throws
	 */
	public LicMgrProductPage gotoProductSearchListPageFromTopMenu(String product) {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrProductPage productPage = LicMgrProductPage.getInstance();
		String adminValue = topMenu.getAdminValue();

		logger.info("Go to product list page from LM top menu.");
		if (!productPage.exists()) {
			if (!adminValue.equals("Products")) {
				topMenu.selectAdminOptions("Products");
			} else {
				topMenu.clickAdmin();
			}
			ajax.waitLoading();
			productPage.waitLoading();
		}

		if (product.matches("(?i)Privilege(s)?")) {
			LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
					.getInstance();
			if (!privilegeListPage.exists()) {
				productPage.clickPrivilegesTab();
				ajax.waitLoading();
				privilegeListPage.waitLoading();
			}
			return privilegeListPage;
		} else if (product.matches("(?i)Consumable(s)?")) {
			LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage
					.getInstance();
			productPage.clickConsumablesTab();
			ajax.waitLoading();
			consumableListPage.waitLoading();
			return consumableListPage;
		} else if (product.matches("(?i)Vehicle(s)?")) {
			LicMgrVehiclesListPage vehicleList = LicMgrVehiclesListPage
					.getInstance();
			productPage.clickVehiclesTab();
			ajax.waitLoading();
			vehicleList.waitLoading();
			return vehicleList;
		} else if (product.matches("(?i)Harvest Question(s)?")) {
			LicMgrHarvestQuestionsListPage hQuestionPage = LicMgrHarvestQuestionsListPage
					.getInstance();
			productPage.clickHarvestQuestionsTab();
			ajax.waitLoading();
			hQuestionPage.waitLoading();
			return hQuestionPage;
		} else if (product.matches("(?i)Suppl(ies|y)")) {
			LicMgrSuppliesListPage supplisetPage = LicMgrSuppliesListPage
					.getInstance();
			productPage.clickSuppliesTab();
			ajax.waitLoading();
			supplisetPage.waitLoading();
			return supplisetPage;
		} else {
			throw new ErrorOnDataException("Unknown product type.");
		}
	}

	public void gotoProductListFromProductDetailPage() {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrSupplyProductDetailsPage supplyDetailPage = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrEditVehicleDetailsPage vehicleDetailPage = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrConsumableProductDetailsPage consumableDetailPage = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrProductPage productPage = LicMgrProductPage.getInstance();

		Object page = browser.waitExists(privilegeDetailPage, supplyDetailPage,
				vehicleDetailPage, consumableDetailPage, productPage);

		logger.info("Go to product list page from product detail page");

		if (page != productPage) {
			if (page == privilegeDetailPage) {
				privilegeDetailPage.clickCancel();
				ajax.waitLoading();
			} else if (page == supplyDetailPage) {
				supplyDetailPage.clickCancel();
				ajax.waitLoading();
			} else if (page == vehicleDetailPage) {
				vehicleDetailPage.clickCancel();
				ajax.waitLoading();
			} else if (page == consumableDetailPage) {
				consumableDetailPage.clickCancel();
				ajax.waitLoading();
			} else {
				throw new ErrorOnPageException("Unkown Page");
			}
			productPage.waitLoading();
		}
	}

	/**
	 * go to vehicle product details page from any page you can find top admin
	 * drop down list
	 * 
	 * @param vehicleCode
	 * @Return void
	 */
	public void gotoVehicleProductDetailsPage(String vehicleCode) {
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();

		gotoProductSearchListPageFromTopMenu("Vehicles");
		listPage.clickVehicleCode(vehicleCode);
		LicMgrEditVehicleDetailsPage.getInstance().waitLoading();
	}

	/**
	 * search vehicle product by criterias in Vehicle product list page
	 * 
	 * @param statuses
	 * @param groups
	 * @param types
	 * @Return void
	 * @Throws
	 */
	public void searchVehicleProduct(Map<String, Boolean> statuses,
			Map<String, Boolean> groups, Map<String, Boolean> types) {
		LicMgrVehiclesListPage page = LicMgrVehiclesListPage.getInstance();

		logger.info("Search vehicle product");
		page.selectVehicleStatus(statuses);
		page.selectVehicleGroup(groups);
		page.selectVehicleType(types);
		page.clickGo();
		page.waitLoading();
	}

	/**
	 * search vehicle product by criterias in Vehicle product list page, and no
	 * results returned
	 * 
	 * @author pzhu
	 * @param statuses
	 * @param groups
	 * @param types
	 * @Return void
	 * @Throws
	 */
	public void searchVehicleProductWithNoResults(
			Map<String, Boolean> statuses, Map<String, Boolean> groups,
			Map<String, Boolean> types) {
		LicMgrVehiclesListPage page = LicMgrVehiclesListPage.getInstance();

		logger.info("Search vehicle product");
		page.selectVehicleStatus(statuses);
		page.selectVehicleGroup(groups);
		page.selectVehicleType(types);
		page.clickGo();
		logger.info("--->> clicked Go");
		ajax.waitLoading();
	}

	/**
	 * This executes the work flow of search privilege question by criteria on
	 * privilege question page
	 * 
	 * @param question
	 */

	public void searchProductQustionByCriteria(QuestionInfo question) {
		LicMgrPrivilegeQuestionPage privilegequestionPage = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();
		LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage
				.getInstance();

		logger.info("Search privileage question by criteria.");
		if (privilegequestionPage.exists()) {
			privilegequestionPage.setSearchCriteria(question);
			privilegequestionPage.clickGo();
			ajax.waitLoading();
			privilegequestionPage.waitLoading();
		} else if (consumableQuestionPg.exists()) {
			consumableQuestionPg.setSearchCriteria(question);
			consumableQuestionPg.clickGo();
			consumableQuestionPg.waitLoading();
		} else if (supplyQuestionPg.exists()) {
			supplyQuestionPg.setSearchCriteria(question);
			supplyQuestionPg.clickGo();
			ajax.waitLoading();
			supplyQuestionPg.waitLoading();
		}

	}

	/**
	 * The method executes the work flow of logout license manger from license
	 * manger home page to ORMS home page
	 * 
	 * @Return void
	 * @Throws
	 */
	public void logOutLicenseManager() {
		OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsLogoutWarningPage sessionPage = OrmsLogoutWarningPage.getInstance();

		logger.info("Logout License Manager");

		if (!topMenuPg.exists()) {
			gotoHomePage();
			topMenuPg.waitLoading();
		}
		topMenuPg.clickSignOut();
		ajax.waitLoading();
		Object page = browser.waitExists(ormsHmPg, sessionPage);
		if (page == sessionPage) {
			sessionPage.clickLeaveFinSesOpenAndSignOut();
			ajax.waitLoading();
		}
		browser.waitExists(ormsHmPg);
	}

	public String addReportCategory(PrivilegeReportCategory reportCategory) {
		return this.addReportCategory(reportCategory, false);
	}

	/**
	 * Add report category
	 * 
	 * @param reportCategory
	 * @param isCancel
	 * @return
	 */
	public String addReportCategory(PrivilegeReportCategory reportCategory,
			boolean isCancel) {
		LicMgrReportCategoriesConfigurationPage reportCategoryConfigPg = LicMgrReportCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget
				.getInstance();
		String value = "";
		logger.info("add report category on the configuration page...");

		if (!widget.exist()) {
			reportCategoryConfigPg.clickAdd();
			ajax.waitLoading();
			widget.waitLoading();
		}
		widget.setReportCategoryInfo(reportCategory.description,
				reportCategory.displayOrder, reportCategory.groupNum);

		if (isCancel) {
			widget.clickCancel();
		} else {
			widget.clickOK();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(widget, reportCategoryConfigPg);
		if (page == reportCategoryConfigPg) {
			value = reportCategoryConfigPg
					.getReportCategoryIdByDescription(reportCategory.description);
		} else if (page == widget) {
			value = widget.getErrorMsg();
		}

		return value;
	}

	/**
	 * Create a new consumable product from select 'Products' option from
	 * 'Admin' drop down list in top menu, and then go to
	 * LicMgrConsumableListPage to click 'Add POS Product' button to go to
	 * LicMgrCreateNewConsumablePage to set consumable details info and finally
	 * click 'Apply' button
	 * 
	 * @param consumable
	 * @return - new created consumable product id
	 */
	public String createNewConsumableProduct(ConsumableInfo consumable) {
		LicMgrConsumableListPage consumableListPg = LicMgrConsumableListPage
				.getInstance();
		LicMgrCreateNewConsumablePage createNewConsumablesPg = LicMgrCreateNewConsumablePage
				.getInstance();
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();

		logger.info("Create a new consumable-" + consumable.orderType
				+ " product.");
		if (!createNewConsumablesPg.exists()) {
			gotoConsumableSearchListPageFromTopMenu();
			consumableListPg.clickAddPOSProduct();
			createNewConsumablesPg.waitLoading();
		}
		createNewConsumablesPg.setupConsumableProductInfo(consumable);
		createNewConsumablesPg.clickApply();
		consumableDetailsPg.waitLoading();
		String id = consumableDetailsPg.getProductID();

		return id;
	}

	/**
	 * add vehicle product.work flow start from LicMgrVehiclesListPage,End in
	 * LicMgrVehiclesListPage or LicMgrCreateNewVehiclePage
	 * 
	 * @param vehicle
	 * @Return void
	 */
	public String addVehicleProduct(VehicleRTI vehicle) {
		LicMgrCreateNewVehiclePage vehicleInfoPage = LicMgrCreateNewVehiclePage
				.getInstance();
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		String toReturn = "";

		logger.info("set vehicle information in CreateVehicleDetailsPage...");
		if (!vehicleInfoPage.exists()) {
			listPage.clickAddVehicleProduct();
			vehicleInfoPage.waitLoading();
		}
		vehicleInfoPage.setVehicleInfo(vehicle);
		vehicleInfoPage.clickApply();

		Object page = browser.waitExists(vehicleInfoPage, vehicleDetailPg);
		if (page == vehicleInfoPage) {
			toReturn = vehicleInfoPage.getErrorMsg();
		}
		if (page == vehicleDetailPg) {
			toReturn = vehicleDetailPg.getProductID();
			vehicleDetailPg.clickOk();
			listPage.waitLoading();
		}
		return toReturn;
	}

	/**
	 * Add consumable product, start from consumable list page, end at
	 * consumable list page or add POS page
	 * 
	 * @param consumable
	 */
	public void addConsumableProduct(ConsumableInfo consumable) {
		LicMgrCreateNewConsumablePage createPOSPg = LicMgrCreateNewConsumablePage
				.getInstance();
		LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage
				.getInstance();

		logger.info("Add consumable product.");
		if (!createPOSPg.exists()) {
			consumableListPage.clickAddPOSProduct();
			createPOSPg.waitLoading();
		}
		createPOSPg.setupConsumableProductInfo(consumable);
		createPOSPg.clickOK();
		browser.waitExists(createPOSPg, consumableListPage);
	}

	/**
	 * edit vehicle product infomation, work flow start from
	 * VehicleProductListPage,End in VehicleProductListPage
	 * 
	 * @param vehicle
	 * @Return void
	 */
	public void editVehicleProduct(VehicleRTI vehicle) {
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();
		LicMgrEditVehicleDetailsPage editPage = LicMgrEditVehicleDetailsPage
				.getInstance();

		logger.info("Edit vehicle product(Code#=" + vehicle.getPrdCode()
				+ ") info.");
		if (!editPage.exists()) {
			listPage.clickVehicleCode(vehicle.getPrdCode());
			editPage.waitLoading();
		}
		editPage.setVehicleInfo(vehicle);
		editPage.clickOk();
		browser.waitExists(editPage, listPage);
	}

	public void changePrivilegeProductStatus(String code, String toStatus) {
		LicMgrPrivilegesListPage privListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privDetailPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info("Change privilege(CD#=" + code + ") status to " + toStatus);
		privListPage.clickPrivilegeCode(code);
		ajax.waitLoading();
		privDetailPage.waitLoading();
		privDetailPage.selectPrivilegeStatus(toStatus);
		privDetailPage.clickOk();
		privListPage.waitLoading();
	}

	/**
	 * This method is used to change vehicle status, from vehicle detail page,
	 * end at vehicle list page
	 * 
	 * @param status
	 */
	public void changeVehicleProductStatus(String status) {
		LicMgrEditVehicleDetailsPage vehicleDetailPage = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();

		logger.info("Change vehicle status to " + status);
		vehicleDetailPage.selectStatus(status);
		vehicleDetailPage.clickOk();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	/**
	 * Switch from current Location to the target Location,and end in Home Page
	 * 
	 * @param location
	 * @Return void
	 * @Throws
	 */
	public void switchLocationInHomePage(String location) {
		LicMgrTopMenuPage topPage = LicMgrTopMenuPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		logger.info("Switch location to " + location + " Home Page");
		this.gotoHomePage();
		topPage.selectLocation(location);
		ajax.waitLoading();
		homePg.waitLoading();
	}

	/**
	 * change bussiness rule status in Privilege Bussiness Rule Tab
	 * 
	 * @param bRuleId
	 * @param isActive
	 * @Return void
	 * @Throws
	 */
	public void changeBussinessRuleStatus(String bRuleId, boolean isActive) {
		LicMgrPrivilegeEditRuleWidget editRuleWidget = LicMgrPrivilegeEditRuleWidget
				.getInstance();
		LicMgrPrivilegeBusinessRulePage bRulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		String status = isActive ? "Active" : "Inactive";

		logger
				.info("change BussinessRule status in Privilege Bussiness Rule Tab");

		if (!bRulePage.exists()) {
			bRulePage.clickBusinessRuleTab();
			ajax.waitLoading();
			bRulePage.waitLoading();
		}

		if (isActive) {
			bRulePage.unSelectActiveCheckBox();
			bRulePage.selectInactiveCheckBox();
		} else {
			bRulePage.unSelectInactiveCheckBox();
			bRulePage.selectActiveCheckBox();
		}
		bRulePage.clickGo();
		ajax.waitLoading();
		bRulePage.clickRuleId(bRuleId);
		editRuleWidget.waitLoading();
		editRuleWidget.selectStatus(status);
		editRuleWidget.clickOK();
		ajax.waitLoading();
	}

	/**
	 * judge the privilege is existing according to the criterias in Privilege
	 * page Workflow Start from:Home Page End:Privileges Page
	 * 
	 * @param code
	 * @param criterias
	 * @return
	 */
	public boolean isThisPriviledgeExist(String code, String... criterias) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		boolean flag = false;

		logger.info("judge the privilege is existing.......");
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		if (homePg.getAdminValue().equals("Products")) {
			homePg.clickAdmin();
		} else {
			homePg.selectAdminOptions("Products");
		}
		privilegePage.waitLoading();
		searchPrivilegesInPrivilegesPage(criterias);
		flag = privilegePage.isThisPrivilegeExist(code);
		return flag;
	}

	/**
	 * search privilege in privileges page.
	 * 
	 * @param criterias
	 */
	public void searchPrivilegesInPrivilegesPage(String... criterias) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();

		logger.info("search privilege in privileges page.");
		if (criterias.length > 0) {
			privilegePage.unSelectAllStatuses();
			privilegePage.unSelectAllDisplayCategories();
			privilegePage.unSelectAllDisplaySubCategories();
			for (String criteria : criterias) {
				privilegePage.clickSearchCriteria(criteria);
			}
		}
		privilegePage.clickGo();
		ajax.waitLoading();
	}

	/**
	 * 
	 * click 'validate' button to validate status
	 * 
	 * @param status
	 * @param page
	 * @Return void
	 * @Throws
	 */
	public void validateStatus(OrmsPage page, String... status) {
		LicMgrNewCustomerPage newCustPage = null;
		List<String> statusFromPage = new ArrayList<String>();
		String msg = null;

		logger.info("verify Status.....");

		if (page instanceof LicMgrNewCustomerPage) {
			newCustPage = (LicMgrNewCustomerPage) page;
			if (newCustPage.checkMailValidateExist()) {
				newCustPage.clickMailingValidate();
				ajax.waitLoading();
				statusFromPage.add(newCustPage.getMailStatus());
			}

			if (newCustPage.checkPhyValidateExist()) {
				newCustPage.clickPhysicalValidate();
				ajax.waitLoading();
				statusFromPage.add(newCustPage.getPhyStatus());
			}
		} else {
			throw new ItemNotFoundException("Unknown Page!");
		}

		for (int i = 0; i < status.length; i++) {
			if (!statusFromPage.get(i).matches(status[i])) {
				msg = "Status :" + status[i] + " is not found \n";
			}
		}

		if (msg != null) {
			throw new ItemNotFoundException(msg);
		}

	}

	/**
	 * Go to New Customer Page from any page which includes a Top menu page
	 * 
	 * @param customer
	 * @Return void
	 * @Throws
	 */
	public void gotoNewCustomerPage(String customerClass) {
		LicMgrTopMenuPage topPg = LicMgrTopMenuPage.getInstance();
		LicMgrCustomersSearchPage customerSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		logger.info("goto new customer page for license.....");

		topPg.clickCustomers();
		ajax.waitLoading();
		customerSearchPg.waitLoading();
		if (customerSearchPg.isLicenseTypeExist(MS_LICENSE_TYPE_MDWFP)) {
			customerSearchPg
			.selectLicenseType(MS_LICENSE_TYPE_MDWFP); // search
																		// by
																		// MDWFP#
		} else if (customerSearchPg.isLicenseTypeExist(IDENT_TYPE_HAL)) { // Lesley[20130925]: update for SK contract customer default type
			customerSearchPg.selectLicenseType(IDENT_TYPE_HAL);
		}
		
		ajax.waitLoading();
		customerSearchPg.setLicenseNum(MS_INVALID_MDWFP_NUM); // set
																				// an
																				// invalid
																				// number
		customerSearchPg.clickSearch();
		ajax.waitLoading();

		this.gotoNewCustomerPageWithoutSearch(customerClass);
	}
	
	/**
	 * Go to New Customer Page without search
	 * @param customerClass
	 * @author Lesley Wang
	 * Apr 16, 2013
	 */
	public void gotoNewCustomerPageWithoutSearch(String customerClass){
		LicMgrSelectCustomerClassWidget custClassWgt = LicMgrSelectCustomerClassWidget.getInstance();
		LicMgrCustomersSearchPage customerSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
		
		logger.info("Go to new customer page without search...");
		customerSearchPg.clickAddCustomer();
		ajax.waitLoading();
		browser.waitExists(custClassWgt, newCustPg);
		if(custClassWgt.exists()){
			custClassWgt.selectCustomerClass(customerClass);
			custClassWgt.clickOK();
			ajax.waitLoading();
			newCustPg.waitLoading();
		}
	}

	/**
	 * edit information for LicMgrNewCustomerPage,CustomerProfilePage,
	 * 
	 * @param cust
	 * @param currPage
	 * @Return void
	 * @Throws
	 */
	public void addOrEditCustomerInfo(Customer cust, OrmsPage currPage) {
		logger.info("Set infomation for "+ currPage.getClass().getSimpleName());

		if (currPage instanceof LicMgrNewCustomerPage) {
			LicMgrNewCustomerPage newCustPg = (LicMgrNewCustomerPage) currPage;
			newCustPg.setCustomerDetails(cust);
		} else if (currPage instanceof LicMgrCustomerAdressesPage) {
			LicMgrCustomerAdressesPage addrPage = (LicMgrCustomerAdressesPage) currPage;
			LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
					.getInstance();

			custDetailPg.clickEdit(); // this is LM new feature
			ajax.waitLoading();
			addrPage.setAddressInfo(cust.physicalAddr, 0);
			if (!cust.mailAddrAsPhy) {
				addrPage.unSelectMaillAddrSameAsPhy();
				ajax.waitLoading();
				addrPage.setAddressInfo(cust.mailingAddr, 1);
			}
			addrPage.setAddressInfo(cust.alternateAddr, 2);
			addrPage.clickSave();
		} else if (currPage instanceof LicMgrCustomerDetailsPage) {
			LicMgrCustomerDetailsPage detailsPage = (LicMgrCustomerDetailsPage) currPage;
			detailsPage.clickEdit(); // this is LM new feature
			ajax.waitLoading();
			detailsPage.setCustomerProfile(cust);
			detailsPage.clickApply();
		} else {
			throw new ErrorOnDataException("Unknown page "
					+ currPage.getClass().getSimpleName());
		}
		ajax.waitLoading();
	}

	/**
	 * Activate,Deactivate or Remove customer identifiers in customer identifier
	 * if 'cancel' = true,the changing will not happen, and a waring message
	 * will be returned if a alert dialog widget pop up; page
	 * 
	 * @param idenID
	 *            identifier ID
	 * @param idType
	 *            Identifier Type
	 * @param idNumber
	 *            Identifier Number
	 * @param option
	 *            Activate,Deactivate or Remove
	 * @param cancel
	 *            if true,the changing will not happen, and a waring message
	 *            will be returned if a alert dialog widget pop up;
	 * @Return warning message
	 */
	String operateIdentifierStatus(String idenID, String idType,
			String idNumber, String option, boolean cancel) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrDeactiveIdentifierWidget deactiveIdentifierPg = LicMgrDeactiveIdentifierWidget
				.getInstance();
		LicMgrRemoveIdentifierDialogWidget removeIdentifierPg = LicMgrRemoveIdentifierDialogWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		String warnMess = "";

		if (null != idenID && idenID.length() > 0) {
			logger.info(option + " a identifier that ID=" + idenID);
			identifierPage.selecRadioButtonViaID(idenID);
		} else {
			logger.info(option + " a identifier that type=" + idType
					+ " and number=" + idNumber);
			identifierPage.selectIdRadioButton(idType, idNumber);
		}

		if (option.equalsIgnoreCase("Activate")) {
			identifierPage.clickActivate();
			Object page = browser.waitExists(confirmDialogWidget,
					identifierPage);
			if (page == identifierPage && identifierPage.checkWarnMes()) {
				warnMess = identifierPage.getWarnMsg();
			} else if (confirmDialogWidget.exists()) {
				warnMess = confirmDialogWidget.getMessage();
				if (cancel) {
					confirmDialogWidget.clickCancel();
				} else {
					confirmDialogWidget.clickOK();
				}
			}
		} else if (option.equalsIgnoreCase("Deactivate")) {
			identifierPage.clickDeactivate();
			if (deactiveIdentifierPg.exists()) {
				warnMess = deactiveIdentifierPg.getMessage();
				if (cancel) {
					deactiveIdentifierPg.clickCancel();
				} else {
					deactiveIdentifierPg.clickOK();
				}
			}
		} else if (option.equalsIgnoreCase("Remove")) {
			identifierPage.clickRemove();
			removeIdentifierPg.waitLoading();
			warnMess = removeIdentifierPg.getMessage();
			if (cancel) {
				removeIdentifierPg.clickCancel();
			} else {
				removeIdentifierPg.clickOK();
			}
		} else {
			throw new ErrorOnDataException(
					"the option should be Activate,Deactivate or Remove !");
		}

		ajax.waitLoading();
		if (identifierPage.checkWarnMes()) {
			identifierPage.getWarnMsg();
		}
		return warnMess;
	}

	public String getWarnMesWhenChangeIdentifierStatus(String idenID,
			String option) {
		return this.operateIdentifierStatus(idenID, "", "", option, true);
	}

	public void changeIdentifierStatus(String idType, String idNum,
			String option) {
		this.operateIdentifierStatus("", idType, idNum, option, false);
	}

	/**
	 * add identifiers for customer in customer identifier page
	 * 
	 * @param List
	 *            <CustomerIdentifier> identifiers
	 * @Return
	 */
	public void addIdentifiersInInditifierPage(
			List<CustomerIdentifier> identifiers) {
		addIdentifiersInInditifierPage(identifiers, null);
	}

	/**
	 * add identifiers for customer in customer identifier page and return
	 * create datetime
	 * 
	 * @param List
	 *            <CustomerIdentifier> identifiers
	 * @Return
	 */
	public List<String> addIdentifiersInInditifierPage(
			List<CustomerIdentifier> identifiers, String schema) {
		List<String> createdDates = new ArrayList<String>();
		for (CustomerIdentifier identifier : identifiers) {
			this.safeAddCustomerIdentifier(identifier);
			if (schema != null)
				createdDates.add(DateFunctions.getToday(DataBaseFunctions
						.getContractTimeZone(schema)));
		}

		return createdDates;
	}

	public String updateCustomerIdentifier(CustomerIdentifier identifier) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrEditIdentifierPage editIdentifierWidget = LicMgrEditIdentifierPage
				.getInstance();
		LicMgrCustomerInputDateOfBirthWidget inputDobPage = LicMgrCustomerInputDateOfBirthWidget
				.getInstance();

		logger.info("Update customer identifier(ID=" + identifier.id + "Type="
				+ identifier.identifierType + "Num=" + identifier.identifierNum
				+ ").");
		identifierPage.clickIdentifier(identifier.id);
		ajax.waitLoading();
		editIdentifierWidget.waitLoading();
		editIdentifierWidget.setIdentifierNum(identifier.identifierNum);
		editIdentifierWidget.selectState(identifier.state);
		editIdentifierWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(inputDobPage, identifierPage);
		String toReturn = "";
		if (page == inputDobPage) {
			toReturn = inputDobPage.getMessage();
		}
		if (page == identifierPage) {
			toReturn = identifierPage.getIdentifierID(
					identifier.identifierType, identifier.identifierNum);
		}

		return toReturn;
	}

	String addCustomerIdentifier(CustomerIdentifier identifier,
			boolean safeAdd) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrAddIdentifiersPage addIdentifier = LicMgrAddIdentifiersPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Add Customer Identifier " + identifier.identifierType
				+ " = " + identifier.identifierNum);

		if (safeAdd
				&& identifierPage.checkIdentifierExistByTypeAndNumber(
						identifier.identifierType, identifier.identifierNum)) {
			this.removeCustIdentifier(identifier.identifierType,
					identifier.identifierNum);
		}

		identifierPage.clickAddIdentifier();
		ajax.waitLoading();
		addIdentifier.waitLoading();
		addIdentifier.setIdentifier(identifier);
		addIdentifier.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, identifierPage);
		if (page == confirmDialogWidget) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			identifierPage.waitLoading();
		}
		return identifierPage.getIdentifierID(identifier.identifierType,
				identifier.identifierNum);
	}

	public String addCustomerIdentifier(CustomerIdentifier identifier) {
		return addCustomerIdentifier(identifier, false);
	}

	public String safeAddCustomerIdentifier(CustomerIdentifier identifier) {
		return addCustomerIdentifier(identifier, true);
	}

	public void removeCustIdentifier(String identifierType, String identifierNum) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrRemoveIndentifierConfirmationDialog confirmDialogWidget = LicMgrRemoveIndentifierConfirmationDialog
				.getInstance();

		logger.info("Remove Customer Identifier " + identifierType + " = "
				+ identifierNum);

		identifierPage.selectIdRadioButton(identifierType, identifierNum);
		identifierPage.clickRemove();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
		confirmDialogWidget.clickOK();
		ajax.waitLoading();

		identifierPage.waitLoading();
	}
	
	/**
	 * Add customer education record in customer education sub page
	 * 
	 * @param education
	 * @return
	 */
	public String addCustomerEducation(Education education, boolean isSafeAdd) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrAddEducationPage addEducationPage = LicMgrAddEducationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrCustomerInputDateOfBirthWidget inputDobWidget = LicMgrCustomerInputDateOfBirthWidget
				.getInstance();

		logger.info("Add Customer Education(Type=" + education.educationType
				+ ", Num=" + education.educationNum + ").");
		if (isSafeAdd
				&& educationPage.checkEducationExists(education.educationType,
						education.educationNum)) {
			this.removeCustomerEducation(education.educationType,
					education.educationNum);
		}
		educationPage.clickAddEducation();
		ajax.waitLoading();
		addEducationPage.waitLoading();
		addEducationPage.setEducation(education);
		ajax.waitLoading();
		addEducationPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(inputDobWidget, confirmWidget,
				educationPage);
		String toReturn = "";
		if (page == inputDobWidget) {

		}
		if (page == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			educationPage.waitLoading();
		}
		if (page == educationPage) {
			toReturn = educationPage.getEducationID(education.educationType,
					education.educationNum);
			logger.info("New customer education id is: " + toReturn);
		}

		return toReturn;
	}

	/**
	 * Add customer certification in Add Certification Widget, Work flow start
	 * from:LicMgrCustomerCertificationPage
	 * 
	 * @param certification
	 * @param isClickOK
	 * @return if add action succeed, return new added certification id; if not,
	 *         return error message displayed on the top of add widget
	 */
	public String addCustomerCertification(Certification certification,
			boolean isClickOK) {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrAddCertificationWidget addCertificationWidget = LicMgrAddCertificationWidget
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("Add Customer Certification.");
		if (!custCertificationPg.exists()) {
			detailsPage.clickCertificationsTab();
			ajax.waitLoading();
			custCertificationPg.waitLoading();
		}
		custCertificationPg.clickAddCertification();
		ajax.waitLoading();
		addCertificationWidget.waitLoading();
		addCertificationWidget.setCertification(certification);
		if (isClickOK) {
			addCertificationWidget.clickOK();
		} else {
			addCertificationWidget.clickCancel();
		}
		ajax.waitLoading();
		String toReturn = "";
		Object page = browser.waitExists(addCertificationWidget,
				custCertificationPg);
		if (addCertificationWidget == page) {
			toReturn = addCertificationWidget.getErrorMessage();
		}
		if ((custCertificationPg == page) && isClickOK) {
			toReturn = custCertificationPg.getCertificationID(certification);
			logger.info("---- New added certification id is: " + toReturn);
		}

		return toReturn;
	}

	/**
	 * Remove customer education record identified by type and number
	 * 
	 * @param educationType
	 * @param educationNum
	 */
	public void removeCustomerEducation(String educationType,
			String educationNum) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Remove Customer Education(Type=" + educationType
				+ ", Num=" + educationNum + ").");
		educationPage.selectEducation(educationType, educationNum);
		educationPage.clickRemove();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		educationPage.waitLoading();
	}

	/**
	 * Change customer education record identified by education type and
	 * education number
	 * 
	 * @param action
	 *            - including "Activate", "Manually Verify" and "Deactivate"
	 * @param educationType
	 * @param educationNum
	 */
	public void changeCustomerEducationStatus(String action,
			String educationType, String educationNum) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		String id = educationPage.getEducationID(educationType, educationNum);
		this.changeCustomerEducationStatus(action, id);
	}

	public void changeCustomerEducationStatus(String action, String educationID) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();

		logger.info(action + " Customer Education(ID=" + educationID + ").");
		educationPage.selectEducationById(educationID);
		if (action.equals("Activate")) {
			educationPage.clickActivate();
		} else if (action.equals("Manually Verify")) {
			educationPage.clickManuallyVerify();
		} else if (action.equals("Deactivate")) {
			educationPage.clickDeactivate();
		}
		ajax.waitLoading();
		educationPage.waitLoading();
	}

	/**
	 * Edit customer education info
	 * 
	 * @param education
	 * @return
	 */
	public String updateCustomerEducation(Education education) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrEditEducationPage editEducationPage = LicMgrEditEducationPage
				.getInstance();

		logger.info("Update Customer Education(ID=" + education.id
				+ ") to (Type=" + education.educationType + ", Num="
				+ education.educationNum + ").");
		educationPage.clickEducationId(education.id);
		ajax.waitLoading();
		editEducationPage.waitLoading();
		editEducationPage.setEducation(education);
		editEducationPage.clickOK();
		ajax.waitLoading();
		educationPage.waitLoading();

		String toReturn = educationPage.getEducationID(education.educationType,
				education.educationNum);
		return toReturn;
	}

	/**
	 * add/edit/activate/Deactivate/Remove/Manually Verify Education for
	 * customer in customer details page
	 * 
	 * @param educations
	 * @param expectEdus
	 *            this param is just for edit education, if you do other
	 *            operation,it should be null
	 * @param operation
	 *            add/edit/activate/Deactivate/Remove/Manually Verify
	 * @Return void
	 * @Throws
	 */
	public void manageEducationsForCustomer(String operation,
			Education[] expectEdus, Education... educations) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrAddEducationPage addEduPage = LicMgrAddEducationPage
				.getInstance();
		LicMgrEditEducationPage editEduPage = LicMgrEditEducationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info(operation + " educations for customer......");

		if (!educationPage.exists()) {
			educationPage.clickEducationTab();
			ajax.waitLoading();
			educationPage.waitLoading();
		}

		if (operation.equalsIgnoreCase("add")) {
			for (Education edu : educations) {
				educationPage.clickAddEducation();
				ajax.waitLoading();
				addEduPage.waitLoading();
				addEduPage.setEducation(edu);
				ajax.waitLoading();
				addEduPage.clickOK();
				ajax.waitLoading();
				Object page = browser.waitExists(confirmDialogWidget,
						educationPage);
				if (page == confirmDialogWidget) {
					confirmDialogWidget.clickOK();
				}
				ajax.waitLoading();
			}
		} else if (operation.equalsIgnoreCase("edit")) {

			if (expectEdus.length != educations.length) {
				throw new ErrorOnDataException(
						"education size is wrong when editing educations..");
			}
			for (int i = 0; i < educations.length; i++) {
				educationPage.clickEducationIdByNum(educations[i].educationNum);
				ajax.waitLoading();
				editEduPage.waitLoading();
				editEduPage.setEducation(expectEdus[i]);
				ajax.waitLoading();
				editEduPage.clickOK();
				ajax.waitLoading();
			}
		} else {

			for (Education edu : educations) {
				if (!StringUtil.isEmpty(edu.id)) {
					educationPage.selectEducationById(edu.id);
				} else {
					educationPage.selectEducationByNum(edu.educationNum);
				}
			}

			if (operation.equalsIgnoreCase("activate")) {
				educationPage.clickActivate();
			} else if (operation.equalsIgnoreCase("deactivate")) {
				educationPage.clickDeactivate();
			} else if (operation.equalsIgnoreCase("Remove")) {
				educationPage.clickRemove();
				ajax.waitLoading();
				confirmWidget.waitLoading();
				confirmWidget.clickOK();
			} else if (operation.equalsIgnoreCase("Manually Verify")) {
				educationPage.clickManuallyVerify();
			} else {
				throw new ActionFailedException("Unknow Operation:" + operation);
			}
			ajax.waitLoading();
		}
		educationPage.waitLoading();
	}

	public void removeAllCustEdus() {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		educationPage.selectAllEducations();
		educationPage.clickRemove();
		ajax.waitLoading();

		Object page = browser.waitExists(confirmDialogWidget, educationPage);
		if (page == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			educationPage.waitLoading();
		}
	}

	/**
	 * Add customer certification in Add Certification Widget
	 * 
	 * @param certification
	 * @return
	 */
	public String addCustomerCertification(Certification certification) {
		return addCustomerCertification(certification, true);
	}

	/**
	 * Add customer certification in Add Certification Widget
	 * 
	 * @param certifications
	 * @return
	 */
	public String[] addCustomerCertification(Certification... certifications) {
		String[] ids = new String[certifications.length];

		for (int i = 0; i < certifications.length; i++) {
			ids[i] = this.addCustomerCertification(certifications[i]);
		}

		return ids;
	}

	/**
	 * Edit customer certification
	 * 
	 * @param editCertification
	 * @param isClickOK
	 * @return if edit action succeed, return certification id; if not, return
	 *         error message displayed on the top of edit widget
	 */
	public String editCustomerCertification(Certification editCertification,
			boolean isClickOK) {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget
				.getInstance();
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
		logger.info("Edit customer certification - " + editCertification.id
				+ ".");
		this.gotoEditCertificationWidget(editCertification.id);
		editCertificationWidget.setCertificationNum(editCertification.num);
		editCertificationWidget.setEffectiveFrom(editCertification.effectiveFrom);
		browser.waitExists(confirmPage, editCertificationWidget);//wait and dismiss confirm dialog
		editCertificationWidget.setEffectiveTo(editCertification.effectiveTo);
		browser.waitExists(confirmPage, editCertificationWidget);//wait and dismiss confirm dialog
		if (isClickOK) {
			editCertificationWidget.clickOK();
		} else {
			editCertificationWidget.clickCancel();
		}
		ajax.waitLoading();
		String toReturn = "";
		Object page = browser.waitExists(editCertificationWidget,
				custCertificationPg);
		if (editCertificationWidget == page) {
			toReturn = editCertificationWidget.getErrorMessage();
		}
		if ((custCertificationPg == page) && isClickOK) {
			toReturn = custCertificationPg
					.getCertificationID(editCertification);
			logger.info("----New edited certification id is: " + toReturn);
		}

		return toReturn;
	}

	/**
	 * Add/Edit/Activate/Deactivate/Remove certifications in customer
	 * certification page.
	 * 
	 * @param certifications
	 * @param expectCert
	 *            this param is just used when editing certification,In other
	 *            situation,it should be null
	 * @param operation
	 *            add/edit/activate/deactivate/remove
	 * @Return void
	 * @Throws
	 */
	public void manageCertificationForCustomer(String operation,
			Certification[] expectCert, Certification... certifications) {

		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage
				.getInstance();

		logger.info(operation + " Certification .....");
		if (!certificationPage.exists()) {
			certificationPage.clickCertificationsTab();
			certificationPage.waitLoading();
		}
		if (operation.equalsIgnoreCase("Add")) {
			LicMgrAddCertificationWidget addWidget = LicMgrAddCertificationWidget
					.getInstance();
			for (Certification c : certifications) {
				certificationPage.clickAddCertification();
				addWidget.waitLoading();
				addWidget.setCertification(c);
				addWidget.clickOK();
				ajax.waitLoading();
			}
		} else if (operation.equalsIgnoreCase("Edit")) {
			LicMgrEditCertificationWidget cWidget = LicMgrEditCertificationWidget
					.getInstance();
			if (expectCert.length != certifications.length) {
				throw new ErrorOnDataException(
						"please make sure the 'expectCert' and 'certifications' have same length");
			}

			for (int i = 0; i < certifications.length; i++) {
				certificationPage.clickCertificationID(certifications[i]);
				cWidget.waitLoading();
				cWidget.setCertification(expectCert[i]);
				cWidget.clickOK();
				ajax.waitLoading();
			}
		} else {
			for (Certification c : certifications) {
				certificationPage.selectCertificationIDCheckBox(c);
			}

			if (operation.equalsIgnoreCase("Activate")) {
				certificationPage.clickActivate();
			} else if (operation.equalsIgnoreCase("Deactivate")) {
				certificationPage.clickDeactivate();
			} else if (operation.equalsIgnoreCase("Remove")) {
				LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
						.getInstance();
				certificationPage.clickRemove();
				confirmWidget.waitLoading();
				confirmWidget.clickOK();
			} else {
				throw new ActionFailedException("Unknow operation:" + operation);
			}
			ajax.waitLoading();
		}
	}

	/**
	 * Remove customer certification
	 * 
	 * @param certification
	 * @param isCancel
	 * @return confirm widget message
	 */
	public String removeCustomerCertification(String certificationID,
			boolean isClickOK) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Remove Customer Certification");
		certificationPg.selectCertificationIDCheckBox(certificationID);
		certificationPg.clickRemove();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		String message = confirmWidget.getMessage();
		if (isClickOK) {
			confirmWidget.clickOK();
		} else {
			confirmWidget.clickCancel();
		}
		ajax.waitLoading();
		certificationPg.waitLoading();
		return message;
	}

	/**
	 * This method is used to Activate or Deactivate certification
	 * 
	 * @param changeStatus
	 *            - Active or Deactivate
	 * @param certificationID
	 *            - certificationID
	 */
	public void activateOrDeactivateCertification(String changeStatus,
			String certificationID) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();

		logger.info("Activate or Inactivate certification.");
		certificationPg.selectCertificationIDCheckBox(certificationID);

		if (changeStatus.equalsIgnoreCase("Activate")) {
			certificationPg.clickActivate();
		} else if (changeStatus.equalsIgnoreCase("Deactivate")) {
			certificationPg.clickDeactivate();
		}
		ajax.waitLoading();
		certificationPg.waitLoading();
	}

	/**
	 * Remove all customer certification. work flow start from: customer details
	 * page or certification page end in : customer certification page
	 * 
	 * @param isClickOK
	 * @return
	 */
	public String removeAllCustomerCertifications(boolean isClickOK) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();

		String message = "";

		if (!certificationPg.exists()) {
			detailsPage.clickCertificationsTab();
			ajax.waitLoading();
			certificationPg.waitLoading();
		}
		if (certificationPg.checkWhetherHaveCertificationResults()) {
			certificationPg.selectAllCertificationIDCheckBox();
			certificationPg.clickRemove();
			ajax.waitLoading();
			confirmWidget.waitLoading();
			message = confirmWidget.getMessage();
			if (isClickOK) {
				confirmWidget.clickOK();
			} else {
				confirmWidget.clickCancel();
			}
			ajax.waitLoading();
		}

		return message;
	}

	/**
	 * Add customer suspension and revoke all privileges
	 * 
	 * @param suspension
	 * @return
	 */
	public String addCustomerSuspension(Suspension suspension) {
		return this.addCustomerSuspension(suspension, null);
	}
	
	/**
	 * Add customer suspension and revoke the specific privilege
	 * @param suspension
	 * @param privilege
	 * @return
	 * @author Lesley Wang
	 * Apr 24, 2013
	 */
	public String addCustomerSuspension(Suspension suspension, PrivilegeInfo privilege) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrAddSuspensionWidget addSuspensionWidget = LicMgrAddSuspensionWidget
				.getInstance();
		LicMgrRevokePrivilegeWidget revokePrivilegeWidget = LicMgrRevokePrivilegeWidget
				.getInstance();

		logger.info("Add Customer Suspension.");
		suspensionPage.clickAddSuspension();
		ajax.waitLoading();
		addSuspensionWidget.waitLoading();
		addSuspensionWidget.setSuspension(suspension);
		addSuspensionWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(revokePrivilegeWidget,
				addSuspensionWidget, suspensionPage);
		String toReturn = "";
		if (page == addSuspensionWidget) {
			addSuspensionWidget.clickOK();
			ajax.waitLoading();
			
			if(addSuspensionWidget.exists()){
				toReturn = addSuspensionWidget.getErrorMessage();
			}
		}
		
		if (page == revokePrivilegeWidget) {
			if (privilege == null) {
				revokePrivilegeWidget.selectAllPrivilegedsToBeRevoked();
			} else {
					revokePrivilegeWidget.selectPrivilegeToBeRevoked(privilege);
			}
			revokePrivilegeWidget.clickOK();
			ajax.waitLoading();
			suspensionPage.waitLoading();
			toReturn = suspensionPage
					.getSuspensionIdByComment(suspension.comment.trim());
		}
		if (page == suspensionPage) {
			toReturn = suspensionPage
					.getSuspensionIdByComment(suspension.comment.trim());
		}

		return toReturn;
	}

	/**
	 * Edit customer suspension
	 * 
	 * @param suspension
	 * @return
	 */
	public String editCustomerSuspension(Suspension suspension) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrEditSuspensionWidget editSuspensionWidget = LicMgrEditSuspensionWidget
				.getInstance();

		logger.info("Edit Customer Suspension - " + suspension.id);
		suspensionPage.clickSuspensionID(suspension.id);
		ajax.waitLoading();
		editSuspensionWidget.waitLoading();
		editSuspensionWidget.setSuspension(suspension);
		editSuspensionWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(editSuspensionWidget, suspensionPage);
		String toReturn = "";
		if (page == editSuspensionWidget) {
			toReturn = editSuspensionWidget.getErrorMessage();
		}
		if (page == suspensionPage) {
			toReturn = suspensionPage
					.getSuspensionIdByComment(suspension.comment);
		}

		return toReturn;
	}

	/**
	 * Remove Customer Suspension
	 * 
	 * @param suspension
	 */
	public void removeCustomerSuspension(Suspension suspension) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Remove Customer Suspension - " + suspension.id);
		// if suspension existed,then remove
		if (suspensionPage.getSuspensionRow(suspension.comment) > 0) {
			suspensionPage.selectSuspension(suspension.comment);
			suspensionPage.clickRemove();
			ajax.waitLoading();
			confirmWidget.waitLoading();
			confirmWidget.clickOK();
			ajax.waitLoading();
			suspensionPage.waitLoading();
		}
	}
	
	public void removeCustAllSuspensions(){
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Remove Customer all suspension.");
		suspensionPage.selectAllSuspensions();
		suspensionPage.clickRemove();
		ajax.waitLoading();
		if(confirmWidget.exists()){
			confirmWidget.waitLoading();
			confirmWidget.clickOK();
			ajax.waitLoading();
		}
		suspensionPage.waitLoading();
	}

	/**
	 * Go to store details page from vendor search page
	 * 
	 * @param storeName
	 */
	public void gotoStoreDetailPageByID(String storeID) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();

		this.gotoVendorSearchPg();
		logger.info("Go to store(StoreID=" + storeID
				+ ") detail page from vendor search page.");
		vendorSearchPg.setStoreID(storeID);

		vendorSearchPg.clickSearch();
		ajax.waitLoading();
		vendorSearchPg.clickStoreNameLink(storeID);
		ajax.waitLoading();
		storeDetailPg.waitLoading();
	}

	/**
	 * Add/edit/activate/Deactivate/Remove suspension in customer detial page
	 * 
	 * @param suspensions
	 * @param expectSusp
	 * @param operation
	 * @Return Suspension[] when adding suspensions,In other situation,return
	 *         null;
	 * @Throws
	 */
	public Suspension[] manageSuspensions(String operation,
			Suspension... suspensions) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();

		logger.info(operation + " suspensions......");

		if (!suspensionPage.exists()) {
			suspensionPage.clickSuspensionsTab();
			ajax.waitLoading();
			suspensionPage.waitLoading();
		}

		if (operation.equalsIgnoreCase("add")) {
			LicMgrAddSuspensionWidget suspWidget = LicMgrAddSuspensionWidget
					.getInstance();
			LicMgrRevokePrivilegeWidget revokePrivilegeWidget = LicMgrRevokePrivilegeWidget
					.getInstance();

			for (int i = 0; i < suspensions.length; i++) {
				suspensionPage.clickAddSuspension();
				ajax.waitLoading();
				suspWidget.waitLoading();
				// this is one of unique identifiers for this suspension
				suspensions[i].comment += System.currentTimeMillis();
				suspWidget.setSuspension(suspensions[i]);
				suspWidget.clickOK();
				ajax.waitLoading();
				Object page = browser.waitExists(revokePrivilegeWidget,
						suspensionPage);
				if (page == revokePrivilegeWidget) {
					revokePrivilegeWidget.selectAllPrivilegedsToBeRevoked();
					revokePrivilegeWidget.clickOK();
					ajax.waitLoading();
					suspensionPage.waitLoading();
				}
				suspensions[i].id = suspensionPage
						.getSuspensionIdByComment(suspensions[i].comment.trim());
			}
			return suspensions;
		} else if (operation.equalsIgnoreCase("Edit")) {
			LicMgrEditSuspensionWidget editPage = LicMgrEditSuspensionWidget
					.getInstance();

			for (int i = 0; i < suspensions.length; i++) {
				suspensionPage.clickSuspensionID(suspensions[i].id);
				editPage.waitLoading();
				editPage.setSuspension(suspensions[i]);
				editPage.clickOK();
				ajax.waitLoading();
			}
		} else {
			for (Suspension s : suspensions) {
				suspensionPage.selectSuspension(s.comment.trim());
			}

			if (operation.equalsIgnoreCase("Activate")) {
				suspensionPage.clickActivate();
			} else if (operation.equalsIgnoreCase("Deactivate")) {
				suspensionPage.clickDeactivate();
			} else if (operation.equalsIgnoreCase("Remove")) {
				LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
						.getInstance();
				suspensionPage.clickRemove();
				confirmWidget.waitLoading();
				confirmWidget.clickOK();
			} else {
				throw new ErrorOnDataException("Unknow Operation:" + operation);
			}
			ajax.waitLoading();
		}
		return null;
	}

	/**
	 * verify Educations in customer education page
	 * 
	 * @param expectEducation
	 * @Return void
	 * @Throws
	 */
	public void verifyEducations(Education... edus) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage
				.getInstance();
		List<Education> expectEducation = Arrays.asList(edus);
		List<Education> educations = educationPage.getEducations();
		List<Education> temp = new ArrayList<Education>();
		String msg = "";

		logger.info("Verify education......");
		Collections.sort(educations);
		Collections.sort(expectEducation);

		// verify education whose status is removed.
		for (int i = 0; i < expectEducation.size(); i++) {
			if (expectEducation.get(i).status.equalsIgnoreCase("Removed")) {
				int row = educationPage.getEducationRowByNum(expectEducation
						.get(i).educationNum);
				if (row > 0) {
					throw new ErrorOnPageException("the education that num="
							+ expectEducation.get(i).educationNum
							+ " should not be dispayed!");
				}
				continue;
			}
			temp.add(expectEducation.get(i));
		}

		expectEducation = temp;

		if (educations.size() != expectEducation.size()) {
			throw new ErrorOnPageException("Verify education failed !");
		}

		for (int i = 0; i < expectEducation.size(); i++) {

			if (!expectEducation.get(i).status.equalsIgnoreCase(educations
					.get(i).status)) {
				logger.warn("Education status: expected \""
						+ expectEducation.get(i).status + "\", actual \""
						+ educations.get(i).status + "\"");
				msg += " status=" + educations.get(i).status;
			}

			if (!expectEducation.get(i).educationType
					.equalsIgnoreCase(educations.get(i).educationType)) {
				logger.warn("Education type: expected \""
						+ expectEducation.get(i).educationType
						+ "\", actual \"" + educations.get(i).educationType
						+ "\"");
				msg += " type=" + educations.get(i).educationType;
			}

			if (!expectEducation.get(i).educationNum
					.equalsIgnoreCase(educations.get(i).educationNum)) {
				logger.warn("Education number: expected \""
						+ expectEducation.get(i).educationNum + "\", actual \""
						+ educations.get(i).educationNum + "\"");
				msg += " educationNum=" + educations.get(i).status;
			}

			if (!expectEducation.get(i).state.equalsIgnoreCase(educations
					.get(i).state)) {
				logger.warn("Education state: expected \""
						+ expectEducation.get(i).state + "\", actual \""
						+ educations.get(i).state + "\"");
				msg += " state=" + educations.get(i).state;
			}

			if (!expectEducation.get(i).country.equalsIgnoreCase(educations
					.get(i).country)) {
				logger.warn("Education country: expected \""
						+ expectEducation.get(i).country + "\", actual \""
						+ educations.get(i).country + "\"");
				msg += " country=" + educations.get(i).country;
			}

			if (msg.trim().length() > 1) {
				throw new ErrorOnPageException("Error education info:" + msg
						+ " is wrong.");
			}
		}
		logger.info("Verify Education successfully");

	}

	/**
	 * Verify Certifications in Customer Certification page
	 * 
	 * @param expectCertification
	 * @Return void
	 * @Throws
	 */
	public void verifyCertifications(Certification... expectCertifications) {
		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage
				.getInstance();
		List<Certification> expectCertification = Arrays
				.asList(expectCertifications);
		List<Certification> certifications = certificationPage
				.getCertifications();
		List<Certification> temp = new ArrayList<Certification>();
		String msg = "";

		logger.info("Verify Certification......");
		Collections.sort(certifications);
		Collections.sort(expectCertification);
		// verify certification whose status is removed.
		for (int i = 0; i < expectCertification.size(); i++) {
			if (expectCertification.get(i).status.equalsIgnoreCase("Removed")) {
				int row = certificationPage
						.getCertificationRow(expectCertification.get(i).num);
				if (row > 0) {
					throw new ErrorOnPageException("the Certification that num="
							+ expectCertification.get(i).num
							+ " should not be dispayed!");
				}
				continue;
			}
			temp.add(expectCertification.get(i));
		}
		expectCertification = temp;

		if (certifications.size() != expectCertification.size()) {
			throw new ErrorOnPageException("Verify Certification failed !- size are different: actual "+certifications.size()+" expected "+expectCertification.size());
		}

		for (int i = 0; i < expectCertification.size(); i++) {
			if (!expectCertification.get(i).status
					.equalsIgnoreCase(certifications.get(i).status)) {
				msg += " status=" + certifications.get(i).status;
			}

			if (!expectCertification.get(i).type
					.equalsIgnoreCase(certifications.get(i).type)) {
				msg += " type=" + certifications.get(i).type;
			}

			if (!expectCertification.get(i).num.equalsIgnoreCase(certifications
					.get(i).num)) {
				msg += " num=" + certifications.get(i).num;
			}

			if (DateFunctions.compareDates(expectCertification.get(i).effectiveFrom, certifications.get(i).effectiveFrom)!=0) {
				msg += " effectiveFrom=" + certifications.get(i).effectiveFrom+", expected "+expectCertification.get(i).effectiveFrom;
			}

			if (DateFunctions.compareDates(expectCertification.get(i).effectiveTo, certifications.get(i).effectiveTo)!=0) {
				msg += " effectiveTo=" + certifications.get(i).effectiveTo
						+ "/" + expectCertification.get(i).effectiveTo;
			}

			if (msg.trim().length() > 1) {
				throw new ErrorOnPageException("Error CerTification info:"
						+ msg + " is wrong.");
			}
		}

	}

	/**
	 * Verify customer suspension detail info with expected
	 * 
	 * @param suspension
	 */
	public void verifyCustomerSuspensionDetailInfo(Suspension suspension) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrEditSuspensionWidget editSuspensionWidget = LicMgrEditSuspensionWidget
				.getInstance();

		logger.info("Verify suspension detail info in Edit Suspension widget.");
		suspensionPage.clickSuspensionID(suspension.id);
		ajax.waitLoading();
		editSuspensionWidget.waitLoading();

		boolean result = editSuspensionWidget.compareSuspensionInfo(suspension);
		if (!result) {
			throw new ErrorOnPageException(
					"The suspension detail info doesn't match. Please refer the log for detail.");
		}

		editSuspensionWidget.clickOK();
		ajax.waitLoading();
		suspensionPage.waitLoading();
	}

	/**
	 * Verify customer education detail info with expected
	 * 
	 * @param edu
	 */
	public void verifyCustomerEducationDetailInfo(Education edu) {
		LicMgrCustomerEducationPage educationPg = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrEditEducationPage editEducationPg = LicMgrEditEducationPage
				.getInstance();

		logger.info("Verify education detail info in Edit education widget.");
		educationPg.clickEducationIdByNum(edu.educationNum);
		ajax.waitLoading();
		editEducationPg.waitLoading();

		boolean result = editEducationPg.compareEducationInfo(edu);
		if (!result) {
			throw new ErrorOnPageException(
					"The education detail info doesn't match. Please refer the log for detail.");
		}

		editEducationPg.clickOK();
		ajax.waitLoading();
		educationPg.waitLoading();
	}

	/**
	 * verify Suspensions for customer in customer suspension page
	 * 
	 * @param expectSusp
	 * @Return void
	 * @Throws
	 */
	public void verifyCustomerSuspensions(Suspension... expectSusps) {
		LicMgrCustomerSuspensionPage suspPage = LicMgrCustomerSuspensionPage
				.getInstance();
		List<Suspension> expectSuspensionsList = Arrays.asList(expectSusps);
		List<Suspension> actualSuspensionsList = suspPage.getSuspensions();
		List<Suspension> tempForActual = new ArrayList<Suspension>();
		List<Suspension> tempForExpect = new ArrayList<Suspension>();

		logger.info("Verify suspension info from customer suspension page...");
		Collections.sort(actualSuspensionsList);
		Collections.sort(expectSuspensionsList);
		// verify suspension that status is removed

		for (int i = 0; i < expectSuspensionsList.size(); i++) {
			if (expectSuspensionsList.get(i).status.equalsIgnoreCase("Removed")) {
				int row = suspPage.getSuspensionRow(expectSuspensionsList
						.get(i).comment);
				if (row > 0) {
					throw new ErrorOnPageException(
							"The suspension whose comments is "
									+ expectSuspensionsList.get(i).comment
									+ " should not be displayed");
				}
				continue;
			}
			tempForExpect.add(expectSuspensionsList.get(i));
			for (int j = 0; j < actualSuspensionsList.size(); j++) {
				if (expectSuspensionsList.get(i).id
						.equals(actualSuspensionsList.get(j).id)) {
					tempForActual.add(actualSuspensionsList.get(j));
				}
			}
		}
		expectSuspensionsList = tempForExpect;
		actualSuspensionsList = tempForActual;
		if (expectSuspensionsList.size() != actualSuspensionsList.size()) {
			throw new ErrorOnDataException(
					"the amount of suspensions is wrong!");
		}
		for (int i = 0; i < expectSuspensionsList.size(); i++) {
			Suspension expect = expectSuspensionsList.get(i);
			Suspension susp = actualSuspensionsList.get(i);
			boolean flag = true;
			if (!expect.status.equalsIgnoreCase(susp.status)) {
				flag = false;
				logger.error("Suspension Status doesn't match. "
						+ expect.status + "|" + susp.status);
			}
			if (!expect.type.equals(susp.type)) {
				flag = false;
				logger.error("Suspension Type doesn't match. " + expect.type
						+ "|" + susp.type);
			}
			if (DateFunctions.compareDates(expect.beginDate, susp.beginDate)!=0) {
				flag = false;
				logger.error("Suspension Begin Date doesn't match. "
						+ expect.beginDate + "|" + susp.beginDate);
			}
			if (DateFunctions.compareDates(expect.endDate, susp.endDate)!=0) {
				flag = false;
				logger.error("Suspension End Date doesn't match. "
						+ expect.endDate + "|" + susp.endDate);
			}
			if (DateFunctions.compareDates(expect.datePosted, susp.datePosted)!=0) {
				flag = false;
				logger.error("Suspension Date Posted doesn't match. "
						+ expect.datePosted + "|" + susp.datePosted);
			}
			if (!expect.comment.trim().equals(susp.comment.trim())) {
				flag = false;
				logger.error("Suspension Status doesn't match. "
						+ expect.comment + "|" + susp.comment);
			}
			if (!flag) {
				throw new ErrorOnDataException(
						"Actual suspension doesn't match expected.");
			}
		}
	}

	/**
	 * edit customer identifier in customer identifier page
	 * 
	 * @param identifier
	 * @return
	 * @Return CustomerIdentifier
	 * @Throws
	 */
	public CustomerIdentifier editIdentifierInfo(CustomerIdentifier identifier) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();

		logger.info("Edit identifier that idType=" + identifier.identifierType);
		identifierPage.clickIdentifiersTab();
		ajax.waitLoading();
		identifierPage.waitLoading();
		if (identifier.id.length() <= 0) {
			identifier.id = identifierPage.getIdentifierID(
					identifier.identifierType, identifier.identifierNum);// ""
		}

		editCustomerIdentifier(identifier);
		return identifier;
	}

	public void safeEditCustomerIdentifier(CustomerIdentifier identifier) {
		editCustomerIdentifier(identifier, true);
	}

	public void editCustomerIdentifier(CustomerIdentifier identifier) {
		editCustomerIdentifier(identifier, false);
	}

	void editCustomerIdentifier(CustomerIdentifier identifier,
			boolean safeEdit) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrEditIdentifierPage editIdPage = LicMgrEditIdentifierPage
				.getInstance();

		if (safeEdit
				&& identifierPage.checkIdentifierExistByTypeAndNumber(
						identifier.identifierType, identifier.identifierNum)) {
			this.removeCustIdentifier(identifier.identifierType,
					identifier.identifierNum);
		}

		this.gotoIdentifierDetailPage(identifier.id);
		logger.info("Edit identifier to " + identifier.identifierType + " = "
				+ identifier.identifierNum);
		editIdPage.editIdentifier(identifier);
		editIdPage.clickOK();
		ajax.waitLoading();
	}

	
	/**
	 * Edit customer status
	 * 
	 * @param customer
	 */
	public void editCustomerStatus(Customer customer) {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("Edit customer status.");
		topMenuPg.clickCustomers();
		custSearchPg.waitLoading();
		custSearchPg.searchCustomerByIdnetifier(
				customer.identifier.identifierType,
				customer.identifier.identifierNum, customer.customerClass);
		Object page = browser.waitExists(custSearchPg, custDetailPg);
		if (page == custSearchPg) {
			custSearchPg.clickCustomerFirstNumer();
		}
		custDetailPg.waitLoading();
//		custDetailPg.clickEdit();
//		ajax.waitLoading();
		custDetailPg.editCustomerStatus(customer.status);
//		custDetailPg.clickApply();
//		ajax.waitLoading();
	}

	/**
	 * get customer id form db by customer last name and first name
	 * 
	 * @param lName
	 * @param fName
	 * @param schema
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getCustomerIdByCustName(String lName, String fName,
			String mName, String schema) {
		String custId = "";
		String sql = "select CUST_ID from C_CUST where F_NAME='" + fName
				+ "' and L_NAME='" + lName + "' "
				+ ((mName.length() > 0) ? ("and M_NAME='" + mName + "'") : "");
		db.resetSchema(schema);
		List<String> ids = db.executeQuery(sql, "CUST_ID");
		if (ids.size() > 0) {
			custId = db.executeQuery(sql, "CUST_ID").get(0).trim();
		}
		logger.info("Find customer id is: " + custId);
		return custId;
	}

	public String getCustomerIdByCustName(String lName, String fName,
			String schema) {
		return getCustomerIdByCustName(lName, fName, "", schema);
	}

	/**
	 * Get customer numbers from DB
	 * 
	 * @param cust
	 *            Customer
	 * @param schema
	 * @return customer numbers
	 */
	public List<String> getCustomerNumsFromDB(Customer cust, String schema) {
		List<String> custNums = null;
		String licenseType = "";
		if (cust.licenseType.equals("MDWFP #")) {
			licenseType = "Customer #";
		}else if (cust.licenseType.equals("Conservation #")) {
			licenseType = "Conservation Number";
		}
		String sql = "select distinct(cch.CUST_NUMBER) from C_CUST_HFPROFILE cch, C_CUST cc, C_CUST_CLASS ccc, "
				+ "C_CUST_HFP_IDENTIFIER cchi, C_IDENTIFIER ci, C_IDENTIFIER_TYPE cit,"
				+ "D_ADDRESS da, C_CUST_HFP_ADDRESS ccha "
				+ "where cch.CUST_ID=cc.CUST_ID and cch.CUST_CLASS_ID=ccc.ID and cch.ID=cchi.PROF_ID and cch.ID=ccha.PROF_ID "
				+ "and cchi.IDENTIFIER_ID=ci.ID and ci.TYPE_ID=cit.ID and ccha.ADDRESS_ID=da.ID "
				+ ((cust.licenseType.trim().length() > 0) ? ("and cit.NAME='"
						+ licenseType + "'") : "")
				+ ((cust.licenseNum.trim().length() > 0) ? ("and cchi.IDENTIFIER_ID='"
						+ cust.licenseNum + "'")
						: "")
				+ ((cust.licenseState.trim().length() > 0) ? ("and ci.STATE='"
						+ cust.licenseState + "'") : "")
				+ ((cust.custNum.trim().length() > 0) ? ("and cch.CUST_NUMBER='"
						+ cust.custNum + "'")
						: "")
				+ ((cust.status.trim().length() > 0) ? ("and cch.STATUS_ID='"
						+ cust.status + "'") : "")// TODO: the status id should
				// be number
				+ ((cust.customerClass.trim().length() > 0) ? ("and ccc.NAME='"
						+ cust.customerClass.substring(0, 1).toUpperCase()
						+ cust.customerClass.substring(1).toLowerCase() + "'")
						: "")
				+ ((cust.fName.trim().length() > 0) ? ("and cc.F_NAME='"
						+ cust.fName + "'") : "")
				+ ((cust.lName.trim().length() > 0) ? ("and cc.L_NAME='"
						+ cust.lName + "'") : "")
				+ ((cust.mName.trim().length() > 0) ? ("and cc.M_NAME='"
						+ cust.mName + "'") : "")
				+ ((cust.businessName.trim().length() > 0) ? ("and cc.ORG_NAME='"
						+ cust.businessName + "'")
						: "")
				+ ((cust.dateOfBirth.trim().length() > 0) ? ("and cch.BIRTHDAY='"
						+ DateFunctions.formatDate(cust.dateOfBirth,
								"dd-MMM-yyyy") + "'")
						: "")
				+ ((cust.address.trim().length() > 0) ? ("and da.ADDRESS='"
						+ cust.address + "'") : "")
				+ ((cust.city.trim().length() > 0) ? ("and da.CITY='"
						+ cust.city + "'") : "")
				+ ((cust.county.trim().length() > 0) ? ("and da.COUNTY_ID='"
						+ cust.county + "'") : "")
				+ ((cust.state.trim().length() > 0) ? ("and da.STATE_ID='"
						+ cust.state + "'") : "")
				+ ((cust.zip.trim().length() > 0) ? ("and da.STATE_ID='"
						+ cust.zip + "'") : "");

		db.resetSchema(schema);
		logger.info(sql);
		custNums = db.executeQuery(sql, "CUST_NUMBER");
		if (custNums.size() <= 0) {
			throw new ErrorOnDataException("No item can be find!");
		}
		return custNums;
	}

	/**
	 * Get the unique customer number
	 * 
	 * @param cust
	 *            : Customer
	 * @param schema
	 * @return customer number
	 */
	public String getCustomerNum(Customer cust, String schema) {
		String custNum = "";
		List<String> custNums = this.getCustomerNumsFromDB(cust, schema);
		if (custNums.size() > 0) {
			custNum = custNums.get(0);
		}
		// else {
		// throw new ErrorOnDataException("Customer number is not unique.");
		// }
		logger.info("The customer number is " + custNum);

		return custNum;
	}

	/**
	 * get all address infomation from Db
	 * 
	 * @param fName
	 * @param lName
	 * @param schema
	 * @return
	 * @Return List<CustomerAddress>
	 * @Throws
	 */
	public Customer getCustomerAddress(String custId, String schema) {
		Customer cust = new Customer();
		cust.custId = custId;
		String[] colNames = new String[] { "ADDRESS_TYPE_ID", "ADDRESS",
				"CITY", "ZIP_CODE", "COUNTY_ID", "STATE_ID", "COUNTRY_ID",
				"VALIDATION_STATUS", "SAME_AS_IND" };
		String sql = "select * from D_ADDRESS where id in ("
				+ " select ADDRESS_ID from C_CUST_HFP_ADDRESS where PROF_ID=("
				+ "   select ID from C_CUST_HFPROFILE where CUST_ID=" + custId
				+ " ))";
		String stateSql = "select NAME from D_REF_STATE_PROVNC where ID=";
		String countrySql = "select NAME from D_REF_COUNTRY where ID=";
		String countySql = "select NAME from D_REF_COUNTY where ID=";

		String commonSchema = schema.substring(0, schema.lastIndexOf("_") + 1)
				+ "COMMON";
		db.resetSchema(schema);
		List<String[]> list = db.executeQuery(sql, colNames);

		for (String[] addrInfo : list) {
			Address custAddr = new Address();

			custAddr.address = addrInfo[1];
			custAddr.city = addrInfo[2];
			custAddr.zip = addrInfo[3];

			db.resetSchema(commonSchema);
			if (addrInfo[4] != null && addrInfo[4].trim().length() > 0) {
				custAddr.county = db.executeQuery(countySql + addrInfo[4],
						"NAME").get(0);
			}

			if (addrInfo[5] != null && addrInfo[5].trim().length() > 0) {
				custAddr.state = db
						.executeQuery(stateSql + addrInfo[5], "NAME").get(0);
			}

			if (addrInfo[6] != null && addrInfo[6].trim().length() > 0) {
				custAddr.country = db.executeQuery(countrySql + addrInfo[6],
						"NAME").get(0);
			}

			if (addrInfo[7] != null && addrInfo[7].trim().length() > 0) {
				if (addrInfo[7].equals("0")) {
					custAddr.status = "Pending";
				} else if (addrInfo[7].equals("1")) {
					custAddr.status = "Valid";
				} else if (addrInfo[7].equals("2")) {
					custAddr.status = "Zip Only";
				} else if (addrInfo[7].equals("3")) {
					custAddr.status = "Failed";
				} else {
					throw new ItemNotFoundException("Unknow Status !");
				}
			}

			if (addrInfo[0].equals("1")) {
				custAddr.addrType = "Physical Address";
				cust.physicalAddr = custAddr;
			} else if (addrInfo[0].equals("2")) {
				custAddr.addrType = "Mailing Address";
				cust.mailingAddr = custAddr;
				if (addrInfo[8].equals("1")) {
					cust.mailAddrAsPhy = true;
				} else {
					cust.mailAddrAsPhy = false;
				}
			} else if (addrInfo[0].equals("3")) {
				custAddr.addrType = "Alternate Address";
				cust.alternateAddr = custAddr;
			} else {
				throw new ItemNotFoundException("Unknown Address Type !");
			}
		}

		return cust;
	}

	/**
	 * get customer profile from C_CUST_HFPROFILE table
	 * 
	 * @param cust
	 * @param schema
	 * @return
	 * @Return Customer
	 * @Throws
	 */
	public Customer getCustmerProfile(String custId, String schema) {
		Customer cust = new Customer();
		cust.custId = custId;
		String sql = "Select Cf.Status_Id,Cf.Cust_Class_Id,Cf.Override_Required_Ind,"
				+ "cf.Override_Reason,cf.Birthday,c.Suffix "
				+ " From C_Cust_Hfprofile Cf,C_Cust C"
				+ " Where cf.CUST_ID=c.CUST_ID  and c.CUST_ID=" + custId;
		String[] colNames = new String[] { "STATUS_ID", "CUST_CLASS_ID",
				"OVERRIDE_REQUIRED_IND", "OVERRIDE_REASON", "BIRTHDAY",
				"SUFFIX" };
		db.resetSchema(schema);
		List<String[]> list = db.executeQuery(sql, colNames);

		String[] s = list.get(0);

		if (s[0].equals("1")) {
			cust.custProfileStatus = "Active";
		} else if (s[0].equals("2")) {
			cust.custProfileStatus = "Inactive";
		} else if (s[0].equals("3")) {
			cust.custProfileStatus = "Deceased";
		} else if (s[0].equals("4")) {
			cust.custProfileStatus = "Merged";
		} else {
			throw new ItemNotFoundException("Unknown status.");
		}

		if (s[1].equals("1")) {
			cust.customerClass = "INDIVIDUAL";
		} else if (s[1].equals("2")) {
			cust.customerClass = "ORGANIZATION";
		} else {
			throw new ItemNotFoundException("Unknown customer class...");
		}

		if (s[2].equals("0")) {
			cust.overrideReqId = false;
		} else if (s[2].equals("1")) {
			cust.overrideReqId = true;
		} else {
			throw new ItemNotFoundException(
					"Unknow Overide required Id code...");
		}

		cust.overideReason = s[3] == null ? "" : s[3];
		cust.dateOfBirth = s[4].substring(0, 10).replaceAll("-", "/");
		cust.suffix = s[5] == (null) ? "" : s[5];

		return cust;
	}

	/**
	 * get customer phone, Phone contact preferred and email from db
	 * 
	 * @param lName
	 * @param fName
	 * @param schema
	 * @return
	 * @Return Customer
	 * @Throws
	 */
	public Customer getPhoneEmailAndPreferContactByCustID(String custId,
			String schema) {

		Customer cust = new Customer();
		cust.custId = custId;

		String sql = "select TYP,VAL from C_CUST_PHONE where CUST_ID=" + custId;
		String preferPhoneSql = "select PHONE_CONTACT_PREF,"
				+ " PREF_CONTACT_TIME from C_CUST where CUST_ID= " + custId;

		logger.info("get customer phome and email from db...");
		db.resetSchema(schema);
		List<String[]> list = db.executeQuery(sql,
				new String[] { "TYP", "VAL" });
		List<String[]> prefList = db.executeQuery(preferPhoneSql, new String[] {
				"PHONE_CONTACT_PREF", "PREF_CONTACT_TIME" });

		for (String[] s : list) {
			String phone = s[1] == (null) ? "" : s[1];

			if (s[0].equalsIgnoreCase("HOME")) {
				cust.hPhone = phone;
			} else if (s[0].equalsIgnoreCase("WORK")) {
				cust.bPhone = phone;
			} else if (s[0].equalsIgnoreCase("CELL")) {
				cust.mPhone = phone;
			} else if (s[0].equalsIgnoreCase("FAX")) {
				cust.fax = phone;
			} else if (s[0].equalsIgnoreCase("EMAIL")) {
				cust.email = phone;
			} 
		}

		String[] sPref = prefList.get(0);
		if (sPref[0].equals("0")) {
			cust.phoneContact = "No preference";
		} else if (sPref[0].equals("1")) {
			cust.phoneContact = "Home Phone";
		} else if (sPref[0].equals("2")) {
			cust.phoneContact = "Work Phone";
		} else if (sPref[0].equals("3")) {
			cust.phoneContact = "Cell Phone";
		} else {
			throw new ItemNotFoundException("Unknow Contact");
		}

		if (sPref[1].equals("0")) {
			cust.contactTime = "No preference";
		} else if (sPref[1].equals("1")) {
			cust.contactTime = "Business Hour ?Morning";
		} else if (sPref[1].equals("2")) {
			cust.contactTime = "Business Hour ?Afternoon";
		} else if (sPref[1].equals("3")) {
			cust.contactTime = "Evening";
		} else {
			throw new ItemNotFoundException("Unknown  Contact Time.");
		}

		return cust;
	}

	/**
	 * get customer profile attribute from db by customer l_name and f_name
	 * 
	 * @param cust
	 * @param schema
	 * @return
	 * @Return Customer
	 * @Throws
	 */
	public Customer getCustProfileAttrFromDB(String custId, String schema) {
		Customer cust = new Customer();
		String sql = "select d.ATTR_NAME,c.VALUE from D_ATTR d,C_CUST_HFPROFILE_ATTR c "
				+ "   where c.ATTR_ID=d.ATTR_ID and c.PROF_ID=("
				+ "   select ID from C_CUST_HFPROFILE where CUST_ID="
				+ custId
				+ ")";
		db.resetSchema(schema);
		List<String[]> list = db.executeQuery(sql, new String[] { "ATTR_NAME",
				"VALUE" });
		for (String[] attr : list) {
			if (attr[0].equals("testAmericanCitizen")) {
				cust.isAmerican = attr[1].equals("y") ? true : false;
			} else if (attr[0].equals("Mississipi Resident")) {
				cust.isMSResident = attr[1].equals("y") ? true : false;
			} else if (attr[0].equalsIgnoreCase("ethnicity")) {
				if (attr[1].equals("1")) {
					cust.ethnicity = "White";
				} else if (attr[1].equals("2")) {
					cust.ethnicity = "Blank";
				} else if (attr[1].equals("3")) {
					cust.ethnicity = "Hispanic/Latino";
				} else if (attr[1].equals("4")) {
					cust.ethnicity = "Asian";
				} else if (attr[1].equals("5")) {
					cust.ethnicity = "Native American";
				} else if (attr[1].equals("6")) {
					cust.ethnicity = "Other";
				} else if (attr[1].equals("7")) {
					cust.ethnicity = "Unknown";
				} else {
					throw new ItemNotFoundException(
							"Unknown customer ethnicity:" + attr[1]);
				}
			} else if (attr[0].equals("SolicitationInd")) {
				cust.solicitationIndcator = attr[1].equals("y") ? "Yes" : "No";
			} else if (attr[0].equalsIgnoreCase("gender")) {
				if (attr[1].equals("F")) {
					cust.custGender = "Female";
				} else if (attr[1].equals("M")) {
					cust.custGender = "Male";
				} else if (attr[1].equals("U")) {
					cust.custGender = "Unknown";
				} else {
					throw new ItemNotFoundException("Unknown customer gender:"
							+ attr[1]);
				}
			} else if (attr[0].equals("Eye Color")) {
				cust.eyeColor = attr[1];
			} else if (attr[0].equals("Height")) {
				cust.height = attr[1];
			} else if (attr[0].equals("Customer Gender")) {
				cust.attributeGender = attr[1].equalsIgnoreCase("Male") ? "M"
						: "F";
			} else if (attr[0].equals("Markings")) {
				cust.markings = attr[1];
			}
		}
		return cust;
	}

	/**
	 * get customer Identifiers infomation from DB
	 * 
	 * @param cust
	 * @param schema
	 * @return
	 * @Return Customer
	 * @Throws
	 */
	public List<CustomerIdentifier> getCustomerIdentifiersFromDB(
			boolean includeDeleted, String custId, String schema) {
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		List<CustomerIdentifier> identifiers = new ArrayList<CustomerIdentifier>();
		String sql = "select A.ID,A.STATUS_ID,A.VERIFY_STATUS_ID,C.NAME,B.SECURE_ID,B.STATE_ID,B.COUNTRY_ID,"
				+ "A.CREATE_LOC_ID,A.CREATE_DATE,A.CREATE_USR_ID, A.DELETED_IND from "
				+ " C_Cust_Hfp_Identifier A,C_Identifier B,C_Identifier_Type C"
				+ " Where "
				+ ((!includeDeleted) ? "A.DELETED_IND=0 and " : "")
				+ "c.ID=b.TYPE_ID and a.IDENTIFIER_ID=b.ID and PROF_ID=("
				+ "select ID from C_CUST_HFPROFILE where  CUST_ID="
				+ custId
				+ ") order by A.ID DESC";
		String[] colNames = new String[] { "ID", "STATUS_ID",
				"VERIFY_STATUS_ID", "NAME", "SECURE_ID", "STATE_ID",
				"COUNTRY_ID", "CREATE_LOC_ID", "CREATE_DATE", "CREATE_USR_ID",
				"DELETED_IND" };

		String stateSql = "select NAME from D_REF_STATE_PROVNC where ID=";
		String countrySql = "select NAME from D_REF_COUNTRY where ID=";

		db.resetSchema(schema);
		logger.info(sql);
		List<String[]> list = db.executeQuery(sql, colNames);

		for (String[] identifier : list) {
			CustomerIdentifier cId = new CustomerIdentifier();
			// Identifier id
			cId.id = identifier[0];
			// Identifier Status
			if (identifier[10].equals("1")) {
				cId.status = "Removed";
			} else if (identifier[1].equals("1")) {
				cId.status = "Active";
			} else if (identifier[1].equals("2")) {
				cId.status = "Inactive";
			} else if (identifier[1].equals("3")) {
				cId.status = "verified";
			} else {
				throw new ItemNotFoundException("Unknown status code:"
						+ identifier[1]);
			}
			// Identifier verify_status, but the verify_status dosdn't have the
			// real mean for 'Customber #'
			if (identifier[2].equals("0")) {
				cId.verifyStatus = "No Status";
			} else if (identifier[2].equals("1")) {
				cId.verifyStatus = "Failed";
			} else if (identifier[2].equals("2")) {
				cId.verifyStatus = "Pending";
			} else if (identifier[2].equals("3")) {
				cId.verifyStatus = "Verified";
			} else if (identifier[2].equals("4")) {
				cId.verifyStatus = "Not Applicable";
			} else {
				throw new ItemNotFoundException("Unknown verify status:"
						+ identifier[2]);
			}
			// Identifier type
			cId.identifierType = identifier[3];
			// Identifier Number
			if (!cId.identifierType.equalsIgnoreCase("Customer #")) {// as
																		// Customer
																		// # is
																		// not
																		// encrypt
				cId.identifierNum = AwoCryptoUtil.identifierDecrypt(identifier[4], TestProperty.getProperty("target_env"));//CryptoUtil.decryptGiftCard(identifier[4]);
			} else {
				cId.identifierNum = AwoCryptoUtil.identifierDecrypt(identifier[4], TestProperty.getProperty("target_env"));
			}
			// Identifier status
			if (identifier[5] == null) {
				cId.state = "";
			} else {
				cId.state = db.executeQuery(stateSql + identifier[5], "NAME")
						.get(0);
			}
			// Identifier country
			if (identifier[6] == null) {
				cId.country = "";
			} else {
				cId.country = db.executeQuery(countrySql + identifier[6],
						"NAME").get(0);
			}
			// Identifier creation application
			if (identifier[7].equals("158230")
					|| identifier[7].equals("151831")) {
				cId.creationApp = "LicenseManager";
			} else
				cId.creationApp = identifier[7];
			// Identifier Date/Time
			cId.createDate = DateFunctions.formatDate(DateFunctions
					.parseDateString(identifier[8], "yyyy-MM-dd HH:mm:ss"),
					"MM/dd/yyyy HH:mm:ss");
			// Identifier Creation User
			if (identifier[9].equals("500000002")
					|| identifier[9].equals("400086073")) {
				cId.createUser = "qa-auto-fm";
			} else
				cId.createUser = identifier[9];
			cId.deletedInd = identifier[10];
			identifiers.add(cId);
		}
		return identifiers;
	}

	public List<CustomerIdentifier> getCustomerIdentifiersFromDB(String custId,
			String schema) {
		return this.getCustomerIdentifiersFromDB(true, custId, schema);
	}

	public List<CustomerIdentifier> getNoneDeletedIdentifiersFromDB(
			String custId, String schema) {
		return this.getCustomerIdentifiersFromDB(false, custId, schema);
	}

	/**
	 * Verify specific identifier status from DB
	 */
	public void verifyIdentifierVerifyStatusFromDB(String identifierID,
			String expectVerifyStatus, String schema) {
		String sql = "select * from C_CUST_HFP_IDENTIFIER where id = "
				+ identifierID;
		db.resetSchema(schema);
		logger.info("Execute query - " + sql);
		List<String> verifyStatusList = db
				.executeQuery(sql, "VERIFY_STATUS_ID");
		// Get verify status
		String verifyStatus = verifyStatusList.get(0);
		if (verifyStatus.equals("4")) {
			verifyStatus = "Not Applicable";
		} else if (verifyStatus.equals("1")) {
			verifyStatus = "Failed";
		} else if (verifyStatus.equals("3")) {
			verifyStatus = "Verified";
		}
		// Verify verify-status
		if (verifyStatusList.size() > 0) {
			if (!expectVerifyStatus.equals(verifyStatus)) {
				throw new ErrorOnDataException("The actual verify statsu: '"
						+ verifyStatus
						+ "' is not match the expect verify status: '"
						+ expectVerifyStatus + "'");
			}
		}
	}

	/**
	 * Get customer education type from DB
	 * 
	 * @param schema
	 */
	public List<String> getCustEducationTypesFromDB(String schema) {
		String sql = "select NAME from d_education_type";
		db.resetSchema(schema);
		return db.executeQuery(sql, "NAME");
	}

	public String getCustEducationVerifiableIndicator(String schema,
			String education) {
		db.resetSchema(schema);
		String sql = "select VERIFIABLE_IND from D_EDUCATION_TYPE where NAME = '"
				+ education + "'";
		List<String> result = db.executeQuery(sql, "VERIFIABLE_IND");
		if (result.size() == 0) {
			throw new ErrorOnDataException("Cannot find Educaton type - "
					+ education);
		}

		return result.get(0);
	}

	/**
	 * Create customer, the work flow start from license manager home page, end
	 * in Customers Search Page.
	 * 
	 * @param customer
	 * @Return void
	 * @Throws
	 */
	public String createNewCustomer(Customer customer) {
		logger.info("Create a Customer for license.....");
		this.gotoNewCustomerPage(customer.customerClass);
		this.addOrEditCustomerInfo(customer, LicMgrNewCustomerPage
				.getInstance());
		customer.custId = this.finishAddOrEditCustomer();
		return customer.custId;
	}

	public String createNewCustomerWithoutSearch(Customer customer) {
		logger.info("Create a new customer without search...");
		this.gotoNewCustomerPageWithoutSearch(customer.customerClass);
		this.addOrEditCustomerInfo(customer, LicMgrNewCustomerPage
				.getInstance());
		customer.custId = this.finishAddOrEditCustomer();
		logger.info("customer num is " + customer.custId);
		return customer.custId;
	}
	/**
	 * 
	 * @param cust
	 * @param transaction
	 * @return
	 */
	public String addOrEditCustomerDuringPurchase(Customer cust,
			String transaction, boolean isAdd, boolean isNullDOBInDetailPage) {
		LicMgrPrivilegeItemDetailPage privilegeDetailPage = LicMgrPrivilegeItemDetailPage
				.getInstance();
		LicMgrIdentifyCustomerPage identifyCustPage = LicMgrIdentifyCustomerPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPage = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrConfirmCustomerPage confirmCustPage = LicMgrConfirmCustomerPage
				.getInstance();
		LicenseMgrHomePage homePage = LicenseMgrHomePage.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget
				.getInstance();

		logger
				.info("Add customer specific DOB during original purchase privilege process.");
		String toReturn = "";
		if (transaction.equals(TRANNAME_PURCHASE_PRIVILEGE)) {
			if (homePage.checkIdentifyCustomerAreaExistInPrevilegeSection()) {
				homePage.identifyCustomer(cust.customerClass, cust.dateOfBirth,
						cust.identifier.identifierType,
						cust.identifier.identifierNum, cust.identifier.country,
						cust.identifier.state);
				homePage.clickPurchasePrivilege();
				ajax.waitLoading();
			} else {
				this.gotoIdentifyCustomerPage();
				identifyCustPage.identifyCustomer(cust);
				identifyCustPage.clickOK();
				ajax.waitLoading();
			}
		} else if (transaction
				.equals(TRANNAME_TRANSFER_PRIVILEGE)) {
			privilegeDetailPage.clickTransfer();
			ajax.waitLoading();
			identifyCustPage.waitLoading();
			identifyCustPage.identifyCustomer(cust);
			identifyCustPage.clickOK();
			ajax.waitLoading();
		}

		Object page = browser.waitExists(confirmWidget, confirmCustPage);
		String tempDob = "";
		if (isNullDOBInDetailPage) {
			tempDob = cust.dateOfBirth;
			cust.dateOfBirth = "";
		}
		if (page == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			newCustPage.waitLoading();
			newCustPage.setCustomerDetails(cust);
			newCustPage.clickOK();
		}
		if (page == confirmCustPage) {
			confirmCustPage.setCustomerInfo(cust);
			confirmCustPage.clickOK();
		}
		ajax.waitLoading();
		page = browser.waitExists(residencyWidget, newCustPage,
				confirmCustPage, addItemPage, orderCartPage);
		if (page == residencyWidget) {
			if (cust.residencyStatus.equalsIgnoreCase("Resident")) {
				residencyWidget.selectResident();
				ajax.waitLoading();
				if (residencyWidget.isAdditionalProofDropdownListExists()) {
					residencyWidget
							.selectAdditionalProof(cust.additionalProofOfResidency);
				}
			} else {
				residencyWidget.selectNonResident();
				ajax.waitLoading();
			}
			residencyWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(newCustPage, confirmCustPage,
					addItemPage, orderCartPage);
		}
		if (page == newCustPage) {
			toReturn = newCustPage.getErrorMsg();
		}
		if (page == confirmCustPage) {
			toReturn = confirmCustPage.getMessage();
		}
		if (isNullDOBInDetailPage) {
			cust.dateOfBirth = tempDob;
		}
		return toReturn;
	}

	/**
	 * go to customer profile details page from customer search page.
	 * 
	 * @param lName
	 * @param fName
	 * @param custClass
	 */

	public void gotoCustomerDetails(String lName, String fName,
			String custClass, String status) {
		LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("goto customer profile details page.....");
		customerPg.setLicenseNum("");
		customerPg.selectCustClass(custClass);
		ajax.waitLoading();
		if (status.length() > 0) {
			customerPg.selectStatus(status);
		}
		customerPg.searchCustomer(lName, fName);
//		customerPg.clickCustomerFirstNumer();//Quentin[2013.08.19] there's a script DEFECT here: if there're multiple records displayed
		if(custClass.equalsIgnoreCase(INDIVIDUAL_CUSTOMER_CLASS)) {
			customerPg.clickCustomerByName(lName, fName);
		} else {
			customerPg.clickCustomerFirstNumer();
		}
		detailsPage.waitLoading();
	}

	public void gotoCustomerDetails(String lName, String fName, String custClass) {
		gotoCustomerDetails(lName, fName, custClass, "");
	}

	public void gotoCustomerDetails(String lName, String fName) {
		this.gotoCustomerDetails(lName, fName, "Individual");
	}

	/**
	 * Go to vendor details page from top menu Vendors
	 * 
	 * @param vendorNum
	 */
	public void gotoVendorDetailsPgFromTopMenu(String vendorNum) {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();

		logger.info("Go to vendor detail page from top menu.");
		topMenu.clickVendors();
		vendorSearchPg.waitLoading();
		this.gotoVendorDetailPgFromVendorSearchPg(vendorNum);
	}

	/**
	 * Goto vendor search page from top menu
	 */
	public void gotoVendorSearchPg() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();

		logger.info("Go to vendor search page from top menu.");

		topMenu.clickVendors();
		ajax.waitLoading();
		vendorSearchPg.waitLoading();
	}

	/**
	 * Search vendor or store from vendor search list page
	 * 
	 * @param vendorInfo
	 * @param storeInfo
	 * @param bankAccountNum
	 * @param bondNum
	 */
	public void searchVendorOrAgent(VendorInfo vendorInfo, StoreInfo storeInfo,
			String bankAccountNum, String bondNum) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();

		logger.info("Search vendor or agent.");
		vendorSearchPg.setSearchCriteria(vendorInfo, storeInfo, bankAccountNum,
				bondNum);
		vendorSearchPg.clickSearch();
		ajax.waitLoading();
		browser.waitExists(vendorSearchPg, vendorDetailPg, storeDetailPg);
	}

	/**
	 * Search vendor vendor name;
	 * 
	 * @param vendorName
	 */
	public void searchVendorInfoByVendorName(String vendorName) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();

		logger.info("Search vendor info by vendor name.");
		vendorSearchPg.deselectStoreState();
		ajax.waitLoading();
		vendorSearchPg.deselectStoreCountry();
		ajax.waitLoading();
		vendorSearchPg.setVendorName(vendorName);
		vendorSearchPg.clickSearch();
		ajax.waitLoading();
	}

	/**
	 * go to EFT Invoice Breakdown Page from store detail page
	 * 
	 * @param invoice
	 * @param date
	 */
	public void gotoEFTInvoiceBreakdownPage(String invoice, String date) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreEFTInvoiceListPage listPg = LicMgrStoreEFTInvoiceListPage
				.getInstance();
		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();
		LicMgrStoreEFTInvoiceDetailBreakdownPage breakdownPg = LicMgrStoreEFTInvoiceDetailBreakdownPage
				.getInstance();

		logger.info("Go to invoice" + invoice + "detail page...");
		storeDetailPg.clickInvoices();
		ajax.waitLoading();
		listPg.waitLoading();

		listPg.gotoInvoiceDetailByInvoiceNum(invoice);
		ajax.waitLoading();
		detailPg.waitLoading();

		detailPg.gotoEFTBreakdownByPostDate(date);
		ajax.waitLoading();
		breakdownPg.waitLoading();
	}

	/**
	 * Go to store details page from vendor search page
	 * 
	 * @param storeName
	 */
	public void gotoStoreDetailPage(String storeName) {
		this.gotoStoreDetailPage(storeName, "");
	}

	/**
	 * Go to store details page from vendor search page
	 * 
	 * @param storeName
	 */
	public void gotoStoreDetailPage(String storeName, String vendorName) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();

		this.gotoVendorSearchPg();
		logger.info("Go to store(StoreName=" + storeName
				+ ") detail page from vendor search page.");
		
		vendorSearchPg.setVendorName(vendorName);
		vendorSearchPg.setStoreName(storeName);
		vendorSearchPg.deselectStoreState();
		ajax.waitLoading();
		vendorSearchPg.waitLoading();
		
		vendorSearchPg.clickSearch();
		ajax.waitLoading();
		vendorSearchPg.waitLoading();
		vendorSearchPg.clickStoreNameLink(storeName);
		ajax.waitLoading();
		storeDetailPg.waitLoading();
	}

	public void associateCustWithStore(String custNum) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
		LicMgrOutfitterAssignmentWidget outfitterAssigWidget = LicMgrOutfitterAssignmentWidget.getInstance();
		
		logger.info("Associated the customer with number=" + custNum + " to the store");
		storeDetailPg.setCustomerNum(custNum);
		storeDetailPg.clickSearchCustomer();
		ajax.waitLoading();
		outfitterAssigWidget.waitLoading();
		outfitterAssigWidget.clickOK();
		ajax.waitLoading();
		storeDetailPg.waitLoading();
	}
	
	/**
	 * Goto store inventory page
	 */
	public void gotoStoreInventoryTab() {
		LicMgrStoreDetailsPage storeDetailsPage = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreInventoryPage storeInventoryPage = LicMgrStoreInventoryPage
				.getInstance();

		logger.info("Go to store inventory page.");
		storeDetailsPage.clickInventoryTab();
		ajax.waitLoading();
		storeInventoryPage.waitLoading();
	}

	public void gotoViewPrivilegeInventoryAllocationCountsWidget() {
		LicMgrStoreInventoryPage storeInventoryPage = LicMgrStoreInventoryPage
				.getInstance();
		LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget countsWidget = LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget
				.getInstance();

		logger
				.info("Go to 'View Privilege Inventory Allocation Counts' widget from store inventory tab.");
		storeInventoryPage.clickViewPrivilegeInventoryAllocationCounts();
		ajax.waitLoading();
		countsWidget.waitLoading();
	}

	public void gotoViewUnusablePrivilegeInventoryWidget() {
		LicMgrStoreInventoryPage storeInventoryPage = LicMgrStoreInventoryPage
				.getInstance();
		LicMgrStoreViewUnusablePrivilegeInventoryWidget unusableInventoryWidget = LicMgrStoreViewUnusablePrivilegeInventoryWidget
				.getInstance();

		logger
				.info("Go to 'View Unusable Privilege Inventory' widget from store inventory tab.");
		storeInventoryPage.clickViewUnusablePrivilegeInventory();
		ajax.waitLoading();
		unusableInventoryWidget.waitLoading();
	}

	public void gotoViewSoldPrivilegeInventory() {
		LicMgrStoreInventoryPage storeInventoryPage = LicMgrStoreInventoryPage
				.getInstance();
		LicMgrStoreViewSoldPrivilegeInventoryWidget soldInventoryWidget = LicMgrStoreViewSoldPrivilegeInventoryWidget
				.getInstance();

		logger
				.info("Go to 'View Sold Privilege Inventory' widget from store inventory tab.");
		storeInventoryPage.clickViewSoldPrivilegeInventory();
		soldInventoryWidget.waitLoading();
	}

	/**
	 * Go to store EFT Adjustment page from store detail page
	 */
	public void gotoStoreEFTAdjustmentFromStoreDetailPg() {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreEFTAdjustmentsPage storeEFTAdjustmentPg = LicMgrStoreEFTAdjustmentsPage
				.getInstance();

		logger.info("Go To Store Adjustment page");
		storeDetailPg.clickEFTAdjustmentsTab();
		ajax.waitLoading();
		storeEFTAdjustmentPg.waitLoading();
	}

	/**
	 * Add Store EFT Adjustment info
	 * 
	 * @param eftAdjustment
	 * @return
	 */
	public String addStoreEFTAdjustment(EFTAdjustmentInfo eftAdjustment) {
		LicMgrStoreEFTAdjustmentsPage storeEFTAdjustmentPg = LicMgrStoreEFTAdjustmentsPage
				.getInstance();
		LicMgrAddStoreEFTAdjustmentsWidget addStoreEFTAdjustmentPg = LicMgrAddStoreEFTAdjustmentsWidget
				.getInstance();

		logger.info("Add Store EFT Adjustment info.");
		storeEFTAdjustmentPg.clickAddEFTAdjustment();
		ajax.waitLoading();
		addStoreEFTAdjustmentPg.waitLoading();
		addStoreEFTAdjustmentPg.setEFTAdjustmentInfo(eftAdjustment);
		addStoreEFTAdjustmentPg.clickOK();
		ajax.waitLoading();
		storeEFTAdjustmentPg.waitLoading();

		// search and get this adjustment id
		eftAdjustment.setAdjustmentType(eftAdjustment.getAdjustmentType()
				.split("-")[0].trim());
		String temp[] = eftAdjustment.getReason().split("-");
		eftAdjustment.setReason(temp[0].trim() + " - " + temp[1].trim());
		if (StringUtil.isEmpty(eftAdjustment.getInvoicingStatus())) {
			eftAdjustment.setInvoicingStatus("NotInvoiced");
		}
		searchStoreEFTAdjustment(eftAdjustment);
		List<String> ids = storeEFTAdjustmentPg.getAdjustmentIDs();
		String id = ids.size() > 0 ? ids.get(0) : "";
		logger.info("New added Store Adjustment id is: " + id);
		return id;
	}

	/**
	 * Search Store EFT Adjustment
	 * 
	 * @param eftAdjustment
	 */
	public void searchStoreEFTAdjustment(EFTAdjustmentInfo eftAdjustment) {
		LicMgrStoreEFTAdjustmentsPage storeEFTAdjustmentPg = LicMgrStoreEFTAdjustmentsPage
				.getInstance();

		logger.info("Search store eft adjustment.");
		storeEFTAdjustmentPg.setSearchCriteria(eftAdjustment);
		storeEFTAdjustmentPg.clickGo();
		ajax.waitLoading();
		storeEFTAdjustmentPg.waitLoading();
	}

	/**
	 * Edit store/agent financial config info
	 * 
	 * @param config
	 * @return
	 */
	public String editStoreFinancialConfig(FinancialConfig config) {
		LicMgrStoreDetailsPage storeDetailPage = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreFinancialConfigPage financialConfigPage = LicMgrStoreFinancialConfigPage
				.getInstance();

		logger.info("Edit Store(ID#=" + storeDetailPage.getStoreID()
				+ ") Financial Config info.");
		storeDetailPage.clickFinancialConfigTab();
		ajax.waitLoading();
		financialConfigPage.waitLoading();
		financialConfigPage.setupFinancialConfigInfo(config);
		financialConfigPage.clickSave();
		ajax.waitLoading();
		String msg = financialConfigPage.getErrorMessage();

		return msg;
	}

	/**
	 * Go to store product assignment sub page in store detail page 1. Click
	 * 'Vendors' link to goto vendor search page; 2. Search store by store name
	 * and click store name link to goto store detail page; 3. Click 'Product
	 * Assignments' tab link to switch store-product assignment sub page.
	 * 
	 * @param storeName
	 */
	public void gotoStoreProductAssignmentPage(String storeName) {
		gotoStoreProductAssignmentPage(storeName, "");
	}

	/**
	 * Go to store product assignment sub page in store detail page 1. Click
	 * 'Vendors' link to goto vendor search page; 2. Search store by store name
	 * and click store name link to goto store detail page; 3. Click 'Product
	 * Assignments' tab link to switch store-product assignment sub page.
	 * 
	 * @param storeName
	 */
	public void gotoStoreProductAssignmentPage(String storeName, String vendor) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();

		logger
				.info("Go to store product assignment page from store detail page...");
		this.gotoStoreDetailPage(storeName, vendor);
		storeDetailPg.clickProductAssignmentsTab();
		ajax.waitLoading();
		storeProductAssignmentPg.waitLoading();
	}

	public List<ProductStoreAssignment> getStoreProductAssignmentList(
			String prdType, Boolean showSameClassOnly) {
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();

		logger
				.info("Get all record of store product assignment of product type-->"
						+ prdType);

		if (showSameClassOnly) {
			storeProductAssignmentPg
					.checkShowProductsAssignedToStoresWithTheSameLocationClassOnly();
		} else {
			storeProductAssignmentPg
					.uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly();
		}

		storeProductAssignmentPg
				.gotoProductAssignmentListByProductCategory(prdType);
		storeProductAssignmentPg.waitLoading();

		return storeProductAssignmentPg.getAllRecordsOnPage(prdType);

	}

	/**
	 * This method is used to assign product to store
	 * 
	 * @param productCategory
	 * @param productCode
	 */
	public void assignProductToStore(String productCategory, String productCode) {
		this.assignOrUnassignProductToStore(productCategory, productCode, true);
	}

	/**
	 * This method is used to un-Assign product to store
	 * 
	 * @param productCategory
	 * @param productCode
	 */
	public void unassignProductFromStore(String productCategory,
			String productCode) {
		this
				.assignOrUnassignProductToStore(productCategory, productCode,
						false);
	}

	/**
	 * Assign privilege to store
	 * 
	 * @param productCode
	 */
	public void assignPrivilgeToStore(String productCode) {
		this.assignProductToStore("Privilege", productCode);
	}

	/**
	 * Unassign privilege to store
	 * 
	 * @param productCode
	 */
	public void unassignPrivilgeToStore(String productCode) {
		this.unassignProductFromStore("Privilege", productCode);
	}

	/**
	 * Assign vehicle to store
	 * 
	 * @param productCode
	 */
	public void assignVehicleToStore(String productCode) {
		this.assignProductToStore("Vehicle", productCode);
	}

	/**
	 * Unassign vehicle from store
	 * 
	 * @param productCode
	 */
	public void unassignVehicleFromStore(String productCode) {
		this.unassignProductFromStore("Vehicle", productCode);
	}

	/**
	 * Assign consumable to store
	 * 
	 * @param productCode
	 */
	public void assignConsumableToStore(String productCode) {
		this.assignProductToStore("Consumable", productCode);
	}

	/**
	 * Unassign consumable to store
	 * 
	 * @param productCode
	 */
	public void unassignConsumableToStore(String productCode) {
		this.unassignProductFromStore("Consumable", productCode);
	}

	/**
	 * Assign supply to store
	 * 
	 * @param productCode
	 */
	public void assignSupplyToStore(String productCode) {
		this.assignProductToStore("Supply", productCode);
	}

	/**
	 * Unassign supply to store
	 * 
	 * @param productCode
	 */
	public void unassignSupplyToStore(String productCode) {
		this.unassignProductFromStore("Supply", productCode);
	}

	/**
	 * This method is used to assign or unassign product to store, start from
	 * product store assignment detail page, end at product store assignment
	 * detail page
	 * 
	 * @param productCategory
	 * @param productCode
	 * @param isAssign
	 */
	public void assignOrUnassignProductToStore(String productCategory,
			String productCode, boolean isAssign) {
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();

		logger.info("Assign or Unassign " + productCategory + ": "
				+ productCode + " to store.");
		storeProductAssignmentPg
				.uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly();
		if (productCategory.equalsIgnoreCase("Privilege")) {
			storeProductAssignmentPg.selectPrivilegeProducts();
		} else if (productCategory.equalsIgnoreCase("Vehicle")) {
			storeProductAssignmentPg.selectVehicleProducts();
		} else if (productCategory.equalsIgnoreCase("Consumable")) {
			storeProductAssignmentPg.selectConsumableProducts();
		} else if (productCategory.equalsIgnoreCase("Supply")) {
			storeProductAssignmentPg.selectSupplyProducts();
		} else
			throw new ErrorOnPageException(
					"Did not found this product category " + productCategory);

		storeProductAssignmentPg.clickGo();
		ajax.waitLoading();
		storeProductAssignmentPg.waitLoading();
		storeProductAssignmentPg.selectProductRecord(productCode);

		if (isAssign) {
			storeProductAssignmentPg.clickAssign();
		} else {
			storeProductAssignmentPg.clickUnassign();
		}

		ajax.waitLoading();
		storeProductAssignmentPg.waitLoading();
	}

	/**
	 * Go to view inactive assignment page, start from store product assignment
	 * page
	 */
	public void gotoViewInactiveAssignmentPgFromStoreProductAssignmentPg() {
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();
		LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget
				.getInstance();

		logger
				.info("Go to view inactive assignment page from product assignment page.");
		storeProductAssignmentPg.clickViewInactiveAssignments();
		viewInactiveAssignmentPg.waitLoading();
	}

	/**
	 * Go to store product assignment page from view inactive assignment page
	 */
	public void gotoStoreProductAssignmentPgFromViewInactiveAssignmentPg() {
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();
		LicMgrProductInactiveAssignmentsWidget viewInactiveAssignmentPg = LicMgrProductInactiveAssignmentsWidget
				.getInstance();

		logger
				.info("Go to store product assignment page from inactive assignment page.");
		viewInactiveAssignmentPg.clickOK();
		ajax.waitLoading();
		storeProductAssignmentPg.waitLoading();
	}

	/**
	 * Go to vendor details page from vendor quick search area in home page
	 * 
	 * @param vendorNum
	 */
	public void gotoVendorDetailsPgFromVendorsQuickSearch(String vendorNum) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();

		logger.info("Go to vendor detail page from Vendors quick search area.");
		if (!homePg.exists()) {
			gotoHomePage();
		}

		homePg.searchVendorByVendorNum(vendorNum);
		vendorDetailsPg.waitLoading();
	}

	/**
	 * Add vendor info
	 * 
	 * @param vendor
	 */
	public void addVendor(VendorInfo vendor) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage
				.getInstance();

		logger.info("Add Vendor...");
		if (!addVendorPg.exists()) {
			vendorSearchPg.clickAddVendor();
			ajax.waitLoading();
			addVendorPg.waitLoading();
		}
		addVendorPg.setupVendorInfo(vendor);
		addVendorPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(vendorSearchPg, addVendorPg);
	}

	/**
	 * Go to vendor bank account page from vendor detail page by clicking 'Bank
	 * Accounts' tab link
	 */
	public void gotoVendorBankAccountPage() {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();

		logger.info("Go to vendor bank account detail page.");
		vendorDetailPg.clickBankAccountsTab();
		vendorBankAccountPg.waitLoading();
	}
	
	public void gotoVendorDocumentUploadsPageFromVendorDetailPage(){
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage.getInstance();
		LicMgrVendorDocumentUploadSubPage vendorDocumentUploadPg = LicMgrVendorDocumentUploadSubPage.getInstance();
		
		logger.info("Go to vendor document upload page from vendor detail page.");
		vendorDetailsPg.clickDocumentUploadsTab();
		ajax.waitLoading();
		vendorDocumentUploadPg.waitLoading();		
	}
	
	String addVendorDocumentUploadInfo(DocumentUploadInfo documentUploadInfo,boolean isClickCancel){
		LicMgrVendorDocumentUploadSubPage vendorDocumentUploadPg = LicMgrVendorDocumentUploadSubPage.getInstance();
		LicMgrAddDocumentUploadPage addDocumentUploadPg = LicMgrAddDocumentUploadPage.getInstance();
		LicMgrAddDocumentUploadTypeWidget addDocumentUploadTypePg = LicMgrAddDocumentUploadTypeWidget.getInstance();
		
		logger.info("Add Vendor Document Upload info.");
		vendorDocumentUploadPg.clickUploadDocumentButton();
		ajax.waitLoading();
		addDocumentUploadPg.waitLoading();
		List<String> types = addDocumentUploadPg.getTypeDropDownListElements();
		if(StringUtil.notEmpty(documentUploadInfo.getType()) && !types.contains(documentUploadInfo.getType())){
			addDocumentUploadPg.clickAddNew();
			ajax.waitLoading();
			addDocumentUploadTypePg.waitLoading();
			addDocumentUploadTypePg.setType(documentUploadInfo.getType());
			addDocumentUploadTypePg.clickOKAndWait();
			addDocumentUploadPg.waitLoading();
		}
		addDocumentUploadPg.setupDocumentUploadInfo(documentUploadInfo);
		if(isClickCancel){
			addDocumentUploadPg.clickCancel();
		}else{
			addDocumentUploadPg.clickOK();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(addDocumentUploadPg,vendorDocumentUploadPg);
		String temp = "";
		if(vendorDocumentUploadPg == pages){
			temp = vendorDocumentUploadPg.getDocumentUploadIDByDocumentName(documentUploadInfo.getDocumentName());
			documentUploadInfo.setID(temp);
		}else{
			temp = addDocumentUploadPg.getErrorMessage();
		}
		
		return temp;
	}
	
	public String addVendorDocumentUploadInfoWithClickOk(DocumentUploadInfo documentUploadInfo){
		return this.addVendorDocumentUploadInfo(documentUploadInfo, false);
	}
	
	public String addVendorDocumentUploadInfoWithClickCancel(DocumentUploadInfo documentUploadInfo){
		return this.addVendorDocumentUploadInfo(documentUploadInfo, true);
	}
	
	public void inactivateVendorDocumentUploadInfo(String id){
		LicMgrVendorDocumentUploadSubPage vendorDocumentUploadPg = LicMgrVendorDocumentUploadSubPage.getInstance();
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		
		logger.info("Inactive vendor document upload info.");
		this.gotoVendorDocumentUploadDetailPageFromListPage(id);
		documentUploadDetailPg.inactiveDocumentUpload();
		vendorDocumentUploadPg.waitLoading();
	}
	
	String editVendorDocumentUploadInfo(DocumentUploadInfo documentUploadInfo, boolean isClickCancel){
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		LicMgrVendorDocumentUploadSubPage vendorDocumentUploadPg = LicMgrVendorDocumentUploadSubPage.getInstance();
		LicMgrAddDocumentUploadTypeWidget addDocumentUploadTypePg = LicMgrAddDocumentUploadTypeWidget.getInstance();
		
		logger.info("Edit Vendor Document Upload info.");
		this.gotoVendorDocumentUploadDetailPageFromListPage(documentUploadInfo.getID());
		List<String> types = documentUploadDetailPg.getTypeDropDownListElements();
		if(!types.contains(documentUploadInfo.getType())){
			documentUploadDetailPg.clickAddNew();
			ajax.waitLoading();
			addDocumentUploadTypePg.waitLoading();
			addDocumentUploadTypePg.setType(documentUploadInfo.getType());
			addDocumentUploadTypePg.clickOKAndWait();
			documentUploadDetailPg.waitLoading();
		}
		documentUploadDetailPg.setupDocumentUploadInfo(documentUploadInfo);

		if(isClickCancel){
			documentUploadDetailPg.clickCancel();
		}else{
			documentUploadDetailPg.clickOK();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(documentUploadDetailPg,vendorDocumentUploadPg);
		String temp = "";
		if(documentUploadDetailPg == pages){
			temp = documentUploadDetailPg.getErrorMessage();
		}
		
		return temp;
	}
	
	public String editVendorDocumentUploadInfoWithClickOK(DocumentUploadInfo documentUploadInfo){
		return this.editVendorDocumentUploadInfo(documentUploadInfo, false);
	}
	
	public String editVendorDocumentUploadInfoWithClickCancel(DocumentUploadInfo documentUploadInfo){
		return this.editVendorDocumentUploadInfo(documentUploadInfo, true);
	}
	
	public void gotoVendorDocumentUploadDetailPageFromListPage(String id){
		LicMgrVendorDocumentUploadSubPage vendorDocumentUploadPg = LicMgrVendorDocumentUploadSubPage.getInstance();
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		
		logger.info("Go to vendor document upload detail page. id = " + id);
		vendorDocumentUploadPg.clickIdLink(id);
		ajax.waitLoading();
		documentUploadDetailPg.waitLoading();
	}
	
	public void gotoDocumentUploadHistoryPageFromDocumentUploadDetailPage(){
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		LicMgrDocumentUploadHistoryPage documentUploadHistoryPg = LicMgrDocumentUploadHistoryPage.getInstance();
		logger.info("Go to document upload history page from document detail page.");
		
		documentUploadDetailPg.clickChangeHistory();
		ajax.waitLoading();
		documentUploadHistoryPg.waitLoading();
	}
	
	public void gotoDocumentUploadDetailPageFromHistoryPage(){
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		LicMgrDocumentUploadHistoryPage documentUploadHistoryPg = LicMgrDocumentUploadHistoryPage.getInstance();
		logger.info("Go to document upload detail page from history page.");
		
		documentUploadHistoryPg.clickReturnToDocumentDetailButton();
		ajax.waitLoading();
		documentUploadDetailPg.waitLoading();
	}
	
	public void gotoVehicleDocumentUploadsPageFromVehicleDetailPage(){
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		LicMgrVehicleDocumentUploadPage vehicleDocumentUploadPg = LicMgrVehicleDocumentUploadPage.getInstance();
		
		logger.info("Go to vehicle document upload page from vehicle detail page.");
		vehicleDetailsPg.clickDocumentUploadsTab();
		ajax.waitLoading();	
		vehicleDocumentUploadPg.waitLoading();
	}
	
	String addVehicleDocumentUploadInfo(DocumentUploadInfo documentUploadInfo,boolean isClickCancel){
		LicMgrVehicleDocumentUploadPage vehicleDocumentUploadPg = LicMgrVehicleDocumentUploadPage.getInstance();
		LicMgrAddDocumentUploadPage addDocumentUploadPg = LicMgrAddDocumentUploadPage.getInstance();
		LicMgrAddDocumentUploadTypeWidget addDocumentUploadTypePg = LicMgrAddDocumentUploadTypeWidget.getInstance();
		
		logger.info("Add Vehicle Document Upload info.");
		vehicleDocumentUploadPg.clickUploadDocumentButton();
		ajax.waitLoading();
		addDocumentUploadPg.waitLoading();
		List<String> types = addDocumentUploadPg.getTypeDropDownListElements();
		if(!types.contains(documentUploadInfo.getType())){
			addDocumentUploadPg.clickAddNew();
			ajax.waitLoading();
			addDocumentUploadTypePg.waitLoading();
			addDocumentUploadTypePg.setType(documentUploadInfo.getType());
			addDocumentUploadTypePg.clickOKAndWait();
			addDocumentUploadPg.waitLoading();
		}
		addDocumentUploadPg.setupDocumentUploadInfo(documentUploadInfo);
		if(isClickCancel){
			addDocumentUploadPg.clickCancel();
		}else{
			addDocumentUploadPg.clickOK();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(addDocumentUploadPg,vehicleDocumentUploadPg);
		String temp = "";
		if(vehicleDocumentUploadPg == pages){
			temp = vehicleDocumentUploadPg.getDocumentUploadIDByDocumentName(documentUploadInfo.getDocumentName());
			documentUploadInfo.setID(temp);
		}else{
			temp = addDocumentUploadPg.getErrorMessage();
		}
		
		return temp;
	}
	
	public String addVehicleDocumentUploadInfoWithClickOk(DocumentUploadInfo documentUploadInfo){
		return this.addVehicleDocumentUploadInfo(documentUploadInfo, false);
	}
	
	public String addVehicleDocumentUploadInfoWithClickCancle(DocumentUploadInfo documentUploadInfo){
		return this.addVehicleDocumentUploadInfo(documentUploadInfo, true);
	}
	
	public void gotoVehicleDocumentUploadDetailPageFromListPage(String id){
		LicMgrVehicleDocumentUploadPage vehicleDocumentUploadPg = LicMgrVehicleDocumentUploadPage.getInstance();
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		
		logger.info("Go to vehicle document upload detail page. id = " + id);
		vehicleDocumentUploadPg.clickIdLink(id);
		ajax.waitLoading();
		documentUploadDetailPg.waitLoading();
	}
	
	String editVehicleDocumentUploadInfo(DocumentUploadInfo documentUploadInfo, boolean isClickCancel){
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		LicMgrVehicleDocumentUploadPage vehicleDocumentUploadPg = LicMgrVehicleDocumentUploadPage.getInstance();
		LicMgrAddDocumentUploadTypeWidget addDocumentUploadTypePg = LicMgrAddDocumentUploadTypeWidget.getInstance();
		
		logger.info("Edit Vehicle Document Upload info.");
		this.gotoVendorDocumentUploadDetailPageFromListPage(documentUploadInfo.getID());
		List<String> types = documentUploadDetailPg.getTypeDropDownListElements();
		if(!types.contains(documentUploadInfo.getType())){
			documentUploadDetailPg.clickAddNew();
			ajax.waitLoading();
			addDocumentUploadTypePg.waitLoading();
			addDocumentUploadTypePg.setType(documentUploadInfo.getType());
			addDocumentUploadTypePg.clickOKAndWait();
			documentUploadDetailPg.waitLoading();
		}
		documentUploadDetailPg.setupDocumentUploadInfo(documentUploadInfo);

		if(isClickCancel){
			documentUploadDetailPg.clickCancel();
		}else{
			documentUploadDetailPg.clickOK();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(documentUploadDetailPg,vehicleDocumentUploadPg);
		String temp = "";
		if(documentUploadDetailPg == pages){
			temp = documentUploadDetailPg.getErrorMessage();
		}
		
		return temp;
	}
	
	public String editVehicleDocumentUploadInfoWithClickOK(DocumentUploadInfo documentUploadInfo){
		return this.editVehicleDocumentUploadInfo(documentUploadInfo, false);
	}
	
	public String editVehicleDocumentUploadInfoWithClickCancel(DocumentUploadInfo documentUploadInfo){
		return this.editVehicleDocumentUploadInfo(documentUploadInfo, true);
	}
	
	public void inactivateVehicleDocumentUploadInfo(String id){
		LicMgrDocumentUploadDetailPage documentUploadDetailPg = LicMgrDocumentUploadDetailPage.getInstance();
		LicMgrVehicleDocumentUploadPage vehicleDocumentUploadPg = LicMgrVehicleDocumentUploadPage.getInstance();
		
		logger.info("Inactive vendor document upload info.");
		this.gotoVehicleDocumentUploadDetailPageFromListPage(id);
		documentUploadDetailPg.inactiveDocumentUpload();
		vehicleDocumentUploadPg.waitLoading();
	}

	public String assignVendorBankAccountToStore(String storeName,
			String account, String effectiveDate) {
		return this.assignVendorBankAccountToStore(storeName, account,
				effectiveDate, true, true);
	}

	public String assignVendorBankAccountToStore(String storeName,
			String account, String effectiveDate, boolean isClickOK) {
		return this.assignVendorBankAccountToStore(storeName, account,
				effectiveDate, isClickOK, true);
	}

	/**
	 * Assign vendor bank account to a specific store in Vendor Bank Account
	 * Store Assignments widget.
	 * 
	 * @param storeName
	 * @param account
	 *            - the format of account should be like - 'Checking Routing #
	 *            026009593 Acct# 4111************'
	 * @param effectiveDate
	 * @param isClickOk
	 *            - if true, will click OK button in Vendor Bank Account Store
	 *            Assignments widget; - if false, will click Cancel button
	 * @param overrideExisting
	 *            - if there is an already existing assignment with the same
	 *            Store ID and Effective Date but a different Bank Account ID
	 *            will pop up a named 'Please Confirm' widget - if true, will
	 *            click OK button; - otherwise will click Cancel button
	 * @return - newest added assignment id, if back to Vendor Bank Account page
	 *         - error message, if still in Vendor Bank Account Store
	 *         Assignments widget - Please Confirm message, if it displays
	 */
	public String assignVendorBankAccountToStore(String storeName,
			String account, String effectiveDate, boolean isClickOK,
			boolean overrideExisting) {
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrChangeVendorBankAccountStoreAssignmentsWidget changeVendorBankAccountWidget = LicMgrChangeVendorBankAccountStoreAssignmentsWidget
				.getInstance();
		LicMgrConfirmDialogWidget pleaseConfirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		String toReturn = "";

		logger.info("Assign vendor bank account(" + account + ") to store - "
				+ storeName);

		vendorBankAccountPg.clickChangeStoreBankAccountAssignments();
		ajax.waitLoading();
		changeVendorBankAccountWidget.waitLoading();
		changeVendorBankAccountWidget.assignBankAccountToStore(storeName,
				account, effectiveDate);
		if (isClickOK) {
			changeVendorBankAccountWidget.clickOK();
		} else {
			changeVendorBankAccountWidget.clickCancel();
		}
		ajax.waitLoading();

		Object page = browser.waitExists(pleaseConfirmWidget,
				changeVendorBankAccountWidget, vendorBankAccountPg);
		if (page == pleaseConfirmWidget
				&& pleaseConfirmWidget.getMessage().length() > 0) {
			toReturn = pleaseConfirmWidget.getMessage();
			if (overrideExisting) {
				pleaseConfirmWidget.clickOK();
			} else {
				pleaseConfirmWidget.clickCancel();
			}
			ajax.waitLoading();
			vendorBankAccountPg.waitLoading();
		} else if (page == vendorBankAccountPg) {
			toReturn = "";
		} else if (page == changeVendorBankAccountWidget) {
			toReturn = changeVendorBankAccountWidget.getErrorMessage();
		}

		return toReturn;
	}

	/**
	 * Add vendor bank account at Add Bank Account Widget by clicking 'Add Bank
	 * Account' link in vendor bank account sub page
	 * 
	 * @param account
	 * @param isClickOK
	 * @return - the newest added bank account id OR the error message displayed
	 *         at the add widget
	 */
	public String addVendorBankAccount(VendorBankAccountInfo account,
			boolean isClickOK) {
		LicMgrVendorBankAccountsSubPage vendorBankAccountSubPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrAddVendorBankAccountWidget addBankAccountWidget = LicMgrAddVendorBankAccountWidget
				.getInstance();
		String toReturn = "";

		logger.info("Add vendor bank account in Bank Accounts sub page.");
		vendorBankAccountSubPg.clickAddBankAccount();
		ajax.waitLoading();
		addBankAccountWidget.waitLoading();
		addBankAccountWidget.setBankAccountInfo(account);
		if (isClickOK) {
			addBankAccountWidget.clickOK();
		} else {
			addBankAccountWidget.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(addBankAccountWidget,
				vendorBankAccountSubPg);
		if (page == addBankAccountWidget) {
			toReturn = addBankAccountWidget.getErrorMsg();
		}
		if ((page == vendorBankAccountSubPg) && isClickOK) {
			toReturn = vendorBankAccountSubPg.getBankAccountID(account);
		}

		return toReturn;
	}

	/**
	 * Deactivate vendor bank account identified by account id in Vendor Bank
	 * start from Bank Account sub-tab page, ends at Bank Account Sub-tab page
	 * or Deactivate warning message widget. Account Sub page
	 * 
	 * @param accountID
	 */
	public void deactivateVendorBankAccount(String accountID) {
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrDeactiveVendorBankAccountWarningWidget deactiveWarnPage = LicMgrDeactiveVendorBankAccountWarningWidget
				.getInstance();

		logger.info("Deactivate vendor bank account (ID=" + accountID + ")");
		bankAccountSubPg.deactivateBankAccount(accountID);

		browser.waitExists(deactiveWarnPage, bankAccountSubPg);
	}

	/**
	 * Verify customer Identifier from DB
	 * 
	 * @param cust
	 * @param schema
	 * @Return void
	 * @Throws
	 */
	public void verifyIdentifierFromDb(Customer cust, String schema) {

		List<CustomerIdentifier> newIdentifiers = getCustomerIdentifiersFromDB(
				cust.custId, schema);

		logger.info("Verify Identifier...");

		// ignore the Identfier that type="Customer #"
		for (int i = 0; i < newIdentifiers.size(); i++) {
			if (newIdentifiers.get(i).identifierType.equals("Customer #")) {
				newIdentifiers.remove(i);
				break;
			}
		}

		if (newIdentifiers.size() != cust.identifiers.size()) {
			throw new ErrorOnDataException(
					"Identifier Size Error. Actual size="
							+ newIdentifiers.size() + " expected size="
							+ cust.identifiers.size());
		}

		Collections.sort(newIdentifiers);
		Collections.sort(cust.identifiers);
		for (int i = 0; i < newIdentifiers.size(); i++) {
			if (cust.identifiers.get(i).id == null
					|| cust.identifiers.get(i).id.trim().length() < 1) {
				cust.identifiers.get(i).id = newIdentifiers.get(i).id;
			}

			if (!newIdentifiers.get(i).equals(cust.identifiers.get(i))) {
				throw new ErrorOnDataException("Identifier Error: expected "
						+ cust.identifiers.get(i) + " actual "
						+ newIdentifiers.get(i));
			}
		}
		logger.info("Verify successfully.");
	}

	/**
	 * Verify Address in db.
	 * 
	 * @param cust
	 * @param schema
	 * @Return void
	 * @Throws
	 */
	public void verifyAddressInDb(Customer cust, String schema) {
		String msg = "";
		Customer customer = getCustomerAddress(cust.custId, schema);

		logger.info("Verify Address info in db....");
		if (!customer.physicalAddr.equals(cust.physicalAddr)) {
			msg += "Verify physicalAddr,";
		}

		if (!customer.mailingAddr.equals(cust.mailingAddr)) {
			msg += "maillingAddr,";
		}

		if (!customer.alternateAddr.equals(cust.alternateAddr)) {
			msg += "alternateAddr";
		}

		if (customer.mailAddrAsPhy != cust.mailAddrAsPhy) {
			msg += "mailAddrAsPhy";
		}

		if (msg.length() > 0) {
			throw new ErrorOnDataException(msg + "failed");
		}

		logger.info("Verify sucessfully");
	}

	/**
	 * verify customer details info in db
	 * 
	 * @param cust
	 * @param schema
	 * @Return void
	 * @Throws
	 */
	public void verifyCustAttrFromDB(Customer cust, String schema) {
		String msg = "";
		Customer cust0 = getCustProfileAttrFromDB(cust.custId, schema);

		logger.info("verify customer attribute from db...");

		if (cust0.isAmerican != cust.isAmerican) {
			msg = "Verify isAmerican,";
		}
		if (cust0.isMSResident != cust.isMSResident) {
			msg = "isMSResident,";
		}

		if (!cust0.ethnicity.equalsIgnoreCase(cust.ethnicity)) {
			msg = "ethnicity,";
		}

		if (!cust0.solicitationIndcator
				.equalsIgnoreCase(cust.solicitationIndcator)) {
			msg = "solicitationIndcator,";
		}

		if (!cust0.custGender.equalsIgnoreCase(cust.custGender)) {
			msg = "custGender,";
		}

		if (StringUtil.notEmpty(cust.eyeColor)&&!cust0.eyeColor.equalsIgnoreCase(cust.eyeColor)) {
			msg = "eyeColor,";
		}

		if (StringUtil.notEmpty(cust.height)&&!cust0.height.equalsIgnoreCase(cust.height)) {
			msg = "height,";
		}
		if (StringUtil.notEmpty(cust.attributeGender)&&!cust0.attributeGender.equalsIgnoreCase(cust.attributeGender)) {
			msg = "attributeGender,";
		}

		if (StringUtil.notEmpty(cust.markings)&&!cust0.markings.equalsIgnoreCase(cust.markings)) {
			msg = "markings,";
		}

		if (msg.length() > 0) {
			throw new ErrorOnDataException(msg + " failed.");
		}
		logger.info("Verify sucessfully");
	}

	public void verifyCustomerInfoFromPage(Customer cust, OrmsPage currPage) {
		Customer infoAfterApply = new Customer();
		String msg = "";

		if (currPage instanceof LicMgrCustomerDetailsPage) {
			LicMgrCustomerDetailsPage prifilePage = (LicMgrCustomerDetailsPage) currPage;
			prifilePage.clickApply();
			infoAfterApply = prifilePage.getCustomerProfile(infoAfterApply);

			if (!infoAfterApply.equals(cust)) {
				throw new ErrorOnDataException(
						"The customer Info you entryed is not stored in DB completely");
			}
		} else if (currPage instanceof LicMgrCustomerAdressesPage) {
			LicMgrCustomerAdressesPage addrPage = LicMgrCustomerAdressesPage
					.getInstance();
			addrPage.clickSave();
			infoAfterApply.physicalAddr = addrPage.getCustomerAddress(0);
			infoAfterApply.mailingAddr = addrPage.getCustomerAddress(1);
			infoAfterApply.alternateAddr = addrPage.getCustomerAddress(2);

			if (infoAfterApply.physicalAddr.equals(cust.physicalAddr)) {
				msg += "physicalAddr";
			}

			if (infoAfterApply.mailingAddr.equals(cust.mailingAddr)) {
				msg += ",maillingAddr";
			}

			if (infoAfterApply.alternateAddr.equals(cust.alternateAddr)) {
				msg += ",alternateAddr";
			}
		} else {
			throw new ErrorOnDataException("Unknow Page !");
		}

		if (msg.trim().length() > 0) {
			throw new ErrorOnPageException("verify " + msg + " failed !");
		}
	}

	/**
	 * verify email,phone,
	 * 
	 * @param cust
	 * @param schema
	 * @Return void
	 * @Throws
	 */
	public void verifyPhoneEmailContactInDb(Customer cust, String schema) {
		String msg = "";
		Customer custInDB = getPhoneEmailAndPreferContactByCustID(cust.custId,
				schema);

		logger.info("Verify contact info...");
		if (!custInDB.hPhone.equals(cust.hPhone)) {
			logger.error("Home Phone expected: " + cust.hPhone + " Actual: "
					+ custInDB.hPhone);
			msg = "Verify hPhone,";
		}

		if (!custInDB.bPhone.equals(cust.bPhone)) {
			logger.error("Business Phone expected: " + cust.bPhone
					+ " Actual: " + custInDB.bPhone);
			msg = "bPhone,";
		}

		if (!custInDB.mPhone.equals(cust.mPhone)) {
			logger.error("Mobile Phone expected: " + cust.mPhone + " Actual: "
					+ custInDB.mPhone);
			msg = "mPhone,";
		}

		if (!custInDB.fax.equals(cust.fax)) {
			logger.error("Fax expected: " + cust.fax + " Actual: "
					+ custInDB.fax);
			msg = "fax,";
		}

		if (!custInDB.email.equalsIgnoreCase(cust.email)) {
			logger.error("Email expected: " + cust.email + " Actual: "
					+ custInDB.email);
			msg = "email,";
		}

		if (!custInDB.phoneContact.equals(cust.phoneContact)) {
			logger.error("Phone contract expected: " + cust.phoneContact
					+ " Actual: " + custInDB.phoneContact);
			msg = "phoneContact,";
		}

		if (!custInDB.contactTime.equals(cust.contactTime)) {
			logger.error("Contract time expected: " + cust.contactTime
					+ " Actual: " + custInDB.contactTime);
			msg = "contactTime,";
		}
		if (msg.length() > 0) {
			throw new ErrorOnDataException(msg + " failed.");
		}
		logger.info("Verify sucessfully");
	}

	/**
	 * verify customer profile from db.
	 * 
	 * @param cust
	 * @Return void
	 * @Throws
	 */
	public void verifyCustomerProfile(Customer cust, String schema) {
		String msg = "";

		Customer customer = getCustmerProfile(cust.custId, schema);

		logger.info("verify customer profile.....");

		if (!customer.custProfileStatus
				.equalsIgnoreCase(cust.custProfileStatus)) {
			msg += "Verify custProfileStatus,";
		}

		if (!customer.customerClass.equalsIgnoreCase(cust.customerClass)) {
			msg += "customerClass,";
		}
		if (customer.overrideReqId != cust.overrideReqId) {
			msg += "overrideReqId,";
		}
		if (!customer.overideReason.equalsIgnoreCase(cust.overideReason)) {
			msg += "overideReason,";
		}
		if (!customer.dateOfBirth.equalsIgnoreCase(cust.dateOfBirth)) {
			msg += "dateOfBirth,";
		}

		if (!customer.suffix.equalsIgnoreCase(cust.suffix)) {
			msg += "suffix,";
		}

		if (msg.length() > 0) {
			throw new ErrorOnDataException(msg + " failed.");
		}
		logger.info("Verify sucessfully");
	}

	public void gotoLicenseManageHomePage() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		logger.info("Go to home page.");
		topMenu.clickHome();
		homePg.waitLoading();

	}

	/**
	 * finish new customer, the work flow start from customer new page or
	 * customer details page, end in customer search page
	 * 
	 * @Return void
	 * @Throws
	 */
	public String finishAddOrEditCustomer() {
		LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("finish customer new process...");
		if (newCustPg.exists()) {
			newCustPg.clickApply();
			ajax.waitLoading();
			Object page = browser.waitExists(custDetailsPg, newCustPg);
			if (page == newCustPg) {
				return newCustPg.getErrorMsg();
			}
		}
		if (custDetailsPg.checkWarnMes()) {
			return custDetailsPg.getWarnMsg();
		}
		String custId = custDetailsPg.getCustomerNumber();
		custDetailsPg.clickOK();
		// custDetailsPg.clickCancel();
		ajax.waitLoading();
		customerPg.waitLoading();
		return custId;
	}

	/**
	 * This method is used to make privilege order from privileges quick sear
	 * area to order cart. Including these steps as below: 1. Click 'Purchase
	 * Privilege' link from privilege quick search area; 2. Set up identifier
	 * details in Identify Customer page and click 'OK'; 3. At Add Item page,
	 * add privilege products to order cart; 4. Click 'Go To Cart' link to goto
	 * Order Cart page.
	 * 
	 * @param privilege
	 * @param customer
	 * @return
	 */
	public List<String> makePrivilegeOrderToCartFromPrivilegeQuickSearch(
			boolean isMakeDonation, Customer customer,
			PrivilegeInfo... privileges) {

		logger
				.info("Making privilege order from privilege quick search area to order cart.");
		for (int i = 0; i < privileges.length; i++) {
			logger.info("Privilege info"
					+ ((privileges.length > 1) ? "#" + i : "") + ": "
					+ privileges[i].toString());
		}
		this.gotoAddItemPgFromPrivilegeQuickSearch(customer);
		List<String> inventoryNums = this.addPrivilegeItem(privileges);
		this.goToCart(isMakeDonation);
		return inventoryNums;
	}

	public List<String> makePrivilegeOrderToCartFromPrivilegeQuickSearch(
			Customer customer, PrivilegeInfo... privileges) {
		return makePrivilegeOrderToCartFromPrivilegeQuickSearch(false, customer,
				privileges);
	}

	public void makeConsumableOrderToCartFromQuickSearch(ConsumableInfo... consumables) {
		makeConsumableOrderToCartFromQuickSearch(null, consumables);
	}
	
	/**
	 * This method is used to make consumable order from consumables quick
	 * search area to order cart. Including these steps as below: 1. Click
	 * 'Purchase Consumables' link from consumable quick search area; 2. At Add
	 * Item page, add consumable products to order cart; 3. Click 'Go To Cart'
	 * link to goto Order Cart page.
	 * 
	 * @param consumables
	 */
	public void makeConsumableOrderToCartFromQuickSearch(Customer cust,
			ConsumableInfo... consumables) {
		logger
				.info("Making consumable order from privilege quick search area to order cart.");
		for (int i = 0; i < consumables.length; i++) {
			logger.info("Consumable info"
					+ ((consumables.length > 1) ? "#" + i : "") + ": "
					+ consumables[i].toString());
		}

		this.gotoAddItemPgFromConsumableQuickSearch();
		this.makeConsumableOrderToCartFromAddItemPage(cust, consumables);
	}

	public void makeConsumableOrderToCartFromAddItemPage(ConsumableInfo... consumables) {
		makeConsumableOrderToCartFromAddItemPage(null, consumables);
	}
	
	public void makeConsumableOrderToCartFromAddItemPage(Customer cust, 
			ConsumableInfo... consumables) {
		this.addConsumableItem(cust, consumables);
		this.goToCart();
	}

	public void goToCart() {
		goToCart(false);
	}

	/**
	 * Click 'Go To Cart' to go to cart
	 */
	public void goToCart(boolean isMakeDonation) {
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage orgAddPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrReplacePrivSaleAddItemPage repAddPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();
		LicMgrConsumableSaleAddItemPage consumPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		LicMgrDonationDialogWidget donationWidget = LicMgrDonationDialogWidget
				.getInstance();

		Object page = browser.waitExists(orgAddPg, repAddPg, consumPg);
		((LicMgrAddItemPage) page).clickGotoCart();
		ajax.waitLoading();

		page = browser.waitExists(ormsOrderCartPg, donationWidget);
		if (page == donationWidget) {
			if (isMakeDonation) {
				donationWidget.clickYes();
				ajax.waitLoading();
				consumPg.waitLoading();
			} else {
				donationWidget.clickNo();
				ajax.waitLoading();
				ormsOrderCartPg.waitLoading();
			}
		}
	}

	public void addConsumableItem(ConsumableInfo... consumables) {
		addConsumableItem(null, consumables);
	}
	
	/**
	 * Add consumable item to cart(ConsumableInfo is one or more...)
	 * 
	 * @param consumables
	 */
	public void addConsumableItem(Customer cust, ConsumableInfo... consumables) {
		// add consumable item(s) action
		for (ConsumableInfo consumable : consumables) {
			this.addConsumableItem(consumable, cust);
		}
	}

	public void addConsumableItem(ConsumableInfo consumable) {
		addConsumableItem(consumable, null);
	}
	
	/**
	 * Add consumable item to cart
	 * 
	 * @param consumable
	 */
	public void addConsumableItem(ConsumableInfo consumable, Customer cust) {
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		LicMgrPrivilegeQuestionWidget questWidget = LicMgrPrivilegeQuestionWidget
				.getInstance();
		LicMgrPrivilegeQuestionConfirmWidget questConfirmWidget = LicMgrPrivilegeQuestionConfirmWidget
				.getInstance();
		LicMgrSubscriberInfoWidget subscriberInfoWidget = LicMgrSubscriberInfoWidget.getInstance();
		
		logger.info("Add consumable item - " + consumable.name + ".");

		// If the consumable's price is empty, set the quantity and add it.
		// Otherwise, set the price and add it. Update by Lesley Wang
		if (StringUtil.isEmpty(consumable.price)) {
			addItemPg.addProductToCart(consumable.name, consumable.quantity);
		} else {
			addItemPg.setProductPriceAndAddToCart(consumable.name,
					consumable.price);
		}

		Object page = browser.waitExists(questWidget, subscriberInfoWidget, addItemPg);

		if (page == questWidget) {
			if (consumable.questions.length > 0) {
				for (int i = 0; i < consumable.questions.length; i++) {
					questWidget.answerPrivilegeQuestion(
							consumable.questions[i].questDisplayText,
							consumable.questions[i].questionType,
							consumable.questions[i].questAnswer);
					questWidget.clickDone();
					ajax.waitLoading();
					page = browser.waitExists(questWidget, questConfirmWidget,
							addItemPg);
				}
				if (page == questConfirmWidget) {
					questConfirmWidget.clickOK();
					ajax.waitLoading();
					addItemPg.waitLoading();
				}
			} else {
				throw new ActionFailedException(
						"This privilege need to initial question");
			}
		}
		
		if(page == subscriberInfoWidget) {
			subscriberInfoWidget.setSubscriberInfo(cust);
			subscriberInfoWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
	}

	/**
	 * Add privilege item to cart(PrivilegeInfo is one or more...)
	 * 
	 * @param privileges
	 */
	public List<String> addPrivilegeItem(PrivilegeInfo... privileges) {
		// add privilege item(s) action
		List<String> inventoryNums = new ArrayList<String>();
		for (PrivilegeInfo priv : privileges) {
			inventoryNums.add(this.addPrivilegeItem(priv));
		}
		
		return inventoryNums;
	}

	/**
	 * Add privilege item to cart
	 * 
	 * @param privilege
	 * @return
	 */
	public String addPrivilegeItem(PrivilegeInfo privilege) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget
				.getInstance();
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
				.getInstance();
		LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime
				.getInstance();
		LicMgrPrivilegeQuestionConfirmWidget privQuestConfirmWidget = LicMgrPrivilegeQuestionConfirmWidget
				.getInstance();
		LicMgrPrivilegeEducationWidget eduWidget = LicMgrPrivilegeEducationWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance(true);
		LicMgrPrivilegeAuthorizationWidget privAuthorizWidget = LicMgrPrivilegeAuthorizationWidget.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrPriLandownerDeclarationWidget landownerDeclarationWidget = LicMgrPriLandownerDeclarationWidget.getInstance();
		LicMgrSelectAllocationLicenseWidget selectAllocationLicWidget = LicMgrSelectAllocationLicenseWidget.getInstance();
		LicMgrSelectFulfillmentMethodWidget selectFulfillmentLicWidget = LicMgrSelectFulfillmentMethodWidget.getInstance();
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();//for purchase leftover privilege
		LicMgrSelectPrimaryPrivilegeWidget selectPrimaryPrivWidget = LicMgrSelectPrimaryPrivilegeWidget.getInstance();
		
		logger.info("Add privilege item - " + privilege.purchasingName + ".");
	
		//james[20130712]: remove unnecessary waiting to improve performance
//		addItemPg.waitExists();

		addItemPg.addProductToCart(privilege.purchasingName,
				privilege.licenseYear, privilege.qty);

		Object page = browser.waitExists(selectHuntPage, validFromDateTime, selectFulfillmentLicWidget, privInvWidget, privQuestWidget, eduWidget, 
				addItemPg, privAuthorizWidget, selectAllocationLicWidget, selectPrimaryPrivWidget, confirmDialogWidget, landownerDeclarationWidget);

		//Lesley[20130923]: handle the privilege authorization widget. The widget is no title, so put the code before confirm dialog widget
		if (page == privAuthorizWidget) {
			logger.info("Input the customer info: number=" + privilege.authorizedCust.custNum + "; date of birth=" + privilege.authorizedCust.dateOfBirth + " who will be authorized to purchase the privielge...");
			privAuthorizWidget.setCustInfo(privilege.authorizedCust.custNum, privilege.authorizedCust.dateOfBirth);
			privAuthorizWidget.clickOK();
			ajax.waitLoading();
			confirmCustPg.waitLoading();
			confirmCustPg.clickOK();
			ajax.waitLoading();
			Object ps = browser.waitExists(addItemPg, confirmDialogWidget);
			if(ps==confirmDialogWidget) {
				logger.info("The customer already has INP-IndivPrivAuth licence on file. Click 'Continue' to purchase another RAL for the same customer.");
				confirmDialogWidget.clickContinue();
				ajax.waitLoading();
				addItemPg.waitLoading();
			}
		}
		
		//Jane[2014-4-23]:Added for purchase authorized privilege, please refer to Setup+Outfitter+Allocations for more details
		//The widget is no title, so put the code before confirm dialog widget
		if(page == selectAllocationLicWidget) {
			selectAllocationLicWidget.selectAllocationLicenseByAuthNum(privilege.authPrivNum);
			selectAllocationLicWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
		
		//Quentin[20140528]: Partner Licence: need to select an purchased primary privilege number before purchasing an associated privilege
		if(page == selectPrimaryPrivWidget) {
			selectPrimaryPrivWidget.searchPrivilegeNum(privilege.primaryPrivilegeNum);
			selectPrimaryPrivWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
		
		if (page == confirmDialogWidget) {
			//james[20130712], confirmDialogWidget may have yes/no options to select. we just go defaul selecting yes
			if(confirmDialogWidget.hasRadioOptions()) {
				confirmDialogWidget.selectYesRadioOption();
			}
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(validFromDateTime, privInvWidget,
					privQuestWidget, addItemPg,confirmDialogWidget);
		}
		
		//james[20130715], there may be another confirmDialogWidget appears
		if (page == confirmDialogWidget) {
			if(confirmDialogWidget.hasRadioOptions()) {
				confirmDialogWidget.selectYesRadioOption();
			}
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(validFromDateTime, privInvWidget,
					privQuestWidget, addItemPg,confirmDialogWidget);
		}

		if (page == eduWidget) {
			if (null != privilege.cust) {
				eduWidget.setEducationInfo(privilege.cust.education);
				ajax.waitLoading();
				page = browser.waitExists(validFromDateTime, privInvWidget,
						privQuestWidget, addItemPg);
			} else {
				throw new ErrorOnPageException("No customer info exist");
			}
		}

		if (page == validFromDateTime) {
			validFromDateTime.setValidFromDateTime(privilege.validFromDate);
			// validFromDateTime.clickToplabel(); //comment out and handle
			// calendar with setValidFromDateTime()
			if (privilege.validFromTime != null
					&& privilege.validFromTime.length() > 0) {
				validFromDateTime.setValidFromTime(privilege.validFromTime);
				validFromDateTime.selectAmPm(privilege.validFromAmPm);
			}
			validFromDateTime.clickOK();
			ajax.waitLoading();
			// privInvWidget.selectInventoryNumber(privilege.inventoryNum);
			// privInvWidget.clickOK();
			// ajax.waitLoading();
			page = browser
					.waitExists(privQuestWidget, privInvWidget, addItemPg);
		}

		if (page == privQuestWidget) {
			if (privilege.privilegeQuestions.length > 0) {
				for (int i = 0; i < privilege.privilegeQuestions.length; i++) {
					privQuestWidget.answerPrivilegeQuestion(
							privilege.privilegeQuestions[i].questDisplayText,
							privilege.privilegeQuestions[i].questionType,
							privilege.privilegeQuestions[i].questAnswer);
					if (privQuestWidget.checkDoneExist()) {
						privQuestWidget.clickDone();
					} else {
						privQuestWidget.clickOK();
					}

					ajax.waitLoading();
					page = browser.waitExists(privQuestWidget,
							privQuestConfirmWidget, validFromDateTime,
							addItemPg);
					if (page == privQuestConfirmWidget) {
						privQuestConfirmWidget.clickOK();
						ajax.waitLoading();
						page = browser.waitExists(privQuestWidget, addItemPg,
								validFromDateTime);
					}
					if (page == validFromDateTime) {
						validFromDateTime
								.setValidFromDateTime(privilege.validFromDate);
						// validFromDateTime.clickToplabel(); //comment out and
						// handle calendar with setValidFromDateTime()
						if (privilege.validFromTime != null
								&& privilege.validFromTime.length() > 0) {
							validFromDateTime
									.setValidFromTime(privilege.validFromTime);
							validFromDateTime
									.selectAmPm(privilege.validFromAmPm);
						}
						validFromDateTime.clickOK();
						ajax.waitLoading();
						// privInvWidget.selectInventoryNumber(privilege.inventoryNum);
						// privInvWidget.clickOK();
						// ajax.waitLoading();
						page = browser.waitExists(privQuestWidget, addItemPg);
					}
				}

			} else {
				throw new ActionFailedException(
						"This privilege need to initial question");
			}
		} //TODO
		
		//Jane[2014-4-24] Add for purchase privilege with inventory fulfillment method
		if(page == selectFulfillmentLicWidget) {
			if(StringUtil.notEmpty(privilege.inventoryQty)) {
				selectFulfillmentLicWidget.selectInvQty(privilege.inventoryQty);
				ajax.waitLoading();
			}
			if(StringUtil.isEmpty(privilege.fulfillmentMethod)) {
				logger.info("Select fulfillment method as 'Select Immediately'");
				privilege.fulfillmentMethod = "Select Immediately";
			}
			logger.info("Select fulfillment method as "+privilege.fulfillmentMethod);
			selectFulfillmentLicWidget.selectInvFulfillMethod(privilege.fulfillmentMethod);
			ajax.waitLoading();
			if(selectFulfillmentLicWidget.isInvNumTextFieldExist()){
				selectFulfillmentLicWidget.setInventoryNums(privilege.inventoryNum);
			}
			selectFulfillmentLicWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(privInvWidget, addItemPg);
		}
		
		if (page == privInvWidget) {// Nicole added, 305
			if(!privilege.useDefaultInventory) {
				privilege.inventoryNum = privInvWidget.selectInventoryNumber(privilege.inventoryNum);
				if (StringUtil.isEmpty(privilege.inventoryNum)) {
					throw new ItemNotFoundException(
							"Failed to get inventory number.");
				}
			}
			privInvWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
		
		if(landownerDeclarationWidget.exists()){
			if(null != privilege.landownerDeclaration && privilege.landownerDeclaration.size() > 0){
				landownerDeclarationWidget.setLandownerDelaration(privilege.landownerDeclaration);
			}
			landownerDeclarationWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(landownerDeclarationWidget, addItemPg);
		}
		
		return privilege.inventoryNum;
	}
	
	/**
	 * Jane[20140417]: added for leftover privilege, will select one hunt for privilege sale
	 * @param hunt
	 */
	public void selectHuntForPrivilegeSale(String huntCode) {
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		logger.info("Select hunt code "+huntCode);
		selectHuntPage.searchHunt(huntCode);
		selectHuntPage.selectHunt(huntCode);
		selectHuntPage.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}

	public void addPrivilegeLotteryItemToCart(HFLotteryProduct lottery) {
		logger.info("Add privilege lottery item - " + lottery.getPurchasingName() + " to Cart.");
		this.addPrivilegeLotteryItemToManageChoicePage(lottery);
		this.addPrivilegeLotteryItemToCartFromManageChoicePage();
	}
	
	public void addHuntToManageChoicePage(HFLotteryProduct lottery){
		LicMgrPrivilegeLotterySelectHuntPage selectHuntPage = LicMgrPrivilegeLotterySelectHuntPage.getInstance();
		LicMgrPrivilegeLotteryManageChoicePage manageChoicePage = LicMgrPrivilegeLotteryManageChoicePage.getInstance();
		LicMgrPrivilegeLotterySelectApplicantTypePage selectApplicantTypePage = LicMgrPrivilegeLotterySelectApplicantTypePage.getInstance();
		if(selectApplicantTypePage.exists()){
			selectApplicantTypePage.selectApplicantType(lottery.getApplicantType());
			ajax.waitLoading();
			selectApplicantTypePage.clickNext();
			ajax.waitLoading();
			selectHuntPage.waitLoading();
		}
		if(lottery.getGroupMembers().size()>0){
			selectHuntPage.setupGroupMembers(lottery.getGroupMembers());
		}
		selectHuntPage.unSelectAllHunts();
		selectHuntPage.unSelectAllPointTypes();
		if(lottery.getPointTypes().size()>0){
			for(int i=0; i<lottery.getPointTypes().size(); i++){
				selectHuntPage.selectPointTypes(lottery.getPointTypes().get(i));
				ajax.waitLoading();
			}
		}
		if(lottery.getHuntCodes().size()>0){
			for(int i=0; i<lottery.getHuntCodes().size(); i++){
				selectHuntPage.selectHunts(lottery.getHuntCodes().get(i));
				ajax.waitLoading();
			}
		}
//		selectHuntPage.selectHunts(lottery.getHuntCodes().toArray(new String[0]));
		
		selectHuntPage.clickOK();
		ajax.waitLoading();
		manageChoicePage.waitLoading();
	}
	
	public void addPrivilegeLotteryItemToManageChoicePage(HFLotteryProduct lottery) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrPrivilegeLotterySelectHuntPage selectHuntPage = LicMgrPrivilegeLotterySelectHuntPage.getInstance();
		LicMgrPrivilegeLotterySelectApplicantTypePage selectApplicantTypePage = LicMgrPrivilegeLotterySelectApplicantTypePage.getInstance();
		
		logger.info("Add privilege lottery item - " + lottery.getPurchasingName() + " to Manage Choice page.");
		addItemPg.addProductToCart(lottery.getPurchasingName(), lottery.getLicenseYear(), null);
		browser.waitExists(selectApplicantTypePage, selectHuntPage);
		this.addHuntToManageChoicePage(lottery);
	}
	
	public String addPrivilegeLotteryItemToCartFromManageChoicePage() {
		LicMgrPrivilegeLotteryManageChoicePage manageChoicePage = LicMgrPrivilegeLotteryManageChoicePage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Add privilege lottery item to Cart from Manage Choice page.");
		manageChoicePage.clickAddToCart();
		ajax.waitLoading();
		Object page = browser.waitExists(manageChoicePage, cartPage);
		String toReturn = "";
		if(page == manageChoicePage) {
			toReturn = manageChoicePage.getErrorMsg();
		}
		
		return toReturn;
	}
	
	/**
	 * Add privilege text display record in privilege add text display widget
	 * 
	 * @param textDisplay
	 * @return
	 */
	public String addPrivilegeTextDisplay(PrivilegeTextDisplay textDisplay) {
		return this.addPrivilegeTextDisplay(textDisplay, true);
	}

	/**
	 * Set privilege text display detail information in Privilege Add Text
	 * Display Widget, Click OK or Cancel button
	 * 
	 * @param textDisplay
	 * @param isClickOK
	 * @return
	 */
	public String addPrivilegeTextDisplay(PrivilegeTextDisplay textDisplay,
			boolean isClickOK) {
		LicMgrPrivilegeTextDisplayPage privilegeTextDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeAddTextDisplayWidget addTextDisplayWidget = LicMgrPrivilegeAddTextDisplayWidget
				.getInstance();

		String logMsg = isClickOK ? "Add privilege text diplay record in LicMgrPrivilegeAddTextDisplayWidget."
				: "Set up privilege text display details info and then cancel.";
		logger.info(logMsg);
		privilegeTextDisplayPg.clickAddTextDisplay();
		ajax.waitLoading();
		addTextDisplayWidget.waitLoading();
		addTextDisplayWidget.setupTextDisplayInfo(textDisplay);
		if (isClickOK) {
			addTextDisplayWidget.clickOK();
		} else {
			addTextDisplayWidget.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(addTextDisplayWidget,
				privilegeTextDisplayPg);
		if ((page == privilegeTextDisplayPg) && isClickOK) {
			return privilegeTextDisplayPg
					.getTextDisplayIdByText(textDisplay.text);
		}
		if (page == addTextDisplayWidget) {
			return addTextDisplayWidget.getErrorMessage();
		}

		return "";
	}

	/**
	 * Deactivate privilege text display record identified by text display id
	 * 
	 * @param id
	 */
	public void deactivatePrivilegeTextDisplay(String... ids) {
		LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget textDisplayDetailWidget = LicMgrPrivilegeEditTextDisplayWidget
				.getInstance();

		textDisplayPg.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		textDisplayPg.selectStatus(ACTIVE_STATUS);
		textDisplayPg.clickGo();
		ajax.waitLoading();
		textDisplayPg.waitLoading();

		for (String id : ids) {
			logger.info("Deactivate privilege text display(ID#= " + id + ").");
			textDisplayPg.clickTextDisplayId(id);
			ajax.waitLoading();
			textDisplayDetailWidget.waitLoading();
			textDisplayDetailWidget
					.selectStatus(INACTIVE_STATUS);
			textDisplayDetailWidget.clickOK();
			ajax.waitLoading();
			textDisplayPg.waitLoading();
		}
	}

	/**
	 * Deactivate privilege text display record by text
	 * 
	 * @param displayTexts
	 */
	public void deactivatePrivilegeTextDisplayByText(String... displayTexts) {
		LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();

		List<String> ids = new ArrayList<String>();
		String tempID = "";
		for (String text : displayTexts) {
			logger.info("Deactivate privilege text display(text= " + text
					+ ").");
			tempID = textDisplayPg.getTextDisplayIdByText(text);
			if (tempID.length() > 0) {
				ids.add(tempID);
			}
		}
		if (ids.size() > 0) {
			this.deactivatePrivilegeTextDisplay(ids.toArray(new String[0]));
		}
	}

	/**
	 * @author Peter Zhu Deactivate all active privilege text display records
	 */
	public void deactivatePrivilegeTextDisplayRecords() {
		LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();

		logger.info("Deactivate all active privilege text display records.");
		textDisplayPg.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		textDisplayPg.selectStatus(ACTIVE_STATUS);
		textDisplayPg.clickGo();
		ajax.waitLoading();
		List<String> ids = textDisplayPg.getTextDisplayIDs();
		if (ids.size() > 0) {
			this.deactivatePrivilegeTextDisplay(ids.toArray(new String[0]));
		}
		logger.info("There is not any matched record.");
	}

	/**
	 * Go to privilege text display sub page from selecting 'Products' option in
	 * 'Admin' drop down list in top menu
	 * 
	 * @param privilegeCode
	 */
	public void gotoPrivilegeTextDisplayPage(String privilegeCode) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeTextDisplayPage privilegeTextDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();

		this.gotoPrivilegeSearchListPageFromTopMenu();
//		privilegeDetailsPg.clickTextDisplayTab();//Sara[20140806] this is not correct code step because it is privilege list page, not privilege details page, no text display tab
		this.gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);
		privilegeDetailsPg.clickTextDisplayTab();
		ajax.waitLoading();
		privilegeTextDisplayPg.waitLoading();
	}

	/**
	 * Get text display details info by clicking text display id in
	 * LicMgrPrivilegeTextDisplayPage to goto
	 * LicMgrPrivilegeEditTextDisplayWidget
	 * 
	 * @param textDisplayID
	 * @return
	 */
	public PrivilegeTextDisplay getPrivilegeTextDisplayDetail(
			String textDisplayID) {
		LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget textDisplayDetailWidget = LicMgrPrivilegeEditTextDisplayWidget
				.getInstance();

		logger
				.info("Get Privilege Text Display details info from LicMgrPrivilegeEditTextDisplayWidget.");
		textDisplayPg.clickTextDisplayId(textDisplayID);
		textDisplayDetailWidget.waitLoading();
		PrivilegeTextDisplay textDisplay = textDisplayDetailWidget
				.getTextDisplayInfo();
		textDisplayDetailWidget.clickCancel();
		textDisplayPg.waitLoading();

		return textDisplay;
	}

	public void verifyAddedCorrectPrivilegeAndQty(String expectPrivName,
			String licenseYear, String expectQty, int itemsIndex) {
		this.verifyAddedCorrectPrivilegeConsumableAndQty(expectPrivName,
				licenseYear, expectQty, false, itemsIndex);
	}

	public void verifyAddedCorrectConsumableAndQty(String expectedConsumable,
			String expectedLicYear, String expectedQty, int itemIndex) {
		this.verifyAddedCorrectPrivilegeConsumableAndQty(expectedConsumable,
				expectedLicYear, expectedQty, false, itemIndex);
	}

	/**
	 * Verify the correct privilege and correct privilege qty is added in cart
	 * 
	 * @param expectPrivName
	 * @param licenseYear
	 * @param expectQty
	 * @param isDuplicate
	 * @param itemsIndex
	 *            - shows the index of current item is added to order cart
	 * @return
	 */
	public void verifyAddedCorrectPrivilegeConsumableAndQty(
			String expectPrivName, String licenseYear, String expectQty,
			boolean isDuplicate, int itemsIndex) {
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger
				.info("Verify correct privilege and correct Qty is added in cart.");

		//James [20130704]: now privilege info contains valid dates
		String privInCart = ormsOrderCartPg.getPrivilegesInOrderCart()[itemsIndex - 1]
				.replaceAll("Remove.*", "").trim().replace(") ", ")");
		int qtyInCart = ormsOrderCartPg
				.getProductItemQtyInCart(itemsIndex - 1);
		String expectedName = "(" + licenseYear + ")" + expectPrivName
				+ (isDuplicate ? " (Duplicate)" : "");

		boolean result = true;
		if (!privInCart.contains(expectedName)) {
			result = false;
			logger.error("The newest added privilege name is WRONG. Expected is: "
							+ expectedName + ", but actual is: " + privInCart);
		}
		if (Integer.parseInt(expectQty) != qtyInCart) {
			result = false;
			logger.error("The newest added privilege Qty is WRONG. Expected is: "
							+ expectQty + ", but actual is: " + qtyInCart);
		}
		
		if(!result){
			throw new ErrorOnPageException("The added privilege(" + expectPrivName + ") and Qty("
					+ expectQty + ") is not correct. Please check logs above.");
		} else {
			logger.info("The added privilege(" + expectPrivName + ") and Qty("
					+ expectQty + ") is correct.");
		}
	}

	/**
	 * Verify whether a specific privilege exists in order cart
	 * 
	 * @param purchasingName
	 * @param licenseYear
	 * @param isExist
	 */
	public void verifyPrivilegeExistsInCart(String purchasingName,
			String licenseYear, boolean isExist) {
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();

		logger.info("Verify privilege - " + purchasingName
				+ (isExist ? " should" : " should NOT")
				+ " exist in order cart.   ");
		String namesInCart[] = orderCartPage.getPrivilegesInOrderCart();
		String expectedName = "(" + licenseYear + ")" + purchasingName;
		for (String name : namesInCart) {
			if (isExist) {
				if (!name.replaceAll("Remove", "").contains(expectedName)) {
					throw new ErrorOnPageException(
							"The privilege should exist in cart.");
				} else
					logger.info("The privilege really exist in cart.");
			} else {
				if (name.replaceAll("Remove", "").contains(expectedName)) {
					throw new ErrorOnPageException(
							"The privilege should NOT exist in cart.");
				} else
					logger.info("The privilege really NOT exist in cart.");
			}
		}
	}

	/**
	 * Get the information of the from and to date
	 */
	public List<String> getAddedPrivilegeFromToDate(int itemsIndex) {
		List<String> fromTo = new ArrayList<String>();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Verify privilege from and to date in cart.");
		List<String> details = ormsOrderCartPg.getOrderItemDetails();
		// ITable orderTable = ormsOrderCartPg.getOrderTable();
		// MiscFunctions.dumpTable(orderTable);
		if (details.size() < 1) {
			throw new ItemNotFoundException("Order table object not found.");
		}
		// if (itemsIndex >= 1 && itemsIndex <= 2) {
		// fromTo.add(orderTable.getCellValue(5 * itemsIndex - itemsIndex + 1,
		// 1).split("to")[0].split(":")[1].trim());
		// fromTo.add(orderTable.getCellValue(5 * itemsIndex - itemsIndex + 1,
		// 1).split("to")[1].trim());
		// } else if (itemsIndex > 2) {
		// fromTo.add(orderTable.getCellValue(5 * itemsIndex - itemsIndex + 1,
		// 0).split("to")[0].split(":")[1].trim());
		// fromTo.add(orderTable.getCellValue(5 * itemsIndex - itemsIndex + 1,
		// 0).split("to")[1].trim());
		// } else {
		// throw new ItemNotFoundException(
		// "Cannot fund the valid from or to date!");
		// }

		String text = details.get(itemsIndex * 2 - 1);
		String[] tokens = text.substring(8).split("to");
		fromTo.add(tokens[0].trim());
		fromTo.add(tokens[1].trim());

		return fromTo;
	}

	/**
	 * Get all privileges' from and to date from datebase
	 * 
	 * @param priviNums
	 * @return - privilege datacollection
	 */
	public LicenseYear getPriviValidFromToDate(String priviNum, String schema) {
		// List<PrivilegeInfo> list = new ArrayList<PrivilegeInfo>();
		String[] col = { "valid_from", "valid_to" };
		logger.info("Get valid from and to date:");
		db.resetSchema(schema);

		String query = "select VALID_FROM, VALID_TO from o_priv_inst "
				+ " where id=" + priviNum;

		List<String[]> fromToDate = db.executeQuery(query, col);

		LicenseYear licYear = new LicenseYear();
		licYear.validFromDate = fromToDate.get(0)[0].split(" ")[0];
		licYear.validToDate = fromToDate.get(0)[1].split(" ")[0];

		return licYear;
	}

	/**
	 * Verify valid from and to date from database
	 */
	public void verifyValidFromToDateFromDB(String[] priviNums, String schema,
			PrivilegeInfo... privileges) {
		List<LicenseYear> list = new ArrayList<LicenseYear>();

		logger.info("Verify valid from and to date from database.");

		for (int i = 0; i < priviNums.length; i++) {
			list.add(this.getPriviValidFromToDate(priviNums[i], schema));
		}

		if (list.size() != privileges.length) {
			throw new ErrorOnDataException("the lengths are not equal!");
		}

		boolean result = true;
		for (int i = 0; i < list.size(); i++) {
			if (!MiscFunctions.compareResult("Valid From Date of "+privileges[i].code, privileges[i].licYear.validFromDate, list.get(i).validFromDate)){
				result &= false;
//				throw new ErrorOnDataException("The valid from date is not equal to excepted one!");
			}
			if(!MiscFunctions.compareResult("Valid To Date of "+privileges[i].code, privileges[i].licYear.validToDate, list.get(i).validToDate)){
				result &= false;
//				throw new ErrorOnDataException("The valid to date is not equal to excepted one!");
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Valid From/To date isn't correct, please check logs above!");
		}

		logger.info("Verify valid from and to date successfully!");
	}
	
	
	public void gotoAddItemPgFromCustDetailsPgAndDealWithAwardLottery(String recidencyStatus, CustomerIdentifier iden, String actionWithLottery, String ordNum){
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrIdentifyCustomerPage identifyPg = LicMgrIdentifyCustomerPage
				.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrAcceptDeclineAwardWidget awardWidget = LicMgrAcceptDeclineAwardWidget.getInstance();

		custDetailPg.clickPurchasePrivilege();
		ajax.waitLoading();
		identifyPg.waitLoading();
		identifyPg.selectResidencyStatus(recidencyStatus);
		ajax.waitLoading();
		identifyPg.setResidencyStatusRelatedIden(iden);
		ajax.waitLoading();
		identifyPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(awardWidget, addItemPg);//Quentin[20140320] product changes
		while(page == awardWidget) {
			if(actionWithLottery.equalsIgnoreCase("Cancel")){
				awardWidget.clickCancel();
			}else{
				String order = awardWidget.getApplicationOrder();
				if(ordNum.equalsIgnoreCase(order)){//It is the order that the case want to 'Accept' or 'Decline'
					if(actionWithLottery.equalsIgnoreCase("Accept")){
						awardWidget.clickAcceptAward();
					}
					if(actionWithLottery.equalsIgnoreCase("Decline")){
						awardWidget.clickDeclineAward();
					}
				}else{
					awardWidget.clickCancel();
				}
			}
			ajax.waitLoading();
			page = browser.waitExists(awardWidget, addItemPg);
		}
	}
		
	public void declinedAwardLotteryFromAddItemPgToCustDetailPg(String recidencyStatus, CustomerIdentifier iden, String orderNum){
		gotoAddItemPgFromCustDetailsPgAndDealWithAwardLottery(recidencyStatus, iden, "Decline", orderNum);
	}

	public void acceptAwardLotteryFromAddItemPgToCustDetailPg(String recidencyStatus, CustomerIdentifier iden, String orderNum){
		gotoAddItemPgFromCustDetailsPgAndDealWithAwardLottery(recidencyStatus, iden, "Accept", orderNum);
	}
	
	public void gotoAddItemPgFromCustDetailPg(String recidencyStatus, CustomerIdentifier iden) {
		gotoAddItemPgFromCustDetailsPgAndDealWithAwardLottery(recidencyStatus, iden, "Cancel", null);
	}

	public void gotoAddItemPgFromCustDetailPg(String recidencyStatus) {
		gotoAddItemPgFromCustDetailPg(recidencyStatus, null);
	}
	/**
	 * This method is used to goto add privilege item page through privilege
	 * quick search, it start from home page and end at add privilege item page.
	 * 
	 * @param customer
	 */
	public void gotoAddItemPgFromPrivilegeQuickSearch(Customer customer) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget
				.getInstance();
		LicMgrCustomerNoteAndAlertPage custAlertPage = LicMgrCustomerNoteAndAlertPage.getInstance();
		LicMgrAlertPopupWidget alertPage = LicMgrAlertPopupWidget.getInstance();
		
		logger
				.info("Goto add privilege item page from privilege quick search.");
		Object page = null;
		if (homePg.checkIdentifyCustomerAreaExistInPrevilegeSection()) {
			homePg.identifyCustomer(customer.customerClass,
					customer.dateOfBirth, customer.identifier.identifierType,
					customer.identifier.identifierNum,
					customer.identifier.country, customer.identifier.state);
			homePg.clickPurchasePrivilege();
			ajax.waitLoading();
//			confirmPg.waitLoading();//Quentin[20131211] handle Note/Alert dialog
			page = browser.waitExists(alertPage, confirmPg);
			if(page == alertPage) {
				alertPage.clickOK();
				ajax.waitLoading();
				confirmPg.waitLoading();
			}
			confirmPg.clickOK();
			ajax.waitLoading();
		} else {
			this.gotoIdentifyCustomerPage();
			logger.info("Identifying customer: " + customer.fName + " "
					+ customer.lName + "," + customer.customerClass + ","
					+ customer.dateOfBirth);
			this.identifyAndConfirmCustomer(customer);
			page = browser.waitExists(alertPage, confirmPg, addItemPg);
			if(page == alertPage) {
				alertPage.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(confirmPg, addItemPg);
			}
			if (page == confirmPg) {
				confirmPg.clickOK();
				ajax.waitLoading();
			}
		}
		page = browser.waitExists(alertPage, custAlertPage, residencyWidget, addItemPg);
		if(page == alertPage) {
			alertPage.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(custAlertPage, residencyWidget, addItemPg);
		}
		if(page == custAlertPage){
			custAlertPage.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
		if (page == residencyWidget) {
			 if (customer.residencyStatus.equalsIgnoreCase("Resident")) {
				residencyWidget.selectResident();
				ajax.waitLoading();
				if (residencyWidget.isAdditionalProofDropdownListExists()) {
					residencyWidget
							.selectAdditionalProof(customer.additionalProofOfResidency);
				}
			} else if(StringUtil.isEmpty(customer.residencyStatus) || customer.residencyStatus.equalsIgnoreCase("Non Resident")){
				residencyWidget.selectNonResident();
				ajax.waitLoading();
			} else{
				residencyWidget.selectResident(customer.residencyStatus);
				ajax.waitLoading();
			}

			residencyWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
	}

	/**
	 * This method is used to goto add consumable item page through consumables
	 * quick search, it start from home page and end at add consumable item
	 * page.
	 * 
	 */
	public void gotoAddItemPgFromConsumableQuickSearch() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();

		logger
				.info("Goto add consumable item page from consumables quick search on the Home page.");

		homePg.clickPurchaseConsumables();
		addItemPg.waitLoading();
	}

	/**
	 * This method is used to make privilege to order cart and its work flow as
	 * below: 1.goto customer detail page from top menu in home page 2.click
	 * purchase privilege goto add item page 3.choose privilege and add to cart
	 * 4.go to cart
	 * 
	 * @param cust
	 * @param privileges
	 */
	public void makePrivilegeToCartFromCustomerTopMenu(Customer cust,
			PrivilegeInfo... privileges) {
		logger.info("Make Privilege Order To Cart from customers in top menu.");

		this.gotoAddItemPgFromCustomerTopMenu(cust);
		this.addPrivilegeItem(privileges);
		this.goToCart();
	}

	/**
	 * This method is used to make privilege to order cart and its work flow as
	 * below: 1.goto customer detail page from top menu in home page 2.click
	 * purchase privilege goto add item page
	 * 
	 * @param cust
	 */
	public void gotoAddItemPgFromCustomerTopMenu(Customer cust) {
		logger.info("Go to add item page from customer top menu.");
		this.gotoCustomerDetailFromTopMenu(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus, cust.identifier); // change it for SK contract, by Lesley
	}

	/**
	 * This method is used to go to add privilege page and its work flow as
	 * below: 1.goto customer detail page from customer quick search in home
	 * page 2.click purchase privilege goto add item page
	 * 
	 * @param cust
	 */
	public void gotoAddItemPgFromCustomersQuickSearch(Customer cust) {
		logger
				.info("Go to add item page from customer quick search on home page.");
		this.gotoCustomerDetailFromCustomersQuickSearch(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
	}

	public void gotoReplacePrivAddItemPgFromOrgiAddPg() {
		LicMgrOrigPrivSaleAddItemPage oriAddItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrReplacePrivSaleAddItemPage replaceAddItemPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();

		logger.info("Goto Replace Add Page From Orginal Add page.");

		oriAddItemPg.clickReplacePrivilege();
		ajax.waitLoading();
		replaceAddItemPg.waitLoading();
	}

	/**
	 * replace privilege identifier by privilege numbers to order cart page from
	 * top menu
	 * 
	 * @param cust
	 * @param privNums
	 *            - privilege number(s)
	 * @return
	 */
	public boolean replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(
			Customer cust, String... privNums) {
		LicMgrReplacePrivSaleAddItemPage replaceAddItemPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();

		logger.info("Replace privilege to cart from customers in top menu.");

		boolean canBeReplaced = true;
		this.gotoCustomerDetailFromTopMenu(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus, cust.identifier); // update to set the identifier info
		this.gotoReplacePrivAddItemPgFromOrgiAddPg();

		for (String pNum : privNums) {
			canBeReplaced &= replaceAddItemPg.replacePrivilegeWithPrivNum(pNum);
		}

		if (canBeReplaced) {
			this.goToCart();
		} else {
			throw new ActionFailedException(
					"These privilege orders can't be replaced.");
		}
		return canBeReplaced;
	}

	/**
	 * This method works the work flow of replacing a original privilege order
	 * to cart: 1. Goto customer detail page from Customers in top menu 2. Click
	 * 'Purchase Privilege' button to goto Replacement Privileges sub page of
	 * Add Item page 3. Check the privilege order exists or not 4. If the
	 * privilege order exists in the replacement table then goto cart page
	 * 
	 * @param cust
	 * @param privilegeOrderNums
	 * @return
	 */
	public boolean replacePrivilegeToCartFromCustomerTopMenu(Customer cust,
			String... privilegeOrderNums) {
		LicMgrReplacePrivSaleAddItemPage replaceAddItemPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();

		logger.info("Replace privilege to cart from customers in top menu.");

		boolean canBeReplaced = true;
		this.gotoCustomerDetailFromTopMenu(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		this.gotoReplacePrivAddItemPgFromOrgiAddPg();

		// James: click ByPassCache is not allowed for automation test. 
//		replaceAddItemPg.clickBypassCache();
//		ajax.waitLoading();
//		replaceAddItemPg.waitExists();

		for (String ordNum : privilegeOrderNums) {
			// replace privilege order in Replacement Privileges sub page of Add
			// Item page
			canBeReplaced &= replaceAddItemPg
					.replacePrivilegeWithOrdNum(ordNum);
		}

		if (canBeReplaced) {
			this.goToCart();
		} else {
			throw new ActionFailedException(
					"These privilege orders can't be replaced.");
		}
		return canBeReplaced;
	}

	/**
	 * This method works the work flow of replacing a original privilege order
	 * from customer quick search 1. Go to customer detail page from customer
	 * quick search 2. Click 'Purchase Privilege' button to goto Replacement
	 * Privileges sub page of Add Item page 3. Replace privilege and answer the
	 * question which the privileges have
	 * 
	 * @param cust
	 * @param privilege
	 * @param privilegeOrderNums
	 */
	public void replacePrivilegeFromCustQuickSearch(Customer cust,
			String privilegeOrderNums, PrivilegeInfo... privilegeInfos) {
		LicMgrReplacePrivSaleAddItemPage addItemPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
				.getInstance();

		logger.info("Replace Privilege From Customer Quick Search");

		this.gotoCustomerDetailFromCustomersQuickSearch(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus, cust.identifier);
		this.gotoReplacePrivAddItemPgFromOrgiAddPg();

		addItemPg.waitLoading();
		addItemPg.replacePrivilegeWithOrdNum(privilegeOrderNums);
		ajax.waitLoading();
		for (PrivilegeInfo privilges : privilegeInfos) {
			if (null != privilges.privilegeQuestions) {
				for (QuestionInfo questionInfo : privilges.privilegeQuestions) {
					privQuestWidget.waitLoading();
					logger.info("Answer question:'"
							+ questionInfo.questDisplayText
							+ "' with the answer: " + questionInfo.questAnswer);
					privQuestWidget
							.answerPrivilegeQuestion(
									questionInfo.questDisplayText,
									questionInfo.questionType,
									questionInfo.questAnswer);
					privQuestWidget.clickDone();
					ajax.waitLoading();
				}
			}
		}
		addItemPg.waitLoading();
	}

	/**
	 * This method works the workflow of replacing a original privilege order to
	 * cart from customer quick search 1. Go to customer detail page from
	 * customer quick search 2. Click 'Purchase Privilege' button to goto
	 * Replacement Privileges sub page of Add Item page 3. Replace privilege and
	 * answer the question which the privileges have 5. Go to cart
	 * 
	 * @param cust
	 * @param privilege
	 * @param privilegeOrderNums
	 */
	public void replacePrivilegeToCartFromCustQuickSearch(Customer cust,
			String privilegeOrderNums, PrivilegeInfo... privilegeInfos) {

		this.replacePrivilegeFromCustQuickSearch(cust, privilegeOrderNums,
				privilegeInfos);
		goToCart();
	}

	/**
	 * Verify the privilege order cannot be replaced in Replacement Privileges
	 * page
	 * 
	 * @param privilegeOrdNum
	 */
	public void verifyPrivilegeOrderCannotBeReplaced(String privilegeOrdNum) {
		LicMgrReplacePrivSaleAddItemPage addItemPg = LicMgrReplacePrivSaleAddItemPage
				.getInstance();

		logger.info("Verify whether the privilege order(#=" + privilegeOrdNum
				+ ") can be replaced or not.");

		boolean canBeReplaced = true;
		// try to replace privilege order in Replacement Privileges sub page of
		// Add Item page
		addItemPg.clickReplacePrivilege();
		ajax.waitLoading();
		canBeReplaced = addItemPg.replacePrivilegeWithOrdNum(privilegeOrdNum);
		if (!canBeReplaced) {
			logger.info("----The privilege order(#=" + privilegeOrdNum
					+ ") is really NOT allowed to be replaced.");
		} else {
			throw new ActionFailedException("The privilege order(#="
					+ privilegeOrdNum + ") should not be replaced.");
		}
	}

	/**
	 * This method is used to make privilege to order cart and its work flow as
	 * below: 1.goto customer profile page from customer quick search section in
	 * home page 2.click purchase privilege goto add item page 3.choose
	 * privilege and add to cart 4.go to cart
	 * 
	 * @param cust
	 * @param privileges
	 */
	public void makePrivilegeToCartFromCustomerQuickSearch(Customer cust,
			PrivilegeInfo... privileges) {

		logger.info("Make Privilege Order To Cart.");

		this.gotoCustomerDetailFromCustomersQuickSearch(cust);
		this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		// add privilege item(s) action
		for (PrivilegeInfo priv : privileges) {
			this.addPrivilegeItem(priv);
		}
		this.goToCart();
	}
	
	public void makePrivilegeLotteryToCartFromCustomerQuickSearch(Customer cust, HFLotteryProduct lottery) {
		logger.info("Make Privilege Lottery order to Cart.");
		
		this.gotoCustomerDetailFromCustomersQuickSearch(cust);
		if(cust.residencyStatus.equals("Non Resident")){//Sara[20140211] SK needs identifier
			this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		}else this.gotoAddItemPgFromCustDetailPg(cust.residencyStatus, cust.identifier);
		
		this.addPrivilegeLotteryItemToCart(lottery);
	}
	
	public void makeActivityToCartFromCustomerQuickSearch(Customer cust, Data<ActivityAttr> activity){
		logger.info("Make Activity order to Cart.");
		
		this.gotoCustomerDetailFromCustomersQuickSearch(cust);
		this.gotoSearchActivityPgFromCustDetailsPg();
		this.addActivityToCart(activity);
	}
	
	/**
	 * Go to activity registration sale page
	 */
	public void gotoSearchActivityPgFromCustDetailsPg(){
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrActivityRegistrationSalePage activitySalePg = LicMgrActivityRegistrationSalePage.getInstance();
		custDetailPg.clickActivityRegistration();
		ajax.waitLoading();
		activitySalePg.waitLoading();
	}
	
	/**
	 * Add activity to cart
	 * @param activityInfo
	 */
	public void addActivityToCart(Data<ActivityAttr> activity){
		LicMgrActivityRegistrationSalePage activitySalePg = LicMgrActivityRegistrationSalePage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		activitySalePg.searchAvailableActivityByName(activity.stringValue(ActivityAttr.activityName)); 
		activitySalePg.clickAddToCart(activity.stringValue(ActivityAttr.activityID) + " - " + activity.stringValue(ActivityAttr.activityName));
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	public void addActivitiesToCart(List<Data<ActivityAttr>> activities){
		LicMgrActivityRegistrationSalePage activitySalePg = LicMgrActivityRegistrationSalePage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		for(int i=0;i<activities.size();i++) {
			Data<ActivityAttr> a = activities.get(i);
			activitySalePg.searchAvailableActivityByName(a.stringValue(ActivityAttr.activityName)); 
			activitySalePg.clickAddToCart(a.stringValue(ActivityAttr.activityID) + " - " + a.stringValue(ActivityAttr.activityName));
			ajax.waitLoading();
			cartPage.waitLoading();
			cartPage.clickAddAnotherActivity();
			activitySalePg.waitLoading();
		}
		this.gotoCartFromTopMenu();
	}
	
	/**
	 * The method execute work flow of goto customer profile page by click
	 * customers button from top menu in home page and then search customer by
	 * identifier
	 * 
	 * @param cust
	 */
	public void gotoCustomerDetailFromTopMenu(Customer cust) {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrCustomersSearchPage searchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrPrivilegeItemSearchPage priItemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		LicMgrAlertPopupWidget alertWidget = LicMgrAlertPopupWidget
				.getInstance();

		logger.info("Goto Customer Profile Page From Top Menu.");

		topMenuPage.clickCustomers();
		ajax.waitLoading();
		Object page = browser.waitExists(searchPg, priItemSearchPg);
		if (page == priItemSearchPg) {
			priItemSearchPg.clickCustomersTab();
		}
		this.searchCustomer(cust);

		if (searchPg.exists()) {
			searchPg.clickCustomerFirstNumer();
			ajax.waitLoading();
			page = browser.waitExists(alertWidget, detailsPg);
		}

		if (alertWidget.exists()) {
			alertWidget.clickOK();
			ajax.waitLoading();
		}
		detailsPg.waitLoading();
	}

	public void gotoHistoricalOrderFromCustomerDetailPg() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerHistoricalOrdersPage orderPg = LicMgrCustomerHistoricalOrdersPage
				.getInstance();
		logger
				.info("Go to historical orders page from customer detail page...");

		custDetailPg.clickHistoricalOrdersTab();
		ajax.waitLoading();
		orderPg.waitLoading();

	}

	/**
	 * The method execute work flow of goto customer profile page by customer
	 * quick search in home page
	 * 
	 * @param customer
	 */
	public void gotoCustomerDetailFromCustomersQuickSearch(Customer customer) {
		LicMgrCustomersSearchPage searchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrAlertPopupWidget alertWidget = LicMgrAlertPopupWidget
				.getInstance();

		logger
				.info("Goto Customer Detail Page from customers quick search area.");

		Object page = gotoCustomerSearchPageFromCustomersQuickSearch(customer);
		if (page == searchPg) {
			searchPg.clickCustomerFirstNumer();
			ajax.waitLoading();
		}
		Object page1 = browser.waitExists(alertWidget, custDetailPg);
		if (page1 == alertWidget) {
			alertWidget.clickOK();
			ajax.waitLoading();
			custDetailPg.waitLoading();
		}
	}

	/**
	 * go back to Customer details page from Privilege Details page by clicking
	 * the Customer MDWFP# link
	 */
	public void gotoCustomerDetailsFromPrivilegeDetails() {
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();

		logger
				.info("Go back to Customer details page from Privilege Details page by clicking the Customer MDWFP# link");

		privilegeItemDetailPg.clickMDWFPNumber();
		ajax.waitLoading();
		custDetailPg.waitLoading();
	}

	/**
	 * The method execute work flow of goto customer profile page by customer
	 * quick search in home page
	 * 
	 * @param custClass
	 * @param idType
	 * @param idNum
	 * 
	 * @return customer Num
	 */
	public String gotoCustomerDetailFromCustomersQuickSearch(String custClass,
			String idType, String idNum) {

		logger
				.info("Goto Customer Detail Page from customers quick search area.");
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		Customer customer = new Customer();
		customer.customerClass = custClass;
		customer.identifier.identifierType = idType;
		customer.identifier.identifierNum = idNum;

		gotoCustomerDetailFromCustomersQuickSearch(customer);
		return custDetailPg.getCustomerNumber();
	}

	public void gotoMergeHistoryPgFromDetailPg()
	{
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
		.getInstance();
		LicMgrCustomerMergeHistoryPage mergeHistoryPg =LicMgrCustomerMergeHistoryPage.getInstance();
		
		logger.info("Goto merge history page form Customer Detail Page");
		custDetailPg.clickMergeHistoryTab();
		ajax.waitLoading();
		mergeHistoryPg.waitLoading();
		
	}
	
	
	public void gotoPointsPgFromDetailPg()
	{
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
		.getInstance();
		LicMgrCustomerPointsPage pointsPg =LicMgrCustomerPointsPage.getInstance();
		
		logger.info("Goto points page form Customer Detail Page");
		custDetailPg.clickPointsTab();
		ajax.waitLoading();
		pointsPg.waitLoading();		
	}
	public void changePointsForCustomerFromDetailPg(CustomerPoint custPoint, boolean isAdd) {
		LicMgrCustomerPointsPage pointsPg =LicMgrCustomerPointsPage.getInstance();
		LicMgrCustomerAddPointsWidget addWidget = LicMgrCustomerAddPointsWidget.getInstance();
		
		this.gotoPointsPgFromDetailPg();
		
		logger.info((isAdd ? "Add" : "Deduct") + " points in Customer points Page");
		if (isAdd) {
			pointsPg.clickAddPoints();
		} else {
			pointsPg.clickDeductPoints();
		}
		ajax.waitLoading();
		addWidget.waitLoading();		
		
		addWidget.setChangePointsInfo(custPoint, isAdd);
		addWidget.clickOK();
		ajax.waitLoading();
		pointsPg.waitLoading();
	}
	
	public void addPointsForCustomFromDetailPg(CustomerPoint custPoint) {
		this.changePointsForCustomerFromDetailPg(custPoint, true);
	}
	
	public void deductPointsForCustomFromDetailPg(CustomerPoint custPoint) {
		this.changePointsForCustomerFromDetailPg(custPoint, false);
	}
	/**
	 * The method used to add more privileges to order cart,and it starts from
	 * order cart page
	 * 
	 * @param privileges
	 */
	public void addMorePrivilegeFromCartToCart(PrivilegeInfo... privileges) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();

		logger.info("Add More Privilege To Order Cart.");

		cartPg.clickAddMorePrivilege();
		addItemPg.waitLoading();
		for (PrivilegeInfo privilege : privileges) {
			this.addPrivilegeItem(privilege);
		}
		this.goToCart();
	}

	/**
	 * Add more consumables from order cart
	 * 
	 * @param consumables
	 */
	public void addMoreConsumableFromCartToCart(ConsumableInfo... consumables) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();

		logger.info("Add More Consumable To Order Cart.");
		cartPg.clickAddMoreConsumable();
		addItemPg.waitLoading();
		this.addConsumableItem(consumables);
		this.goToCart();
	}

	/**
	 * This method used to purchase privilege from order cart and identify
	 * another customer and purchase privilege to order cart
	 * 
	 * @param newCust
	 *            --another customer info
	 * @param privileges
	 */
	public void purchasePrivilegeFromCartToCart(Customer newCust,
			PrivilegeInfo... privileges) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrIdentifyCustomerPage idenCustPg = LicMgrIdentifyCustomerPage
				.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget.getInstance();

		logger
				.info("Purchase Privilege From cart to cart for another customer.");

		cartPg.clickPurchasePrivilege();
		ajax.waitLoading();
		idenCustPg.waitLoading();

		this.identifyAndConfirmCustomer(newCust);
		confirmCustPg.waitLoading();
		confirmCustPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(residencyWidget, addItemPg);
		if (page == residencyWidget) {
			if (newCust.residencyStatus.equalsIgnoreCase("Resident")) {
				residencyWidget.selectResident();
				ajax.waitLoading();
				if (residencyWidget.isAdditionalProofDropdownListExists()) {
					residencyWidget
					.selectAdditionalProof(newCust.additionalProofOfResidency);
				}
			} else if(newCust.residencyStatus.equalsIgnoreCase("Non Resident")){
				residencyWidget.selectNonResident();
				ajax.waitLoading();
			} else{
				residencyWidget.selectResident(newCust.residencyStatus);
				ajax.waitLoading();
			}
			
			residencyWidget.clickOK();
			ajax.waitLoading();
		}
		
		addItemPg.waitLoading();
		for (PrivilegeInfo privilege : privileges) {
			this.addPrivilegeItem(privilege);
		}
		this.goToCart();
	}

	/**
	 * purchase consumable from order cart
	 * 
	 * @param consumables
	 */
	public void purchaseConsumableFromCartToCart(ConsumableInfo... consumables) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();

		logger
				.info("Purchase Consumable from cart to cart for another customer.");
		cartPg.clickPurchaseConsumables();
		ajax.waitLoading();
		addItemPg.waitLoading();
		this.addConsumableItem(consumables);
		this.goToCart();
	}

	/**
	 * Goto order page from orders Quick Search area
	 * 
	 * @param privOrdNum
	 */
	public void gotoOrderPageFromOrdersQuickSearch(String ordNum) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();

		logger.info("Go to order#" + ordNum
				+ " detail page from privilege quick search area.");
		homePg.setOrderNumber(ordNum);
		homePg.clickSearchInOrders();
		ajax.waitLoading();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickOrderNum(ordNum);
		ajax.waitLoading();
		privOrderDetailPage.waitLoading();
	}

	/**
	 * Goto order page from orders in Top Menu
	 * 
	 * @param ordNum
	 */
	public void gotoOrderPageFromOrdersTopMenu(String ordNum) {
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		logger.info("Go to order#" + ordNum
				+ " detail page from Orders in top menu.");
		homePg.clickOrders();
		ajax.waitLoading();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.selectOrderSearchType("Order #");
		privOrderSearchPage.setOrderSearchValue(ordNum);
		privOrderSearchPage.clickSearch();
		ajax.waitLoading();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickOrderNum(ordNum);
		ajax.waitLoading();
		privOrderDetailsPage.waitLoading();
	}
	
	public void gotoPrivilegeDetailPgFromOrdersTopMenu(String orderNum, String privilegeNum){
		gotoOrderPageFromOrdersTopMenu(orderNum);
		gotoPrivilegeDetailFromOrderPg(privilegeNum);
	}

	/**
	 * Go to vehicle order search page from top menu
	 */
	public void gotoVehicleOrderPgFromTop() {
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrVehicleOrderSearchPage vehicleOrderSearchPg = LicMgrVehicleOrderSearchPage
				.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		homePg.clickOrders();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickVehicleOrdersTab();
		ajax.waitLoading();
		vehicleOrderSearchPg.waitLoading();

	}

	public void searchVehicleOrder(OrderInfo info) {
		LicMgrVehicleOrderSearchPage vehicleOrderSearchPg = LicMgrVehicleOrderSearchPage
				.getInstance();

		vehicleOrderSearchPg.setupOrderSearchCriteria(info);
		vehicleOrderSearchPg.clickSearch();
		ajax.waitLoading();
		vehicleOrderSearchPg
				.waitLoading();
	}

	/**
	 * Go to customer search page from home page by click advanced search link
	 */
	public void gotoCustomerSearchPgFromHomePgByClickAdvSearchLink() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();

		logger
				.info("Go to customer search page from home page by click advanced search link ");
		homePg.clickAdvancedSearchInCustomers();
		ajax.waitLoading();
		custSearchPg.waitLoading();
	}

	/**
	 * Goto customer search/list page from customers quick search area in
	 * license manager home page.
	 * 
	 * @param customer
	 * @param isAdvancedSearch
	 */
	public Object gotoCustomerSearchPageFromCustomersQuickSearch(
			Customer customer, boolean isAdvancedSearch) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();

		logger
				.info("Goto Customer Search/List page from customers quick search area.");

		if (isAdvancedSearch) {
			// if click the advanced search link system will directly goto
			// customer search/list page without
			homePg.clickAdvancedSearchInCustomers();
		} else {
			if (customer.customerClass != null
					&& customer.customerClass.length() > 0) {
				homePg.selectCustomerClass(customer.customerClass);
				ajax.waitLoading();
			}
			if (customer.identifier.identifierType != null
					&& customer.identifier.identifierType.length() > 0) {
				homePg.selectIdentifierType(customer.identifier.identifierType);
				ajax.waitLoading();
			}
			if (customer.identifier.identifierNum != null
					&& customer.identifier.identifierNum.length() > 0) {
				homePg.setIdentifierNum(customer.identifier.identifierNum);
			}
			if (customer.zip != null && customer.zip.length() > 0) {
				homePg.setZipCode(customer.zip);
			}
			if (customer.lName != null && customer.lName.length() > 0) {
				homePg.setCustomerLastName(customer.lName);
			}
			if (customer.fName != null && customer.fName.length() > 0) {
				homePg.setCustomerFirstName(customer.fName);
			}
			if (customer.businessName != null
					&& customer.businessName.length() > 0) {
				homePg.setBusinessName(customer.businessName);
			}
			if (customer.dateOfBirth != null
					&& customer.dateOfBirth.length() > 0) {
				homePg.setDateOfBirth(customer.dateOfBirth);
			}

			homePg.clickSearchInCustomers();
			ajax.waitLoading();
		}
		return browser.waitExists(custSearchPg, custDetailPg);
	}

	/**
	 * Goto customer search/list page from customers quick search area in
	 * license manager home page.
	 * 
	 * @param customer
	 */
	public Object gotoCustomerSearchPageFromCustomersQuickSearch(
			Customer customer) {
		return gotoCustomerSearchPageFromCustomersQuickSearch(customer, false);
	}

	/**
	 * Goto customer search/list page from customers in top menu.
	 */
	public void gotoCustomerSearchPageFromCustomersTopMenu() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();

		logger
				.info("Goto customer search/list page from Customers in top menu.");
		homePg.clickCustomers();
		custSearchPg.waitLoading();
	}

	/**
	 * Search privilege order from Orders Quick Search Area and goto order
	 * detail to invalidate it.
	 * 
	 * @param ordNum
	 * @param reason
	 * @param note
	 */
	public void searchAndInvalidatePrivilegeOrder(String ordNum, String reason,
			String note) {
		this.searchAndOperatePrivilegeOrder("Invalidate", ordNum, reason, note);
	}

	/**
	 * Search privilege order from Orders Quick Search Area and goto order
	 * detail to reactivate it.
	 * 
	 * @param ordNum
	 * @param reason
	 * @param note
	 */
	public void searchAndReactivatePrivilegeOrder(String ordNum, String reason,
			String note) {
		this.searchAndOperatePrivilegeOrder("Reactivate", ordNum, reason, note);
	}

	/**
	 * Search privilege order from Orders Quick Search Area and goto order
	 * detail to transfer it.
	 * 
	 * @param ordNum
	 * @param reason
	 * @param note
	 */
	public void searchAndTransferPrivilegeOrder(String ordNum, String reason,
			String note) {
		this.searchAndOperatePrivilegeOrder("Transfer", ordNum, reason, note);
	}

	/**
	 * The method is used to operate privilege order in Order Detail
	 * page from Orders Quick Search Area
	 * 
	 * @param operation
	 *            - including invalidate, reactivate, transfer, etc.
	 * @param ordNum
	 * @param reason
	 * @param note
	 */
	void searchAndOperatePrivilegeOrder(String operation,
			String ordNum, String reason, String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget confirmationWidget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget
				.getInstance();

		logger.info(operation + " privilege order(# - " + ordNum + ").");
		if (!privOrderDetailPage.exists()
				|| privOrderDetailPage.getOrderNum() != ordNum) {
			gotoOrderPageFromOrdersQuickSearch(ordNum);
		}

		if (operation.equalsIgnoreCase("Invalidate")) {
			privOrderDetailPage.invalidateOrder();
		} else if (operation.equalsIgnoreCase("Reactivate")) {
			privOrderDetailPage.reactivateOrder();
		} else if (operation.equalsIgnoreCase("Transfer")) {
			// TODO: Finish it
		} else {
			throw new ActionFailedException("The privilege order operation - "
					+ operation + " is wrong.");
		}

		confirmationWidget.waitLoading();
		confirmationWidget.setInfo(reason, note);
		privOrderDetailPage.waitLoading();
	}

	/**
	 * Invalidate privilege order in order detail page
	 * 
	 * @param reason
	 * @param note
	 */
	public void invalidatePrivilegeOrder(String reason, String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget confirmationWidget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget
				.getInstance();

		logger.info("Invalidate privilege order in order detail page.");
		privOrderDetailPage.invalidateOrder();
		confirmationWidget.waitLoading();
		confirmationWidget.setInfo(reason, note);
		privOrderDetailPage.waitLoading();
	}

	/**
	 * Reactivate privilege order in order detail page
	 * 
	 * @param reason
	 * @param note
	 */
	public void reactivatePrivilegeOrder(String reason, String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget confirmationWidget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget
				.getInstance();

		logger.info("Reactivate privilege order in order detail page.");
		privOrderDetailPage.reactivateOrder();
		ajax.waitLoading();
		confirmationWidget.waitLoading();
		confirmationWidget.setInfo(reason, note);
		privOrderDetailPage.waitLoading();
	}

	public void invalidateFirstPrivilegeFromCustomerPrivilegPage(String licenseY, String[] searchCr, String reason,
			String note) {
		this.invalidatePrivilegeFromCustomerPrivilegePage(null, licenseY, searchCr, reason, note);
	}

	public void invalidateFirstPrivilegeFromCustomerPrivilegPage(String[] searchCr, String reason,String note) {
		this.invalidatePrivilegeFromCustomerPrivilegePage(null, null, searchCr, reason, note);
	}
	
	/**
	 * Goto privilege detail page to invalidate privilege from customer
	 * privilege page
	 * 
	 * @param privilegeNum
	 * @param reason
	 * @param note
	 * @return
	 */
	public void invalidatePrivilegeFromCustomerPrivilegePage(
			String privilegeNum, String year, String []searchCriteria, String reason, String note) {
		LicMgrCustomerPrivilegePage custPrivilegePg = LicMgrCustomerPrivilegePage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Invalidate privilege from customer privilege page.");
		if (!privilegeDetailPg.exists()
				|| privilegeDetailPg.getPrivilegeNumber() != privilegeNum) {
			if (null != privilegeNum && privilegeNum.length() > 0) {
				custPrivilegePg.gotoPrivilegeDetailPage(privilegeNum, year);
			} else {
				custPrivilegePg.gotoFirstPrivilgeDetailPage(year, searchCriteria);
			}
		}

		privilegeDetailPg.invalidatePrivilege(reason, note);
	}

	public void invalidatePrivilegeFromCustomerPrivilegePage(String privilegeNum, String year, String reason, String note){
		invalidatePrivilegeFromCustomerPrivilegePage(privilegeNum, year, null, reason, note);
	}
	/**
	 * Goto privilege detail page to reactivate privilege from customer
	 * privilege page
	 * 
	 * @param privilegeNum
	 * @param reason
	 * @param note
	 * @return
	 */
	public void reactivatePrivilegeFromCustomerPrivilegePage(
			String privilegeNum, String licenseYeae, String[] searchCr, String reason, String note) {
		LicMgrCustomerPrivilegePage custPrivilegePg = LicMgrCustomerPrivilegePage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Reactivate privilege from customer privilege page.");
		if (!privilegeDetailPg.exists()
				|| privilegeDetailPg.getPrivilegeNumber() != privilegeNum) {
			// TODO need to add other branch to handler more situations
			if (null != privilegeNum && privilegeNum.length() > 0) {
				custPrivilegePg.gotoPrivilegeDetailPage(privilegeNum, null);
			} else {
				custPrivilegePg.gotoFirstPrivilgeDetailPage(licenseYeae, searchCr);
			}
		}

		privilegeDetailPg.reactivatePrivilege(reason, note);
	}

	public void invalidatePrivilegeItem(String reason, String note) {
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Invalidate privilege in privilege detail page.");
		privilegeItemDetailPg.invalidatePrivilege(reason, note);
	}

	public void reactivatePrivilegeItem(String reason, String note) {
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Reactivate privilege in privilege detail page.");
		privilegeItemDetailPg.reactivatePrivilege(reason, note);
	}

	/**
	 * Goto privilege search page from privilege item detail page by clicking
	 * the 'Privileges' tab
	 */
	public void gotoPrivSearchPgFromPrivItemDetailPg() {
		LicMgrPrivilegeItemDetailPage priItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();
		LicMgrPrivilegeItemSearchPage priItemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		logger
				.info("Goto add privilege search page from privilege item detail page.");
		priItemDetailPg.clickPrivilegesTab();
		priItemSearchPg.waitLoading();
	}

	/**
	 * Go to privilege search page from customer page
	 */
	public void gotoPrivSearchPgFromCustomerPg() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage searchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrPrivilegeItemSearchPage priItemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();

		logger.info("Goto add privilege search page from customer page.");
		homePg.clickCustomers();
		ajax.waitLoading();
		searchPg.waitLoading();

		searchPg.clickPrivilegesTab();
		ajax.waitLoading();
		priItemSearchPg.waitLoading();
	}

	/**
	 * Search privilege item by privilege number in privilege item search page
	 * and goto current privilege item detail page
	 * 
	 * @param privilegeNum
	 */
	public void searchAndGotoPrivilegeItemDetail(String privilegeNum) {
		LicMgrPrivilegeItemSearchPage privilegeItemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger
				.info("Search privilege by privilege num(#="
						+ privilegeNum
						+ ") in Privilege Item Search page and goto privilege item detail page.");
		privilegeItemSearchPg.searchPrivilege("Privilege #", privilegeNum);
		privilegeItemSearchPg.clickPrivilegeNumber(privilegeNum);

		privilegeItemDetailPg.waitLoading();
	}

	/**
	 * Search privilege item using order number and then go to the specify
	 * privilege item detail page
	 * 
	 * @param order
	 * @param product
	 * @param licYear
	 * @param index
	 *            : specify privilege
	 * @return
	 */
	public String searchAndGotoPrivilegeItemDetail(String order,
			String product, String licYear, int index) {
		LicMgrPrivilegeItemSearchPage privilegeItemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger
				.info("Search privilege prodcut : "
						+ product
						+ " in Privilege Item Search page and goto privilege item detail page.");

		privilegeItemSearchPg.searchPrivilege("Order #", order);
		String privilegeNum = privilegeItemSearchPg.getPrivilegeNumber(product,
				licYear).get(index);
		privilegeItemSearchPg.clickPrivilegeNumber(privilegeNum);
		privilegeItemDetailPg.waitLoading();

		return privilegeNum;

	}

	/**
	 * Search privilege item using order number and then go to the first
	 * privilege item detail page
	 * 
	 * @param order
	 * @param product
	 * @param licYear
	 * @return
	 */
	public String searchAndGotoPrivilegeItemDetail(String order,
			String product, String licYear) {
		return searchAndGotoPrivilegeItemDetail(order, product, licYear, 0);
	}

	/**
	 * Go back to home page by clicking the Home link in top menu
	 */
	public void gotoHomePage() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();

		logger.info("Go back to License Manager home page.");
		if (!homePg.exists()) {
			topMenuPg.clickHome();
			ajax.waitLoading();
			homePg.waitLoading();
		}
	}

	public void gotoRequestReportPage() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsRequestReportPage reportPg = OrmsRequestReportPage.getInstance();

		logger.info("Go to License Manager Request Report page.");
		topMenuPg.clickReports();
		reportPg.waitLoading();
	}

	public void selectOneRpt(String group, String reportName) {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsRequestReportPage reportPg = OrmsRequestReportPage.getInstance();
		OrmsReportCriteriaPage criteriaPg = OrmsReportCriteriaPage
				.getInstance();

		logger.info("Select " + reportName + " on the report selection page");

		topMenuPg.clickReports();
		reportPg.waitLoading();

		if (group != null && !"".equals(group)) {
			reportPg.selectReportGroup(group);
			reportPg.waitLoading();
		} else
			logger.info("No group given,by default!");

		reportPg.selectReport(reportName);
		reportPg.clickOK();

		criteriaPg.waitLoading();
	}

	public String runSpecialReport(ReportData rd, String path) {
		OrmsReportCriteriaPage rptCriteriaPg = OrmsReportCriteriaPage
				.getInstance();
		OrmsOnlineReportProcessingPage onlineRptPg = OrmsOnlineReportProcessingPage
				.getInstance();

		logger.info("Run Report " + rd.reportName);

		rptCriteriaPg.setReportCriteria(rd);
		rptCriteriaPg.clickOk();
		onlineRptPg.waitLoading();
		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new RuntimeException("Failed to create directory " + path);
			}
		}
		String format = (rd.reportFormat.equalsIgnoreCase("text") ? "txt"
				: rd.reportFormat);
		String fileName = file.getAbsolutePath() + "\\";
		if (StringUtil.notEmpty(rd.fileName)) {
			fileName = fileName + rd.fileName + "." + format;
		} else {
			fileName = fileName + rd.reportName.replaceAll(" ", "") + "_LM."
					+ format;
		}

		downloadReport(fileName);
		// onlineRptPg.close(); this windows will be closed itself.
		rptCriteriaPg.waitLoading();

		return fileName;
	}

	/**
	 * Search privilege order from Orders quick search area and then goto a
	 * specifed privilege item detail page to invalidate it.
	 * 
	 * @param orderNum
	 * @param privilegeNum
	 * @param reason
	 * @param note
	 */
	public void searchAndInvalidatePrivilegeItem(String orderNum,
			String privilegeNum, String reason, String note) {
		this.searchAndOperatePrivilegeItem("Invalidate", orderNum,
				privilegeNum, reason, note);
	}

	/**
	 * Search privilege order from Orders quick search area and then goto a
	 * specified privilege item detail page to reactivate it.
	 * 
	 * @param orderNum
	 * @param privilegeNum
	 * @param reason
	 * @param note
	 */
	public void searchAndReactivatePrivilegeItem(String orderNum,
			String privilegeNum, String reason, String note) {
		this.searchAndOperatePrivilegeItem("Reactivate", orderNum,
				privilegeNum, reason, note);
	}

	/**
	 * Search privilege order from Orders quick search area and then goto a
	 * specified privilege item detail page to transfer it.
	 * 
	 * @param orderNum
	 * @param privilegeNum
	 * @param reason
	 * @param note
	 */
	public void searchAndTransferPrivilegeItem(String orderNum,
			String privilegeNum, String reason, String note) {
		this.searchAndOperatePrivilegeItem("Transfer", orderNum, privilegeNum,
				reason, note);
	}

	/**
	 * method to operate privilege item from Orders Quick Search Area
	 * and end with Privilege Item Detail page
	 * 
	 * @param operation
	 *            - including invalidate, reactivate, transfer, etc.
	 * @param orderNum
	 *            - the privilege ORDER number
	 * @param privilegeNum
	 *            - if null, the method will goto the first privilege item
	 *            detail page
	 * @param reason
	 * @param note
	 */
	void searchAndOperatePrivilegeItem(String operation,
			String orderNum, String privilegeNum, String reason, String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info(operation + " the privilege item(#=" + privilegeNum
				+ ") of privilege order(#=" + orderNum + ").");
		this.gotoOrderPageFromOrdersQuickSearch(orderNum);
		if (null != privilegeNum && privilegeNum.length() > 0) {
			privOrderDetailsPage.selectPrivilegeByPrivilegeNum(privilegeNum);
		} else {
			// lmOrderPg.gotoFirstPrivilegeItemDetailPg();
		}

		privItemDetailPg.waitLoading();
		if (operation.equalsIgnoreCase("Invalidate")) {
			privItemDetailPg.invalidatePrivilege(reason, note);
		} else if (operation.equalsIgnoreCase("Reactivate")) {
			privItemDetailPg.reactivatePrivilege(reason, note);
		} else if (operation.equalsIgnoreCase("Transfer")) {
			privItemDetailPg.transferPrivilege(reason, note);
		} else {
			throw new ActionFailedException("The privilege item operation - "
					+ operation + " is wrong.");
		}
	}

	/**
	 * Void privilege order to order cart page from License Manager Order Detail
	 * Page
	 * 
	 * @param voidReason
	 * @param voidNote
	 */
	public void voidPrivilegeOrderToCart(String voidReason, String voidNote) {
		this.operatePrivilegeOrdToCart("Void", voidReason, voidNote);
	}

	public void voidPrivilegeOrderItems(String ordNum, List<String> privilegeNames, String voidReason, String voidNote, Payment pay) {
		for(int i=0; i<privilegeNames.size(); i++){
			gotoOrderPageFromOrdersTopMenu(ordNum);
			operatePrivilegeOrdToCart("Void", privilegeNames.get(i), voidReason, voidNote);
			processOrderCart(pay);
		}
	}

	/**
	 * Undo void a privilege order to order cart page
	 * 
	 * @param undoVoidReason
	 * @param undoVoidNote
	 */
	public void undoVoidPrivilegeOrdToCart(String undoVoidReason,
			String undoVoidNote) {
		this.operatePrivilegeOrdToCart("Undo Void", undoVoidReason,
				undoVoidNote);
	}

	public void reversePrivilegeOrdToCart(String reason, String note) {
		this.operatePrivilegeOrdToCart("Reverse", reason, note);
	}

	public void undoReversePrivilegeOrderToCart(String reason, String note) {
		this.operatePrivilegeOrdToCart("Undo Reverse", reason, note);
	}

	public void operatePrivilegeOrdToCart(String action, String reason,String note) {
		operatePrivilegeOrdToCart(action, StringUtil.EMPTY, reason, note);
	}
	
	public void gotoVoidUndoVoidPgFromPrivOrderDetailPg(String action){
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		if (!action.contains("Undo")) {
			privOrderDetailPage.voidOrReverseOrder();
		} else {
			privOrderDetailPage.undoVoidOrReverseOrder();
		}
		ajax.waitLoading();
		voidUndoVoidPg.waitLoading();
	}
	
	public void operatePrivilegeOrdToCart(String action, String privilegeName, String reason, String note) {
		logger.info(action + " Privilege Order To Cart.");
		gotoVoidUndoVoidPgFromPrivOrderDetailPg(action);
		operatePrivilegeOrdToCartFromVoidUndoVoidPg(action, privilegeName, reason, note);
	}
	
	public void operatePrivilegeOrdToCartFromVoidUndoVoidPg(String action, String privilegeName, String reason, String note) {
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
		LicMgrChooseHowToReleaseInventoryWidget releaseTypeWidget = LicMgrChooseHowToReleaseInventoryWidget.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance(true);
		int index = 0;
		if(StringUtil.notEmpty(privilegeName)){
			index = voidUndoVoidPg.getPrivilegeRow(privilegeName);
		}
		voidUndoVoidPg.selectkOrderItemRadio(index);
		ajax.waitLoading();
		voidUndoVoidPg.clickVoidOrUndoVoidSelectedTransaction();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(action, reason, note);
		
		Object page = browser.waitExists(releaseTypeWidget, alert, orderCartPg, refundWidget, voidUndoVoidPg);
		if(page == releaseTypeWidget) {
			releaseTypeWidget.selectAllReturnTo();
			releaseTypeWidget.clickOK();
			ajax.waitLoading();
			browser.waitExists(alert, orderCartPg, refundWidget, voidUndoVoidPg); //When the voided order pay by "Cash*", after voiding, it will stay in voidUndoVoidPg
		}
		//Jane[2014-7-2]:Alert without title would display after confirm reverse order
		//Or after release inventory
		if(alert.exists()) {
			alert.clickOK();
			ajax.waitLoading();
			voidUndoVoidPg.waitLoading();
		}
		
		//Jane[2014-7-1]: Added for handle with issue refund process after release inventory
		if(refundWidget.exists())
			this.issueRefundToCustFromRefundWidgetToOrdDetailsPg();
	}
	
	/**
	 * Click 'UndoVoid/Reverse' button in privilege order page to goto undo void
	 * page
	 */
	public void gotoUndoVoidPageFromOrderPg() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();

		privOrderDetailsPage.undoVoidOrReverseOrder();
		voidUndoVoidPg.waitLoading();
	}

	/**
	 * Verify the privilege order doesn't exists in the Undo Void page shows it
	 * cannot be undo voided
	 */
	public void verifyPrivilegeCannotBeUndoVoided() {
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();

		logger.info("Verify privilege can not be undo voided.");

		boolean isPrivilegeExists = voidUndoVoidPg.selectPrivilege();
		if (isPrivilegeExists) {
			throw new ActionFailedException(
					"This privilege order should not be undo voided. But it exists in the Undo Void list.");
		} else {
			logger
					.info("----This privilege order really can not be undo voided.");
		}
	}

	/**
	 * This method used to remove the privilege item in the order cart.
	 * 
	 * @param privilegeName
	 */
	public void removeItemInTheOrderCart(String... privilegeName) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		for (String product : privilegeName) {
			logger.info("Remove the privilege item : " + product
					+ " on the order cart.");
			ordCartPg.removePrivItemInCartPg(product);
			ordCartPg.waitLoading();
		}
	}

	/**
	 * This method step: 1.update payment amount using total price 2.process
	 * order cart
	 * 
	 * @param pay
	 * @return
	 */
	public String processOrderAfterUpdateAmount(Payment pay) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		logger.info("Update payment amount using total price...");
		ordCartPg.updatePaymentAmounts();
		String orderId = this.processOrderCart(pay);
		return orderId;
	}

	/**
	 * go to privilege product sub-tab from home page or
	 * 'EditPrivilegeDetailsPage',Or any page which you can get the 'Top
	 * Dropdown list' in LM
	 * 
	 * @param priCode
	 * @param tabName
	 *            tab name like 'pricing',"Print Documents"
	 */
	public void gotoPrivilegeSubTabPage(String priCode, String tabName) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeQuantityControlPage quantityPage = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		LicMgrPriLandownerSubPage landownerPage = LicMgrPriLandownerSubPage.getInstance();

		if (!privilegeDetailsPage.exists()
				|| !privilegeDetailsPage.getCode().equals(priCode)) {
			if (!privilegeDetailsPage.exists()) {
				this.gotoProductSearchListPageFromTopMenu("Privileges");
			} else if (!privilegeDetailsPage.getCode().equals(priCode)) {
				privilegeDetailsPage.clickOk();
				privilegeListPage.waitLoading();
			}
			this.gotoPrivilegeDetailsPageFromProductListPage(priCode);
		}

		logger.info("Go to privilege sub tab :'" + tabName + "'");
		if (tabName.equalsIgnoreCase("Print Documents")) {
			privilegeDetailsPage.clickPrintDocumentsTab();
			ajax.waitLoading();
			printDocSubTab.waitLoading();
		} else if (tabName.equalsIgnoreCase("License Year")) {
			privilegeDetailsPage.clickLicenseYearTab();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Pricing")) {
			privilegeDetailsPage.clickPricingTab();
			ajax.waitLoading();
			LicMgrPrivilegePricingPage.getInstance().waitLoading();
		} else if (tabName.equalsIgnoreCase("Quantity Control")) {
			privilegeDetailsPage.clickQuantityControlTab();
			ajax.waitLoading();
			quantityPage.waitLoading();
		} else if (tabName.matches("Agent Assignment(s)?")) {
			LicMgrPrivilegeProductStoreAssignmentsPage privilegeAssignPage = LicMgrPrivilegeProductStoreAssignmentsPage
					.getInstance();
			privilegeDetailsPage.clickStoreAssignmentsTab();
			ajax.waitLoading();
			privilegeAssignPage.waitLoading();
		} else if (tabName.matches("Business Rule(s)?")) {
			LicMgrPrivilegeBusinessRulePage businessRulePage = LicMgrPrivilegeBusinessRulePage
					.getInstance();
			privilegeDetailsPage.clickBusinessRuleTab();
			ajax.waitLoading();
			businessRulePage.waitLoading();
		} else if(tabName.equalsIgnoreCase("Landowner")){
			privilegeDetailsPage.clickLandownerTab();
			ajax.waitLoading();
			landownerPage.waitLoading();
		} else {
			if (tabName != null && tabName.trim().length() > 0) {
				throw new ErrorOnDataException("Unknown tab name:" + tabName);
			}
		}

	}

	/**
	 * go to privilege product sub-tab from home page or
	 * 'EditVehicleDetailsPage'
	 * 
	 * @param vehCode
	 * @param tabName
	 */
	public void gotoVehicleProductSubTabPage(String vehCode, String tabName) {
		LicMgrEditVehicleDetailsPage vehicleDetailPage = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrVehiclePrintDocumentsPage printDocSubTab = LicMgrVehiclePrintDocumentsPage
				.getInstance();
		LicMgrVehiclesListPage listPage = LicMgrVehiclesListPage.getInstance();

		if (vehicleDetailPage.exists()
				&& !vehicleDetailPage.getVehicleCode().equals(vehCode)
				|| !vehicleDetailPage.exists()) {
			if (vehicleDetailPage.exists()
					&& !vehicleDetailPage.getVehicleCode().equals(vehCode)) {
				vehicleDetailPage.clickOk();
				listPage.waitLoading();
			}
			this.gotoVehicleProductDetailsPage(vehCode);
		}
		logger.info("Go to Vehicle Product sub tab:" + tabName);
		if (tabName.equalsIgnoreCase("Print Documents")) {
			vehicleDetailPage.clickPrintDocumentsTab();
			ajax.waitLoading();
			printDocSubTab.waitLoading();
		} else if (tabName.equalsIgnoreCase("Pricing")) {
			vehicleDetailPage.clickPricingTab();
			LicMgrVehiclePricingPage.getInstance().waitLoading();
		} else if (tabName.matches("Agent Assignment(s)?")) {
			LicMgrVehicleProductStoreAssignmentsPage vehicleAssignPage = LicMgrVehicleProductStoreAssignmentsPage
					.getInstance();
			vehicleDetailPage.clickStoreAssignmentsTab();
			vehicleAssignPage.waitLoading();
		} else {
			if (tabName != null && tabName.trim().length() > 0) {
				throw new ErrorOnDataException("Unknown tabName:" + tabName);
			}
		}
	}

	/**
	 * search print documents in product(privilege,vehicle) sub-tab:print
	 * documents
	 * 
	 * @param docInfo
	 * @param currentOnly
	 */
	public void searchPrintDocumentsInProductSubTab(String status,
			String docTemp, String equipType, boolean currentOnly) {
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		logger
				.info("search print document product info on Product sub Tab page.");
		printDocSubTab.searchPrintDocument(currentOnly, status, docTemp,
				equipType);
	}

	/**
	 * change Print Document status for product Work flow start
	 * from:productdetailspage or sub-tab:print document End with:product
	 * sub-tab:print document
	 * 
	 * @param id
	 * @param activate
	 */
	public void changePrintDocumentStatus(String id, boolean activate) {
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();
		LicMgrPrivilegePrintDocumentsPage priviPrintDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		LicMgrVehiclePrintDocumentsPage vehiclePrintDocSubTab = LicMgrVehiclePrintDocumentsPage
				.getInstance();
		LicMgrEditVehicleDetailsPage vehicleDetailsPage = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		String status = activate ? "Active" : "Inactive";
		logger.info("change PrintDocument status which id=" + id + " to "
				+ status);
		if (privilegeDetailsPage.exists()) {
			if (!priviPrintDocSubTab.exists()) {
				privilegeDetailsPage.clickPrintDocumentsTab();
				ajax.waitLoading();
			}
			priviPrintDocSubTab.waitLoading();
			priviPrintDocSubTab.uncheckShowCurrentRecordsOnly();
			ajax.waitLoading();
			priviPrintDocSubTab.clickGo();
			ajax.waitLoading();
			priviPrintDocSubTab.clickPrintDocumentID(id);
		} else if (vehicleDetailsPage.exists()) {
			if (!vehiclePrintDocSubTab.exists()) {
				vehicleDetailsPage.clickPrintDocumentsTab();
				ajax.waitLoading();
			}
			vehiclePrintDocSubTab.waitLoading();
			vehiclePrintDocSubTab.clickPrintDocumentID(id);
			ajax.waitLoading();
		} else {
			throw new ActionFailedException("Unknown page.");
		}

		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.selectStatus(status);
		editWidget.clickOK();
		ajax.waitLoading();
	}

	/**
	 * update the document info start from privilege product details master
	 * page, ends at product print document page.
	 */
	public String editPrintDocumentFroProductInfo(LicMgrDocumentInfo document) {
		this.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(document.prodCode);
		this.searchPrintDocumentsInProductSubTab(document.status,
				document.docTepl, document.equipType, false);
		document.id = this.getPrivilegePrintDocumentId(document);
		return this.editPrintDocumentForProduct(document);
	}

	/**
	 * Get document assignment ID via status, document template name, equipment
	 * type, purchase type, license year from, Start ends from privilege product
	 * fulfillment document page.
	 * 
	 * @param document
	 * @param withEffectiveFromDate
	 *            whether or not use effectiveFromDate as search criteria to get
	 *            print documents id.
	 * @return document assignment ID
	 */
	public String getPrivilegePrintDocumentId(LicMgrDocumentInfo doc) {
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		String id = "";
		id = printDocPg.getPrintDocumentAssignmentID(doc, false);
		return id;
	}

	/**
	 * edit print document information by Id, Work flow start
	 * from:ProductPrintDocumentsPage End in:ProductPrintDocumentsPage
	 * 
	 * @param docInfo
	 */
	public String editPrintDocumentForProduct(LicMgrDocumentInfo docInfo) {
		return this.editPrintDocumentForProduct(docInfo, true);
	}

	/**
	 * edit print document information by Id, Work flow start
	 * from:ProductPrintDocumentsPage End in:ProductPrintDocumentsPage
	 * 
	 * @param docInfo
	 * @param confirmOk
	 *            click ok or not when confirm widget is displayed
	 */
	public String editPrintDocumentForProduct(LicMgrDocumentInfo docInfo,
			boolean confirmOk) {
		LicMgrPrivilegePrintDocumentsPage printDocPage = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		String msg = "";
		logger.info("Edit PrintDocument which id=" + docInfo.id);
		this.gotoEditPrintDocumentWidget(docInfo.id);
		editWidget.setDocInfo(docInfo);
		editWidget.clickOK();
		ajax.waitLoading();

		Object page = browser.waitExists(confirmWidget, editWidget,
				printDocPage);
		if (confirmWidget == page) {
			msg = confirmWidget.getMessage();
			if (confirmOk) {
				confirmWidget.clickOK();
			} else {
				confirmWidget.clickCancel();
			}
			ajax.waitLoading();
			return msg;
		}

		if (editWidget == page) {
			msg = editWidget.getWarningMessage();
		}

		return msg;
	}

	/**
	 * Go to Edit Print Document page from Print Document list page
	 * 
	 * @param documentId
	 */
	public void gotoEditPrintDocumentWidget(String documentId) {
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		logger
				.info("Go to Edit Print Document detail page form Print Document list page.");
		printDocSubTab.searchPrintDocument(false, "", "", "");
		printDocSubTab.clickPrintDocumentID(documentId);
		ajax.waitLoading();
		editWidget.waitLoading();
	}

	/**
	 * Add print document for privilege
	 * 
	 * @from 'LM HomePage' or "Product DetailsPage" or
	 *       'LicProductPrintDocumentsSubTab' or LicMgrAddPrintDocumentWidget;
	 * @To LicMgrPrivilegePrintDocumentsPage/LicMgrVehiclePrintDocumentsPage
	 * @Product: Vehicle, Privilege
	 * @Note: the product type should be specified by docInfo.proTyp
	 * 
	 * @param docInfo
	 * @return message if error message displays
	 */
	public String addPrintDocumentForProduct(LicMgrDocumentInfo docInfo) {
		return this.addPrintDocumentForProduct(docInfo, true);
	}

	/***
	 * Add print documents for privilege * @from 'LM HomePage' or
	 * "Product DetailsPage" or 'LicProductPrintDocumentsSubTab' or
	 * LicMgrAddPrintDocumentWidget;
	 * 
	 * @To LicMgrPrivilegePrintDocumentsPage/LicMgrVehiclePrintDocumentsPage
	 * @Product: Vehicle, Privilege
	 * @Note: the product type should be specified by docInfo.proTyp
	 * @param documentInfos
	 * @return messages if error message displays
	 */
	public String[] addPrintDocumentForProduct(
			LicMgrDocumentInfo... documentInfos) {
		String[] msg = new String[documentInfos.length];
		for (int i = 0; i < documentInfos.length; i++) {
			msg[i] = this.addPrintDocumentForProduct(documentInfos[i]);
		}
		return msg;
	}

	/**
	 * Add print document for privilege * @from 'LM HomePage' or
	 * "Product DetailsPage" or 'LicProductPrintDocumentsSubTab' or
	 * LicMgrAddPrintDocumentWidget;
	 * 
	 * @To LicMgrPrivilegePrintDocumentsPage/LicMgrVehiclePrintDocumentsPage
	 * @Product: Vehicle, Privilege
	 * @Note: the product type should be specified by docInfo.proTyp
	 * 
	 * @param docInfo
	 * @param confirmOk
	 *            : after click 'OK' on add print document widget and if confirm
	 *            widget popup, confirmOk==true?'OK':'Cancel'
	 * @return message if error message displays
	 */
	public String addPrintDocumentForProduct(LicMgrDocumentInfo docInfo,
			boolean confirmOk) {
		String msg = "";
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		LicMgrAddPrintDocumentWidget addPrintDocWidget = LicMgrAddPrintDocumentWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();

		if (!addPrintDocWidget.exists()) {
			if (!printDocSubTab.exists()) {
				if (docInfo.prodType.equalsIgnoreCase("privilege")) {
					this.gotoPrivilegeSubTabPage(docInfo.prodCode,
							"Print Documents");

				} else if (docInfo.prodType.equalsIgnoreCase("vehicle")) {
					this.gotoVehicleProductSubTabPage(docInfo.prodCode,
							"Print Documents");

				} else {
					throw new ErrorOnDataException("UNknown Product Type:"
							+ docInfo.prodType);
				}
			}

			logger.info("add 'print document' for privilege which code is "
					+ docInfo.prodCode);
			printDocSubTab.clickAddPrintDocument();
			ajax.waitLoading();
			addPrintDocWidget.waitLoading();
		}
		addPrintDocWidget.setDocInfo(docInfo);
		addPrintDocWidget.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget, addPrintDocWidget,
				printDocSubTab);

		if (addPrintDocWidget == pages) {
			msg = addPrintDocWidget.getWarningMessage();
		}

		if (confirmWidget == pages) {
			msg = confirmWidget.getMessage();
			if (confirmOk)
				confirmWidget.clickOK();
			else {
				confirmWidget.clickCancel();
			}
			ajax.waitLoading();
			printDocSubTab.waitLoading();
		}

		return msg;
	}

	/**
	 * The method used to goto given privilege item detail page
	 * 
	 * @return
	 */
	public void gotoPrivilegeDetailFromOrderPg(String privilegeNumber) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrPrivilegeItemDetailPage itemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		privOrderDetailsPage.selectPrivilegeByPrivilegeNum(privilegeNumber);
		itemDetailPg.waitLoading();
	}

	/**
	 * Go to PrivilegeProductDetailsPage From ProductListPage
	 * 
	 * @param priCode
	 */
	public void gotoPrivilegeDetailsPageFromProductListPage(String priCode) {
		this.gotoPrivilegeDetailsPageFromProductListPage(priCode, true);
	}

	
	public void gotoPrivilegeDetailsPageFromProductListPage(String priCode, boolean isActive) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger
				.info("Go to PrivilegeProductDetailsPage From ProductListPage: privilege code ="
						+ priCode);
		
		if(!isActive){
			privilegePage.selectStatus(INACTIVE_STATUS);
			privilegePage.clickGo();
			ajax.waitLoading();
			privilegePage.waitLoading();
		}
		privilegePage.clickPrivilegeCode(priCode);
		ajax.waitLoading();
		privilegeDetailsPg.waitLoading();
	}
	
	/**
	 * edit privilege info, start at privilege product list page, end at
	 * privilege product details page.
	 * 
	 * @param privilege
	 */
	public void editPrivilegeInfo(PrivilegeInfo privilege) {
		this.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.editPrivilege(privilege);
	}

	/**
	 * update the license year info. start from top menu, ends at Privilege
	 * License Year page.
	 */
	public void editPrivilegeLicenseYearInfoFromTopMenu(LicenseYear ly) {
		this.gotoPrivilegeSearchListPageFromTopMenu();
		this.gotoPrivilegeDetailsPageFromProductListPage(ly.productCode);
		this.editPrivilegeLicenseYear(ly);// update the license year sell
		// and valid date,time info with
		// the initial data
	}

	/**
	 * edit privilege product license year info, start from privileges detail
	 * page/license year page and ends at license year page.
	 * 
	 * @param ly
	 * @return - new license year id
	 */
	public String editPrivilegeLicenseYear(LicenseYear ly) {
		LicMgrPrivilegeLicenseYearPage licYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage priDetailPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget detailWidget = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();

		Object page = browser.waitExists(priDetailPg, licYearPg);
		if (page == priDetailPg) {
			this.gotoPrivilegeLicenseYearPage();
		}

		if (ly.id.length() > 0) {
			this.gotoPrivilegeLicenseYearDetailPg(ly.id);
		} else if (ly.locationClass.length() > 0 && ly.licYear.length() > 0) {
			this.gotoPrivilegeLicenseYearDetailPg(ly.locationClass, ly.licYear);
		}

		detailWidget.setLicenseYearInfo(ly);
		detailWidget.clickOK();
		ajax.waitLoading();
		licYearPg.waitLoading();
		String id = licYearPg.getLicenseYearId(ly.status, ly.locationClass,
				ly.licYear);

		return id;
	}

	/**
	 * update privilege product license year info, start from privileges license
	 * year detail page and ends at license year page/edit license year widget
	 * page.
	 * 
	 * @param ly
	 */
	public void updatePrivilegeLicenseYearInfo(LicenseYear ly) {
		LicMgrPrivilegeLicenseYearPage licYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget detailPg = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();

		detailPg.setLicenseYearInfo(ly);
		detailPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(licYearPg, detailPg);
	}

	/**
	 * edit privilege info
	 * 
	 * @param privilege
	 */
	public void editPrivilege(PrivilegeInfo privilege) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info("Edit privilege(CD#=" + privilege.code + ") info.");
		privilegeDetailsPg.updatePrivilegeInfo(privilege);
		privilegeDetailsPg.clickOk();
		ajax.waitLoading();
		// privilegeListPage.waitExists();
		browser.waitExists(privilegeDetailsPg, privilegeListPage);
	}

	/**
	 * Verify privilege product brief info in list page
	 * 
	 * @param expectedPrivilege
	 */
	public void verifyPrivilegeListInfo(PrivilegeInfo expectedPrivilege) {
		LicMgrPrivilegesListPage privListPage = LicMgrPrivilegesListPage
				.getInstance();

		logger.info("Verify privilege(CD#=" + expectedPrivilege.code
				+ ") list info.");
		boolean result = privListPage.comparePrivilegeInfo(expectedPrivilege);
		if (!result) {
			throw new ErrorOnPageException(
					"Actual privilege list info doesn't match with expceted.");
		} else {
			logger.info("Privilege brief info is correct.");
		}
	}

	/**
	 * Verify privilege product details info
	 * 
	 * @param expectedPrivilege
	 */
	public void verifyPrivilegeDetailInfo(PrivilegeInfo expectedPrivilege, boolean isInventoryPrdType) {
		LicMgrPrivilegesListPage privListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privDetailPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info("Verify privilege(CD#=" + expectedPrivilege.code
				+ ") details info.");
		privListPage.clickPrivilegeCode(expectedPrivilege.code);
		ajax.waitLoading();
		privDetailPage.waitLoading();
		boolean result = privDetailPage
				.comparePrivilegeInfoIsCorrect(expectedPrivilege, isInventoryPrdType);
		if (!result) {
			throw new ErrorOnPageException(
					"Actual privilege detail info doesn't match with expceted.");
		} else {
			logger.info("Privilege detail info is correct.");
		}
		privDetailPage.clickOk();
		ajax.waitLoading();
		privListPage.waitLoading();
	}

	public void verifyPrivilegeDetailInfo(PrivilegeInfo expectedPrivilege) {
		verifyPrivilegeDetailInfo(expectedPrivilege, false);
	}
	
	public String addPrivilegeQuantityControl(
			QuantityControlInfo quantityControl) {
		return this.addPrivilegeQuantityControl(quantityControl, true);
	}

	public List<String> addPrivilegeQuantityControl(
			QuantityControlInfo... quantityControls) {
		List<String> list = new ArrayList<String>();
		for (QuantityControlInfo q : quantityControls) {
			String id = this.addPrivilegeQuantityControl(q, true);
			list.add(id);
		}
		return list;
	}

	public void gotoPrivQuantityControlPgFromPrivDetailsPg(){
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		logger.info("Go to Privilege Quantity Control page from privilege details page.");
		privilegeDetailsPg.clickQuantityControlTab();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	public void gotoAddPrivQuantityControlWidgetFromPrivQuantityControlList(){
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeAddQuantityControlWidget addQuantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
		logger.info("Go to add Privilege Quantity Control widget from privilege Quantity Control page.");
		quantityControlPg.clickAddQuantityControl();
		ajax.waitLoading();
		addQuantityControlWidget.waitLoading();
	}
	
	public void gotoEditPrivQuantityControlWidgetFromPrivQuantityControlList(String quantityControlID){
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWidget = LicMgrPrivilegeEditQuantityControlWidget.getInstance();
		logger.info("Go to edit Privilege Quantity Control widget from privilege Quantity Control page.");
		quantityControlPg.clickQuantityControlID(quantityControlID);
		ajax.waitLoading();
		editQuantityControlWidget.waitLoading();
	}
	
	public void gotoPrivQuantityControlListFromAddPrivQuantityControlWidget(){
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeAddQuantityControlWidget addQuantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
		logger.info("Go to Privilege Quantity Control page from add Privilege Quantity Control widget.");
		addQuantityControlWidget.clickCancel();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	public void gotoPrivQuantityControlListFromEditPrivQuantityControlWidget(){
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWidget = LicMgrPrivilegeEditQuantityControlWidget.getInstance();
		logger.info("Go to Privilege Quantity Control page from edit Privilege Quantity Control widget.");
		editQuantityControlWidget.clickCancel();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	/**
	 * Add privilege quantity control in add privilege quantity control widget
	 * 
	 * @param quantityControl
	 *            - data collection info
	 * @param isClickOk
	 *            - if true, clicking ok button; if false, click cancel button
	 * @return
	 */
	public String addPrivilegeQuantityControl(
			QuantityControlInfo quantityControl, boolean isClickOk) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		LicMgrPrivilegeAddQuantityControlWidget addWidget = LicMgrPrivilegeAddQuantityControlWidget
				.getInstance();

		logger.info("Add Privilege Quantity Control.");
		if (!quantityControlPg.exists()) {
			quantityControlPg.clickQuantityControlTab();
			ajax.waitLoading();
			quantityControlPg.waitLoading();
		}
		quantityControlPg.clickAddQuantityControl();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setQuantityControlInfo(quantityControl);
		if (isClickOk) {
			addWidget.clickOK();
		} else {
			addWidget.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(addWidget, quantityControlPg);
		String toReturn = "";
		if (addWidget == page) {
			toReturn = addWidget.getErrorMessage();
		}
		if (quantityControlPg == page && isClickOk) {
			toReturn = quantityControlPg.getQuantityControlID(
					quantityControl.locationClass,
					quantityControl.multiSalesAllowance);
			logger.info("----New Quantity Control id - " + toReturn);
		}

		return toReturn;
	}

	/**
	 * Edit privilege quantity control
	 * 
	 * @param quantityControl
	 * @return
	 */
	public String editPrivilegeQuantityControl(
			QuantityControlInfo quantityControl) {
		return this.editPrivilegeQuantityControl(quantityControl, true);
	}

	/**
	 * Edit privilege quantity control(Location Class Settings) record
	 * 
	 * @param quantityControl
	 * @param clickOK
	 * @return
	 */
	public String editPrivilegeQuantityControl(
			QuantityControlInfo quantityControl, boolean clickOK) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editWidget = LicMgrPrivilegeEditQuantityControlWidget
				.getInstance();

		logger.info("Eidt Privilege Quantity Control - " + quantityControl.id);
		quantityControlPg.clickQuantityControlID(quantityControl.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.setQuantityControlInfo(quantityControl);
		editWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(editWidget, quantityControlPg);
		String toReturn = "";
		if (editWidget == page) {
			toReturn = editWidget.getErrorMessage();
		}
		if ((quantityControlPg == page) && clickOK) {
			toReturn = quantityControlPg.getQuantityControlID(
					quantityControl.locationClass,
					quantityControl.multiSalesAllowance);
		}

		return toReturn;
	}

	/**
	 * This method is used to go to create new privilege product page, start
	 * from privilege list page, end at create new privilege page
	 */
	public void gotoCreatePrivilegeProductPageFromPrivilegeListPage() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();

		logger
				.info("Go to Create Privilege Product Page From Privilege List Page.");
		privilegeListPage.clickAddPrivilegeButton();
		createwPrivilegeProductPage.waitLoading();
	}

	//

	public void gotoNewLotteryProductPageFromListPage() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrCreateNewLotteryProductPage createProductPage = LicMgrCreateNewLotteryProductPage
				.getInstance();

		logger
				.info("Go to Create Lottery Product Page From product List Page.");
		lotteriesProductListPg.clickAddLotteryProduct();
		ajax.waitLoading();

		createProductPage.waitLoading();
	}

	/**
	 * This method is used to go to edit privilege display order page, start
	 * from privilege list page, end at edit display order page
	 */
	public void gotoPrivilegeEditDisplayOrderPageFromPrivilegeListPage() {
		this.gotoPrivilegeEditDisplayOrderPageFromPrivilegeListPage(ACTIVE_STATUS);
	}

	/**
	 * This method is used to go to edit privilege display order page, start
	 * from privilege list page, end at edit display order page
	 * @param priStatus: Active or Inactive
	 */
	public void gotoPrivilegeEditDisplayOrderPageFromPrivilegeListPage(String priStatus) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeEditDisplayOrderPage privilegeEditDisplayOrderPage = LicMgrPrivilegeEditDisplayOrderPage
				.getInstance();

		logger
				.info("Go to edit privilege display order page from privilege list page.");
		if("Inactive".equalsIgnoreCase(priStatus)){
			privilegeListPage.selectStatus(priStatus);
			privilegeListPage.clickGo();
			ajax.waitLoading();
			privilegeListPage.waitLoading();
		}
		privilegeListPage.clickEditDisplayOrder();
		privilegeEditDisplayOrderPage.waitLoading();
	}

	/**
	 * This method is used to edit display order for privilege, start from
	 * Privilege List page, end at privilege list page
	 * 
	 * @param privilegeCode
	 * @param displayOrder
	 */
	public void editDisplayOrderForPrivilege(String privilegeCode,
			String displayOrder) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeEditDisplayOrderPage displayOrderPage = LicMgrPrivilegeEditDisplayOrderPage
				.getInstance();

		logger.info("Edit display order for privilege(Code#=" + privilegeCode
				+ ").");
		this.gotoPrivilegeEditDisplayOrderPageFromPrivilegeListPage();

		displayOrderPage.setDisplayOrderForPrivilege(privilegeCode,
				displayOrder);
		displayOrderPage.clickOk();
		ajax.waitLoading();
		browser.waitExists(displayOrderPage, privilegeListPage);
	}

	/**
	 * Go to change history detail page from privilege detail page
	 */
	public void gotoPrivilegeChangeHistoryDetailPage() {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();

		logger
				.info("Go to Change History Detail Page From Privilege Detail Page");

		privilegeDetailsPg.clickChangeHistory();
		historyPage.waitLoading();
	}

	/**
	 * Go to vehicle detail page from vehicle search list page
	 * 
	 * @param vehicleCode
	 */
	public void gotoVehicleProductDetailsPageFromListPage(String vehicleCode) {
		LicMgrVehiclesListPage vehicleListPg = LicMgrVehiclesListPage
				.getInstance();
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();

		logger.info("Go to vehicle - " + vehicleCode
				+ " detail page from vehicle search list page.");
		vehicleListPg.clickVehicleCode(vehicleCode);
		vehicleDetailPg.waitLoading();
	}

	/**
	 * Go to vehicle change history page from vehicle detail page
	 */
	public void gotoVehicleChangeHistoryPageFromDetailPage() {
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();

		logger
				.info("Go to vehicle change history page from vehicle detail page.");
		vehicleDetailPg.clickViewChangeHistory();
		historyPage.waitLoading();
	}

	/**
	 * Go to vehicle detail page from change history page
	 */
	public void gotoVehicleDetailPageFromChangeHistoryPage() {
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();

		logger
				.info("Go to vehicle detail page from vehicle change history page.");
		historyPage.clickReturnToProductDetailButton();
		ajax.waitLoading();
		vehicleDetailPg.waitLoading();
	}

	/**
	 * Go to consumable details page from consumable search list page by click
	 * consumable id link
	 * 
	 * @param consumableID
	 */
	public void gotoConsumableProductDetailsPageFromListPage(String consumableID) {
		LicMgrConsumableListPage consumableListPg = LicMgrConsumableListPage
				.getInstance();
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();

		logger.info("Go to consumable(#=" + consumableID
				+ ") details page from consumable search list page.");
		consumableListPg.clickConsumableIDLink(consumableID);
		consumableDetailsPg.waitLoading();
	}

	/**
	 * The methods used to go to consumable question page from top menu
	 * 
	 * @param consumableID
	 *            : consumable ID
	 */
	public void gotoConsumableQuestionPgFromTopMenu(String consumableID) {
		this.gotoConsumableSearchListPageFromTopMenu();
		this.gotoConsumableProductDetailsPageFromListPage(consumableID);
		this.gotoConsumableQuestionPg();
	}

	public void gotoSupplySearchListPageFromTopMenu() {
		this.gotoProductSearchListPageFromTopMenu("Supplies");
	}

	/**
	 * Go to consumable question sub page from consumable details page
	 */
	public void gotoConsumableQuestionPg() {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();

		logger.info("Go to consumable question page from detail page.");
		consumableDetailsPg.clickQuestionsTab();
		ajax.waitLoading();
		consumableQestionPg.waitLoading();
	}

	/**
	 * Go to consumable Contractor Fees sub page from consumable details page
	 */
	public void gotoConsumableContractorFeesPg() {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrConsumableProductContractFeesPage consumableContractFeesPg = LicMgrConsumableProductContractFeesPage
				.getInstance();

		logger.info("Go to consumable contractor fees page from detail page.");
		consumableDetailsPg.clickContractFeesTab();
		consumableContractFeesPg.waitLoading();
	}

	/**
	 * The methods used to go to consumable Contractor Fees page from top menu
	 * 
	 * @param consumableID
	 *            : consumable ID
	 */
	public void gotoConsumableContractorFeesPgFromTopMenu(String consumableID) {
		this.gotoConsumableSearchListPageFromTopMenu();
		this.gotoConsumableProductDetailsPageFromListPage(consumableID);
		this.gotoConsumableContractorFeesPg();
	}

	/**
	 * The method used to search consumable question by criteria
	 * 
	 * @param question
	 *            search criteria
	 */
	public void searchConsumableQuestionByCriteria(QuestionInfo question) {
		LicMgrConsumableProductQuestionsPage consumableQestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();
		logger.info("Search consumable question by criteria.");

		consumableQestionPg.setSearchCriteria(question);
		consumableQestionPg.clickGo();
		consumableQestionPg.waitLoading();
	}

	/**
	 * Goto privilege order detail page from license home page to privilege
	 * order detail page
	 * 
	 * @param priviOrderNum
	 *            --- the order number after we purchase privilege
	 */
	public String gotoPrivilegeOrderDetailPage(String priviOrderNum) {
		LicenseMgrHomePage licMrgHomePg = LicenseMgrHomePage.getInstance();
		LicMgrPrivilegeOrderSearchPage priviOrderSearchPg = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrAlertPopupWidget alertPage = LicMgrAlertPopupWidget.getInstance();

		logger.info("----Goto privilege order(#=" + priviOrderNum
				+ ") detail page from license home page----");

		licMrgHomePg.setOrderNumber(priviOrderNum);
		licMrgHomePg.clickSearchInOrders();
		ajax.waitLoading();
		priviOrderSearchPg.waitLoading();
		priviOrderSearchPg.clickOrderNum(priviOrderNum);
		ajax.waitLoading();
		Object page = browser.waitExists(alertPage, privOrderDetailsPage);

		String returnInfo = StringUtil.EMPTY;
		if (page == alertPage) {
			returnInfo = alertPage.getAlertInfo();
			alertPage.clickOK();
			ajax.waitLoading();
			privOrderDetailsPage.waitLoading();
		}
		return returnInfo;
	}

	public void gotoApplicationOrderDetailsPageFromTopMenu(String ordNum) {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeOrderSearchPage privOrdSearchPage = LicMgrPrivilegeOrderSearchPage.getInstance();
		LicMgrApplicationOrderSearchPage searchPage = LicMgrApplicationOrderSearchPage.getInstance();
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		
		logger.info("Goto Application Order(#=" + ordNum + ") details page from top menu.");
		topMenu.clickOrders();
		privOrdSearchPage.waitLoading();
		privOrdSearchPage.clickApplicationOrdersTab();
		ajax.waitLoading();
		searchPage.waitLoading();
		searchPage.searchApplicationOrder(ordNum);
		searchPage.clickOrderNum(ordNum);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoActivityRegistrationOrderDetailPageFromTopMenu(String ordNum){
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeOrderSearchPage privOrdSearchPage = LicMgrPrivilegeOrderSearchPage.getInstance();
		LicMgrActivityRegistrationOrdersSearchPage searchPage = LicMgrActivityRegistrationOrdersSearchPage.getInstance();
		LicMgrActivityRegistrationOrderDetailPage detailsPage = LicMgrActivityRegistrationOrderDetailPage.getInstance();
		
		logger.info("Goto Activity Registration Order(#=" + ordNum + ") details page from top menu.");
		topMenu.clickOrders();
		privOrdSearchPage.waitLoading();
		privOrdSearchPage.clickActivityRegistrationOrdersTab();
		ajax.waitLoading();
		searchPage.waitLoading();
		searchPage.searchApplicationOrder(ordNum);
		searchPage.clickOrderNum(ordNum);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoApplicationOrderHistoryPgFromDetailPg(){
		LicMgrApplicationOrderHistoryPage historyPage = LicMgrApplicationOrderHistoryPage.getInstance();
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		logger.info("Go to history page of application lottery order");
		detailsPage.clickHistory();
		ajax.waitLoading();
		historyPage.waitLoading();
	}
	
	public void goBackToApplicationOrderPgFromHistoryPg(){
		LicMgrApplicationOrderHistoryPage historyPage = LicMgrApplicationOrderHistoryPage.getInstance();
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		logger.info("Go to application lottery order detail page from history page.");
		historyPage.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoApplicationOrderDetailsPageFromHomePage(String ordNum) {
		LicenseMgrHomePage homePage = LicenseMgrHomePage.getInstance();
		LicMgrApplicationOrderSearchPage searchPage = LicMgrApplicationOrderSearchPage.getInstance();
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		
		logger.info("Goto Application Order(#=" + ordNum + ") details page from license home page.");
		homePage.selectOrderSearchFor("Application");
		homePage.setOrderNumber(ordNum);
		homePage.clickSearchInOrders();
		ajax.waitLoading();
		searchPage.waitLoading();
		searchPage.clickOrderNum(ordNum);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void changeAppplicationOrderItemToCart(HFLotteryProduct lottery){
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		LicMgrPrivilegeLotteryManageChoicePage manageChoicePage = LicMgrPrivilegeLotteryManageChoicePage.getInstance();
		logger.info("Start to change application order item.");
		detailsPage.clickChange();
		ajax.waitLoading();
		manageChoicePage.waitLoading();
		manageChoicePage.clickAddChoice();
		ajax.waitLoading();
		addHuntToManageChoicePage(lottery);
		addPrivilegeLotteryItemToCartFromManageChoicePage();
	}

	public void reverseApplicationOrderToCart(String reason, String note) {
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		LicMgrReverseApplicationOrderWidget widget = LicMgrReverseApplicationOrderWidget.getInstance();
		
		logger.info("Reverse Application Order(#=" + detailsPage.getOrderNum() + ") to cart.");
		detailsPage.clickReverse();
		ajax.waitLoading();
		widget.waitLoading();
		widget.selectReverseReason(reason);
		widget.setReverseNotes(note);
		widget.clickOK();
		ajax.waitLoading();
		OrmsOrderCartPage.getInstance().waitLoading();
	}
	
	public void reverseAppOrderToAppOrderDetailsPg(String reason, String note) {
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		LicMgrReverseApplicationOrderWidget widget = LicMgrReverseApplicationOrderWidget.getInstance();
		LicMgrReverseAppOrderRefundWidget refundWidget = LicMgrReverseAppOrderRefundWidget.getInstance();
		logger.info("Reverse Application Order(#=" + detailsPage.getOrderNum() + ") to Application order details page.");
		detailsPage.clickReverse();
		ajax.waitLoading();
		widget.waitLoading();
		widget.selectReverseReason(reason);
		widget.setReverseNotes(note);
		widget.clickOK();
		ajax.waitLoading();
		if(refundWidget.exists()){
			refundWidget.clickOK();
			ajax.waitLoading();
		}
			
		detailsPage.waitLoading();
	}
	
	public void revokeAppOrderToAppOrderDetailsPg(String reason, String note) {
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		LicMgrRevokeLicenceAwardWidget widget = LicMgrRevokeLicenceAwardWidget.getInstance();
		logger.info("Revoke Application Order(#=" + detailsPage.getOrderNum() + ") to Application order details page.");
		detailsPage.clickRevoke();
		ajax.waitLoading();
		widget.waitLoading();
		widget.selectRevokeAwardReason(reason);
		widget.setRevokeNotes(note);
		widget.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	/**
	 * Goto receipt detail page from privilege order detail page
	 * */
	public String gotoReceiptPageFromPrivilegeOrder() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage
				.getInstance();
		logger.info("Go to receipt detail page from privilege detail page");

		String receiptNum = privOrderDetailsPage.getReceiptNum();
		privOrderDetailsPage.clickReceiptNum(receiptNum);
		receiptPg.waitLoading();
		return receiptNum;
	}

	/**
	 * go to receipt detail page from privilege order detail page.
	 * 
	 * @return
	 */
	public String gotoReceiptPageFromConsumableOrder() {
		LicMgrConsumableOrderDetailsPage consumableOrderDetailPg = LicMgrConsumableOrderDetailsPage
				.getInstance();
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage
				.getInstance();

		logger
				.info("Go to receipt detail page from consumale order detail page");
		String receiptNum = consumableOrderDetailPg.getReceiptNum();
		consumableOrderDetailPg.clickReceiptNum(receiptNum);
		receiptPg.waitLoading();
		return receiptNum;
	}

	/**
	 * Goto receipt detail page from vehicle order detail page
	 * */
	public String gotoReceiptPageFromVehicleOrder() {
		LicMgrVehicleOrderDetailsPage vehicleOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage
				.getInstance();
		logger.info("Go to receipt detail page from vehicle detail page");

		String receiptNum = vehicleOrderDetailsPage.getReceiptNum();
		vehicleOrderDetailsPage.clickReceiptNum(receiptNum);
		receiptPg.waitLoading();
		return receiptNum;
	}

	/**
	 * Download H&F receipt confirmation letter not on line.
	 * 
	 * @param pdfFileFolder
	 * @param receiptNum
	 * @return
	 */
	public String sentReceiptConfirmationLetterByEmail(String proName) {
		String msg = "";
		OrmsRequestHFConfirmLetterPage confirmLetterPg = OrmsRequestHFConfirmLetterPage
				.getInstance();
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage
				.getInstance();

		confirmLetterPg.selectEmailMethod();
		confirmLetterPg.clickOK();
		ajax.waitLoading();
		receiptPg.waitLoading();
		msg = receiptPg.getAlertMsg();

		return msg;
	}

	/**
	 * Download H&F receipt confirmation letter
	 * 
	 * @Param target folder to save the PDF file
	 * @return full path of the PDF file(including folder)
	 */
	public String getReceiptConfirmationLetterOnline(String pdfFileFolder,
			String receiptNum) {
		OrmsRequestHFConfirmLetterPage confirmLetterPg = OrmsRequestHFConfirmLetterPage
				.getInstance();
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage
				.getInstance();

		this.gotoConfimLetterPg();

		logger.info("Try to download PDF file online..");
		confirmLetterPg.selectOnlineMethod();
		confirmLetterPg.clickOK();
		ajax.waitLoading();

		downloadPage.setDismissible(false);
		downloadPage.setBeforePageLoading(false);
		downloadPage.waitLoading();// we must wait untill this page exist.

		File file = new File(pdfFileFolder);
		if (!file.exists()) {
			boolean exists = file.mkdir();
			if (!exists) {
				throw new RuntimeException("Failed to create directory - "
						+ pdfFileFolder);
			}
		}

		// download file
		String fullFileName = file.getAbsolutePath() + "\\"
				+ "ReceiptConfirmLetter_" + receiptNum + ".pdf";
		this.downloadFile(fullFileName);

		/**
		 * Since this method can be called from different pages path, it will
		 * not wait any pages at the end. For example: 1. privilege order detail
		 * page->receipt detail page-> get confirmation letter; the end page
		 * will be privilege order detail page 2. vehicle order detail
		 * page->receipt detail page-> get confirmation letter; the end page
		 * will be vehicle order detail page
		 * 
		 * So, those who will use this method, should add somepage.waitExist()
		 * out of this method.
		 * */

		return fullFileName;

	}

	public void addToCartFromReceiptDetailsPage() {
		OrmsReceiptDetailsPage detailsPage = OrmsReceiptDetailsPage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		detailsPage.clickAddToCart();
		cartPage.waitLoading();
	}
	
	/**
	 * Goto privilege order fees detail page from privilege order detail page
	 * 
	 */
	public void gotoPrivilegeOrderFeesDetailPage() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage
				.getInstance();

		privOrderDetailsPage.clickFees();
		ajax.waitLoading();
		priFeesPage.waitLoading();
	}
	
	public void gotoPrivilegeOrderDetailPageFromFeeDetailPage(){
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage
				.getInstance();
		
		logger.info("Go to privilege order detail page from fee detail page.");
		priFeesPage.clickOK();
		ajax.waitLoading();
		privOrderDetailsPage.waitLoading();
	}

	/**
	 * input new value for Fee adjust on fee detail page, and process to order
	 * summary page.
	 * 
	 * 
	 * @param transType
	 *            --- Transaction type of the contractor fee
	 * @param newValue
	 *            --- New value for transaction type
	 * 
	 * @param notes
	 *            ---adjustment notes
	 */
	public void processFeesAdjustment(String transType, String newValue,
			String notes) {
		LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		priFeesPage.inputAdjustmentValue(transType, newValue);
		priFeesPage.inputAdjustmentNotes(notes);
		priFeesPage.clickOK();
		ajax.waitLoading();
		ordCartPg.waitLoading();
	}

	/**
	 * Goto vehicle order detail page from license home page
	 * 
	 * @param ordNum
	 *            --- the order number after we register a vehicle
	 */
	public void gotoVehicleOrderDetailPage(String ordNum) {
		LicenseMgrHomePage licMgrHomePg = LicenseMgrHomePage.getInstance();
		LicMgrVehicleOrderSearchPage vehicleOrdSearchPg = LicMgrVehicleOrderSearchPage
				.getInstance();
		LicMgrVehicleOrderDetailsPage vehicleOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();

		logger.info("----Goto vehicle order(#=" + ordNum
				+ ") detail page from license home page----");

		if(!licMgrHomePg.exists()) {
			this.gotoHomePage();
		}
		licMgrHomePg.selectOrderSearchFor("Vehicle");
		licMgrHomePg.setOrderNumber(ordNum);
		licMgrHomePg.clickSearchInOrders();
		ajax.waitLoading();
		vehicleOrdSearchPg.waitLoading();
		vehicleOrdSearchPg.clickOrderNum(ordNum);
		ajax.waitLoading();
		vehicleOrdDetailsPg.waitLoading();
	}

	/**
	 * Reverse vehicle order to order cart page from vehicle detail page.
	 * 
	 * @param reason
	 * @param note
	 */
	public void reverseVehicleOrderToOrderCartFromVehicleDetailPg(
			String reason, String note) {
		LicMgrVehicleOrderDetailsPage vehicleOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget reveserReasonPg = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrConfirmDialogWidget confirmPg = LicMgrConfirmDialogWidget
				.getInstance();

		logger
				.info("Reserve vehicle order to order cart page from vehicle detail page.");
		vehicleOrdDetailsPg.clickReverseOrder();
		ajax.waitLoading();
		Object pages = browser.waitExists(reveserReasonPg, confirmPg);
		if (confirmPg == pages) {
			confirmPg.clickOK();
			ajax.waitLoading();
			reveserReasonPg.waitLoading();
		}
		reveserReasonPg.setupConfirmInfo(reason, note);
		orderCartPg.waitLoading();
	}

	/**
	 * Reverse Vehicle Order Item by the product name and purchase type
	 * 
	 * @param reason
	 * @param note
	 * @param product
	 * @param type
	 * @author Lesley Wang
	 * @date Jun 19, 2012
	 */
	public void reverseVehicleOrderItemOnOrdDetailsPg(String reason,
			String note, String product, String type) {
		LicMgrVehicleOrderDetailsPage vehicleOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget reveserReasonPg = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrConfirmDialogWidget confirmPg = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();

		logger.info("Reserve vehicle order item with product name=" + product
				+ " purchaseType=" + type + " on vehicle detail page.");
		vehicleOrdDetailsPg.selectOrderItemByProductNmAndType(product, type);
		vehicleOrdDetailsPg.clickReverse();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmPg, reveserReasonPg);
		if (confirmPg == pages) {
			confirmPg.clickOK();
			ajax.waitLoading();
			reveserReasonPg.waitLoading();
		}
		reveserReasonPg.setupConfirmInfo(reason, note);
		browser.waitExists(orderCartPg, refundWidget, vehicleOrdDetailsPg);
	}

	/**
	 * Goto vehicle order fees detail page from vehicle order detail page
	 * 
	 */
	public void gotoVehicleOrderFeesDetailPage() {
		LicMgrVehicleOrderDetailsPage vehicleOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage priFeesPage = LicMgrOrderFeesDetailsPage
				.getInstance();

		vehicleOrderDetailsPage.clickFees();
		ajax.waitLoading();
		priFeesPage.waitLoading();
	}

	/**
	 * Goto vehicle order history page from vehicle order detail page
	 * 
	 */
	public void gotoVehicleOrderHistoryPage() {
		LicMgrVehicleOrderDetailsPage vehicleOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVehicleOrderHistoryPage historyPage = LicMgrVehicleOrderHistoryPage
				.getInstance();

		vehicleOrderDetailsPage.clickHistory();
		ajax.waitLoading();
		historyPage.waitLoading();
	}

	/**
	 * This method starts with top menu and ends with privilege order detail
	 * page
	 * 
	 * @param orderNum
	 */
	public void gotoPrivilegeOrderDetailPageFromTopMenu(String orderNum) {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrPrivilegeOrderDetailsPage orderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();

		logger.info("Go to privilege order(#=" + orderNum
				+ ") detail page from top menu.");
		if (topMenuPage.checkOrdersExists()) {
			topMenuPage.clickOrders();
		} else {
			topMenuPage.selectAdminOptions("Orders");
		}
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.selectOrderSearchType("Order #");
		privOrderSearchPage.setOrderSearchValue(orderNum);
		privOrderSearchPage.clickSearch();
		ajax.waitLoading();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickOrderNum(orderNum);
		ajax.waitLoading();
		orderDetailPage.waitLoading();
	}

	/**
	 * Go to lotteries product List page from top menu
	 */
	public void gotoLotteriesProductListPgFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		String adminValue = topMenuPg.getAdminValue();

		logger.info("Go to lotteries product list page.");
		if (!adminValue.equals("Lotteries")) {
			topMenuPg.selectAdminOptions("Lotteries");
		} else {
			topMenuPg.clickAdmin();
		}

		lotteriesProductListPg.waitLoading();
	}
	
	public void gotoFeeScheduleListPageFromTopMenu(){
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
		String adminValue = topMenuPg.getAdminValue();
		logger.info("Go to fee schedule list page.");
		if (!adminValue.equals("Fee Schedule")) {
			topMenuPg.selectAdminOptions("Fee Schedule");
		} else {
			topMenuPg.clickAdmin();
		}
		feeScheduleListPg.waitLoading();
	}
	
	public void gotoTaxListPageFromRightTopMenu(){
		LicMgrTopMenuPage lmTopMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrTaxSearchPage taxListPg = LicMgrTaxSearchPage.getInstance();
		logger.info("Go to tax detail page.");
		lmTopMenuPg.selectFinancialsOptions("Tax");
		taxListPg.waitLoading();
	}
	
	public String addNewTax(Tax tax){
		LicMgrTaxSearchPage taxListPg = LicMgrTaxSearchPage.getInstance();
		LicMgrTaxDetialPg detailPg = LicMgrTaxDetialPg.getInstance();
		logger.info("Add a new tax with name:"+ tax.taxName);
		taxListPg.clickAddNew();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setTaxInfoAndClickApply(tax);
		String idOrErr = detailPg.getTaxId();
		if(idOrErr.equalsIgnoreCase("New")){//The tax is not added correctly
			idOrErr = detailPg.getErrorMsg();
		}else{
			detailPg.clickOk();
			ajax.waitLoading();
			taxListPg.waitLoading();
		}
		return idOrErr;
	}
	
	public void gotoTaxDetailPage(String taxName){
		LicMgrTaxSearchPage taxSearchPg = LicMgrTaxSearchPage.getInstance();
		LicMgrTaxDetialPg detailPg = LicMgrTaxDetialPg.getInstance();
		logger.info("Go to tax detail page for tax with name:" + taxName);
		taxSearchPg.searchByTaxName(taxName);
		taxSearchPg.clickTaxName(taxName);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	public void gotoTaxScheduelListPage(){
		LicMgrTaxSearchPage taxListPg = LicMgrTaxSearchPage.getInstance();
		LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
		logger.info("Go to tax schedule list page.");
		if(!taxListPg.exists()){
			this.gotoTaxListPageFromRightTopMenu();
		}
		taxListPg.clickTaxScheduleLable();
		ajax.waitLoading();
		scheduleListPg.waitLoading();
	}
	
	public String addNewTaxSchedule(ScheduleData schedule){
		LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
		LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
		logger.info("Go to add a new tax schedule!");
		scheduleListPg.clickAddNew();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setUpTaxScheduleAndClickApply(schedule);
		String idOrErr = detailPg.getTaxScheduleId();
		if(idOrErr.equalsIgnoreCase("New")){//The tax is not added correctly
			idOrErr = detailPg.getErrorMsg();
		}else{
			detailPg.clickOk();
			ajax.waitLoading();
			scheduleListPg.waitLoading();
		}
		return idOrErr;
	}
	
	public String editTaxSchedule(ScheduleData schedule){
		LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
		LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
		logger.info("Update tax schedule with schedule id:" + schedule.scheduleId);
		if(!detailPg.exists()){
			this.gotoTaxScheduleDetailPage(schedule.scheduleId);
		}
		detailPg.setUpTaxScheduleAndClickApply(schedule);
		String ErrMsgOrId = detailPg.getErrorMsg();
		if(StringUtil.isEmpty(ErrMsgOrId)){
			ErrMsgOrId = detailPg.getTaxScheduleId();
		}
		detailPg.clickOk();
		ajax.waitLoading();
		scheduleListPg.waitLoading();
		return ErrMsgOrId;
	}
	
	public void gotoTaxScheduleDetailPage(String id){
		LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
		LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
		logger.info("Go to tax schedule detail page with id:" + id);
		scheduleListPg.searchByScheduleId(id);
		scheduleListPg.clickScheduleId(id);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	public String addNewFeeSchedule(FeeScheduleData schedule){
		LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
		LicMgrSelectFeeTypePage selectFeeTypePg = LicMgrSelectFeeTypePage.getInstance();
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		logger.info("Go to add a new fee schedule.");
		feeScheduleListPg.clickAddNew();
		ajax.waitLoading();
		selectFeeTypePg.waitLoading();
		selectFeeTypePg.setUpProductCatAndFeeType(schedule.productCategory, schedule.feeType);
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setUpFeeScheduleInfoAndClickApply(schedule);
		String idOrErrMsg = detailPg.getFeeScheduleId();
		if(idOrErrMsg.equalsIgnoreCase("New")){
			idOrErrMsg = detailPg.getErrorMsg();
		}else{
			detailPg.clickOk();
			ajax.waitLoading();
			feeScheduleListPg.waitLoading();
		}
		return idOrErrMsg;
	}
	
	public void addNewRAFeeToFeeDetailPg(RaFeeScheduleData raFee) {
		LicMgrRAFeeScheduleSearchPage raFeeSearchPg = LicMgrRAFeeScheduleSearchPage.getInstance();
		LicMgrFeeFindLocationPage findLocPg = LicMgrFeeFindLocationPage.getInstance();
		LicMgrRAFeeScheduleDetailPage detailPg = LicMgrRAFeeScheduleDetailPage.getInstance();
		raFeeSearchPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(raFee.location, raFee.locationCategory);
		findLocPg.selectLocation(raFee.location);
		ajax.waitLoading();
		detailPg.waitLoading();	
	}
	
	public String setUpInfoAndClickApply(RaFeeScheduleData raFee){
		LicMgrRAFeeScheduleSearchPage raFeeSearchPg = LicMgrRAFeeScheduleSearchPage.getInstance();
		LicMgrRAFeeScheduleDetailPage detailPg = LicMgrRAFeeScheduleDetailPage.getInstance();
		detailPg.setUpInfoAndClickApply(raFee);
		detailPg.waitLoading();
		String idOrErrMsg = detailPg.getRaFeeScheduleId();
		if(idOrErrMsg.equalsIgnoreCase("New")){
			idOrErrMsg = detailPg.getErrorMsg();
		}else{
			detailPg.clickOk();
			ajax.waitLoading();
			raFeeSearchPg.waitLoading();
		}
		return idOrErrMsg;
	}
	
	
	public String addNewRaFeeSchedule(RaFeeScheduleData raFee){
		this.addNewRAFeeToFeeDetailPg(raFee);
		String idOrErr = this.setUpInfoAndClickApply(raFee);
		return idOrErr;
	}
	
	/**
	 * The scheduleId is the id for schedule before update, for the schedule id may changed after update
	 * editSchedule is the updated schedule information 
	 * @param scheduleId
	 * @param editSchedule
	 */
	public String editRAFeeSchedule(String scheduleId, RaFeeScheduleData editRaFee){
		LicMgrRAFeeScheduleSearchPage raFeeSearchPg = LicMgrRAFeeScheduleSearchPage.getInstance();
		LicMgrRAFeeScheduleDetailPage detailPg = LicMgrRAFeeScheduleDetailPage.getInstance();
		logger.info("Update fee schedule with schedule id:" + scheduleId);
		if(!detailPg.exists()){
			this.gotoFeeScheduleDetailPgFromListPg(scheduleId);
		}
		detailPg.setUpInfoAndClickApply(editRaFee);
		String ErrMsgOrId = detailPg.getErrorMsg();
		if(StringUtil.isEmpty(ErrMsgOrId)){
			ErrMsgOrId = detailPg.getRaFeeScheduleId();
		}
		detailPg.clickOk();
		ajax.waitLoading();
		raFeeSearchPg.waitLoading();
		return ErrMsgOrId;
	}
	
	public void gotoRAFeeScheduleDetailPgFromListPg(String scheduleId){
		LicMgrRAFeeScheduleSearchPage raFeeListPg = LicMgrRAFeeScheduleSearchPage.getInstance();
		LicMgrRAFeeScheduleDetailPage detailPg = LicMgrRAFeeScheduleDetailPage.getInstance();
		logger.info("Go to ra fee schedule detail page from list page, schedule id:" + scheduleId);
		raFeeListPg.searchByFeeScheduleId(scheduleId);
		raFeeListPg.clickFeeScheduleId(scheduleId);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	public void gotoFeeScheduleDetailPgFromListPg(String scheduleId){
		LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		logger.info("Go to fee schedule detail page from list page, schedule id:" + scheduleId);
		feeScheduleListPg.searchByFeeScheduleId(scheduleId);
		feeScheduleListPg.clickFeeScheduleId(scheduleId);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	/**
	 * The scheduleId is the id for schedule before update, for the schedule id may changed after update
	 * editSchedule is the updated schedule information 
	 * @param scheduleId
	 * @param editSchedule
	 */
	public String editFeeSchedule(String scheduleId, FeeScheduleData editSchedule){
		LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		logger.info("Update fee schedule with schedule id:" + scheduleId);
		if(!detailPg.exists()){
			this.gotoFeeScheduleDetailPgFromListPg(scheduleId);
		}
		detailPg.setUpFeeScheduleInfoAndClickApply(editSchedule);
		String ErrMsgOrId = detailPg.getErrorMsg();
		if(StringUtil.isEmpty(ErrMsgOrId)){
			ErrMsgOrId = detailPg.getFeeScheduleId();
		}
		detailPg.clickOk();
		ajax.waitLoading();
		feeScheduleListPg.waitLoading();
		return ErrMsgOrId;
	}

	/**
	 * Go to hunts list page, start from lotteries product list page, end at
	 * hunts list page
	 */
	public void gotoHuntsListPgFromLotteriesProdListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();

		logger.info("Go to hunts page from lotteries product list page.");
		lotteriesProductListPg.clickHuntsTab();
		ajax.waitLoading();
		huntsListPg.waitLoading();
	}

	/**
	 * Go to Hunts List page from top menu
	 */
	public void gotoHuntsListPg() {
		this.gotoLotteriesProductListPgFromTopMenu();
		this.gotoHuntsListPgFromLotteriesProdListPg();
	}
	
	/**
	 * Go to lotteries product list page from lotteries hunt list page
	 */
	public void gotoLotteriesProductListPgFromLotteriesHuntsListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();

		logger
				.info("Go to lotteries product list page from lotteris hunt list page.");
		huntsListPg.clickProductTab();
		ajax.waitLoading();
		lotteriesProductListPg.waitLoading();
	}

	/**
	 * Go to Date periods list page, start from lotteries product list page, end
	 * at Date periods list page
	 */
	public void gotoDatePeriodListPgFromLotteriesProdListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrDatePeriodsListPage datePg = LicMgrDatePeriodsListPage
				.getInstance();

		logger
				.info("Go to Date Periods list page from lotteries product list page.");
		lotteriesProductListPg.clickDatePeriodsTab();
		ajax.waitLoading();
		datePg.waitLoading();
	}

	/**
	 * Go to Quota list page, start from lotteries product list page, end at
	 * Quota list page
	 */
	public void gotoQuotaListPgFromLotteriesProdListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrQuotaListPage quotaLisPg = LicMgrQuotaListPage.getInstance();
		logger.info("Go to Quota list page from lotteries product list page.");
		lotteriesProductListPg.clickQuotaTab();
		ajax.waitLoading();
		quotaLisPg.waitLoading();
	}

	public void gotoPointTypesListPgFromLotteriesProdListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrPointTypesListPage pointTypesLisPg = LicMgrPointTypesListPage.getInstance();
		logger.info("Go to Point types list page from lotteries product list page.");
		lotteriesProductListPg.clickPointTypesTab();
		ajax.waitLoading();
		pointTypesLisPg.waitLoading();
	}
	
	public void gotoPointTypesDetailsPgFromPointTypeListPg(String pointTypeID) {
		LicMgrPointTypesListPage pointTypesLisPg = LicMgrPointTypesListPage.getInstance();
		LicMgrPointTypeDetailsPage pointTypeDetailsPg = LicMgrPointTypeDetailsPage.getInstance();
		logger.info("Go to Point types details page from point type list page.");
		if(StringUtil.isEmpty(pointTypeID)){
			pointTypesLisPg.clickAddPointType();
		}else //Todo, click point type id
	
		ajax.waitLoading();
		pointTypeDetailsPg.waitLoading();
	}
	
	/** Go to Quota Details page from Quota List page by quota id */
	public void gotoQuotaDetailsPgFromQuotaListPg(String id) {
		LicMgrQuotaListPage quotaListPg = LicMgrQuotaListPage.getInstance();
		LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
		
		logger.info("Go to Quota Details page from Quota List page for quota " + id);
		quotaListPg.clickQuotaID(id);
		ajax.waitLoading();
		quotaDetailsPg.waitLoading();
	}
	
	public void gotoQuotaDetailViaQuotaDescription(String desc){
		LicMgrQuotaListPage quotaListPg = LicMgrQuotaListPage.getInstance();
		
		String id = quotaListPg.getQuotaID(desc);
		gotoQuotaDetailsPgFromQuotaListPg(id);
	}
	
	public void gotoQutoListPageFromQuotaDetailPg(){
		LicMgrQuotaListPage quotaListPg = LicMgrQuotaListPage.getInstance();
		LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
		
		logger.info("Go to Quota list page from Quota detail page.");
		quotaDetailsPg.clickCancel();
		ajax.waitLoading();
		quotaListPg.waitLoading();
	}
	
	public void saveOrCancelToEditQuotaDetails(boolean isOK) {
		LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
		LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
		
		if (isOK) {
			quotaDetailsPg.clickOK();
		} else {
			quotaDetailsPg.clickCancel();
		}
		ajax.waitLoading();
		browser.waitExists(listPg, quotaDetailsPg);
	}
	
	/**
	 * Go to Hunt Location list page, start from lotteries product list page,
	 * end at Hunt Location list page
	 */
	public void gotoHuntLocationListPgFromLotteriesProdListPg() {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrHuntLocationsListPage huntLocLisPg = LicMgrHuntLocationsListPage
				.getInstance();
		logger
				.info("Go to Hunt Locations list page from lotteries product list page.");
		lotteriesProductListPg.clickHuntLocationsTab();
		ajax.waitLoading();
		huntLocLisPg.waitLoading();
	}

	/**
	 * Go to add Date periods page, start from Date periods list page, end at
	 * add Date periods page
	 */
	public void gotoAddDatePeriodPgFromListPg() {
		LicMgrDatePeriodsListPage listPg = LicMgrDatePeriodsListPage
				.getInstance();
		LicMgrAddDatePeriodsPage addPg = LicMgrAddDatePeriodsPage.getInstance();

		logger.info("Go to add Date Periods page from list page.");
		listPg.clickAddDatePeriod();
		ajax.waitLoading();
		addPg.waitLoading();
	}

	/**
	 * Go to add Quota page, start from Quota list page, end at add Quota page
	 */
	public void gotoAddQuotaPgFromListPg() {
		LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
		LicMgrAddQuotaPage addPg = LicMgrAddQuotaPage.getInstance();
		logger.info("Go to add Quota page from list page.");
		listPg.clickAddQuota();
		ajax.waitLoading();
		addPg.waitLoading();
	}

	/**
	 * Go to add Hunt Location page, start from Hunt Location list page, end at
	 * add Hunt Location page
	 */
	public void gotoAddHuntLocationPgFromListPg() {
		LicMgrHuntLocationsListPage listPg = LicMgrHuntLocationsListPage
				.getInstance();
		LicMgrAddHuntLocationPage addPg = LicMgrAddHuntLocationPage
				.getInstance();
		logger.info("Go to add Hunt Location page from list page.");
		listPg.clickAddHuntLocation();
		ajax.waitLoading();
		addPg.waitLoading();
	}

	/**
	 * Go to Date periods detail page, start from Date periods list page, end at
	 * add Date periods detail page
	 */
	public void gotoDatePeriodDetailPgFromListPg(String code) {
		LicMgrDatePeriodsListPage listPg = LicMgrDatePeriodsListPage
				.getInstance();
		LicMgrDatePeriodsDetailPage detailPg = LicMgrDatePeriodsDetailPage
				.getInstance();

		logger.info("Go to Date Periods detail page from list page.");
		listPg.clickCode(code);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * Go to date period hunt assignment list page from date period detail page
	 */
	public void gotoDatePeriodHuntAssignmentListPgFromDatePeriodDetailPg() {
		LicMgrDatePeriodsDetailPage detailPg = LicMgrDatePeriodsDetailPage
				.getInstance();
		LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage
				.getInstance();

		logger
				.info("Go to date period hunt assignment list page from date period detail page.");
		detailPg.clickDatePeriodHuntsTab();
		ajax.waitLoading();
		datePeriodHuntsAssignmentListPg.waitLoading();
	}

	/**
	 * Go to Date Period List page from top menu
	 */
	public void gotoDatePeriodListPg() {
		this.gotoLotteriesProductListPgFromTopMenu();
		this.gotoDatePeriodListPgFromLotteriesProdListPg();
	}
	
	/**
	 * Go to date period list page from lotteries hunts page, end at date period
	 * list page
	 */
	public void gotoDatePeriodListPgFromLotteriesHuntsListPg() {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		LicMgrDatePeriodsListPage datePg = LicMgrDatePeriodsListPage
				.getInstance();

		logger
				.info("Go to Date Periods list page from lotteries hunts list page.");
		huntsListPg.clickDatePeriodsTab();
		ajax.waitLoading();
		datePg.waitLoading();
	}

	/**
	 * Filter Date Period hunt assignment info at date period hunt assignment
	 * list page
	 * 
	 * @param status
	 */
	public void filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(
			String status) {
		LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage
				.getInstance();

		logger.info("Filter Date Period Hunt Assignment info.");
		datePeriodHuntsAssignmentListPg.selectFilterStatus(status);
		datePeriodHuntsAssignmentListPg.clickGo();
		ajax.waitLoading();
		datePeriodHuntsAssignmentListPg.waitLoading();
	}

	/**
	 * Assign or Unassign hunt to date period Start from date period hunt
	 * assignment list page End at date priod hunt assignment list page
	 * 
	 * @param isAssign
	 * @param huntInfo
	 */
	void assignOrUnassignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(
			boolean isAssign, String huntInfo) {
		LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage
				.getInstance();

		logger.info("Assign or Unassign hunt to date period. Hunt info = "
				+ huntInfo);
		datePeriodHuntsAssignmentListPg.selectHuntInfoCheckBox(huntInfo);
		if (isAssign) {
			datePeriodHuntsAssignmentListPg.clickAssign();
		} else {
			datePeriodHuntsAssignmentListPg.clickUnassign();
		}
		ajax.waitLoading();
		datePeriodHuntsAssignmentListPg.waitLoading();
	}

	/**
	 * Assign hunt to date period from date period hunt assignment list page
	 * 
	 * @param huntInfo
	 */
	public void assignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(
			String huntInfo) {
		this.assignOrUnassignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(
				true, huntInfo);
	}

	/**
	 * Unassign hunt to date period at date period hunt assignment list page
	 * 
	 * @param huntInfo
	 */
	public void unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(
			String huntInfo) {
		this.assignOrUnassignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(
				false, huntInfo);
	}

	/**
	 * Go to HIP reporting common page from top menu
	 */
	public void gotoHIPReportingCommonPageFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrHIPReportingCommonPage hipReportingCommonPg = LicMgrHIPReportingCommonPage
				.getInstance();
		String adminValue = topMenuPg.getAdminValue();

		logger.info("Go to HIP Reporting common page.");
		if (!adminValue.equals("HIP Reporting")) {
			topMenuPg.selectAdminOptions("HIP Reporting");
		} else {
			topMenuPg.clickAdmin();
		}

		hipReportingCommonPg.waitLoading();
	}

	/**
	 * Go to HIP Reporting schedules list page from HIP reporting common page
	 */
	public void gotoHIPReportingSchedulesListPageFromHIPReprotingCommonPage() {
		LicMgrHIPReportingCommonPage hipReportingCommonPg = LicMgrHIPReportingCommonPage
				.getInstance();
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();

		logger
				.info("Go to HIP Reporting schedules list page from HIP Reproting common page.");
		hipReportingCommonPg.clickHIPReportingSchedulesLabel();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
	}

	/**
	 * Add HIP Reporting schedule from HIP Reporting schedule list page
	 * 
	 * @param reportingScheduleInfo
	 * @param isClickOK
	 * @return
	 */
	String addHIPReportingScheduleFromHIPReportingSchedulesListPage(
			HIPReportingScheduleInfo reportingScheduleInfo, boolean isClickOK) {
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();
		LicMgrAddHIPReportingScheduleWidget addHIPReportingSchedulePg = LicMgrAddHIPReportingScheduleWidget
				.getInstance();
		String value;

		logger
				.info("Add HIP Reporting scheduel from HIP Reporting schedules list page.");
		hipReportingScheduleListPg.clickAddHIPReportingSchedules();
		ajax.waitLoading();
		addHIPReportingSchedulePg.waitLoading();
		value = addHIPReportingSchedulePg
				.setHIPReportingScheduleInfo(reportingScheduleInfo);
		if(StringUtil.notEmpty(value)){
			return value;
		}
		if (isClickOK) {
			addHIPReportingSchedulePg.clickOK();
		} else {
			addHIPReportingSchedulePg.clickCancel();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(addHIPReportingSchedulePg,
				hipReportingScheduleListPg);
		if (hipReportingScheduleListPg == pages) {
			value = hipReportingScheduleListPg
					.getHIPReportScheduleID(reportingScheduleInfo);
		} else {
			value = addHIPReportingSchedulePg.getMessage();
		}

		return value;
	}

	/**
	 * Add HIP Reporting Schedule From HIP Reporting Schedule List page with
	 * click OK
	 * 
	 * @param reportingScheduleInfo
	 * @return
	 */
	public String addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(
			HIPReportingScheduleInfo reportingScheduleInfo) {
		return this.addHIPReportingScheduleFromHIPReportingSchedulesListPage(
				reportingScheduleInfo, true);
	}

	/**
	 * Add HIP Reporting Schedule From HIP Reporting Schedule List page with
	 * click Cancel
	 * 
	 * @param reportingScheduleInfoc
	 * @return
	 */
	public String addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickCancel(
			HIPReportingScheduleInfo reportingScheduleInfo) {
		return this.addHIPReportingScheduleFromHIPReportingSchedulesListPage(
				reportingScheduleInfo, false);
	}

	/**
	 * Go to HIP Reporting schedule detail page from HIP Reporting Schedule list
	 * page
	 * 
	 * @param scheduleID
	 */
	public void gotoHIPReportingScheduleDetailPgFromHIPReportingScheduleListPg(
			String scheduleID) {
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();
		LicMgrEditHIPReportingScheduleWidget hipReportingScheduleDetaiPg = LicMgrEditHIPReportingScheduleWidget
				.getInstance();

		logger
				.info("Go to HIP Reporting Schedule Detail page from HIP Reporting schedule list page.");
		hipReportingScheduleListPg.clickHIPReportingScheduleID(scheduleID);
		ajax.waitLoading();
		hipReportingScheduleDetaiPg.waitLoading();
	}

	/**
	 * Edit HIP Reporting schedule info from HIP Reporting schedule list page
	 * 
	 * @param reportingScheduleInfo
	 * @param isClickOK
	 */
	String editHIPReportingScheduleInfoFromHIPReportingScheduleListPg(
			HIPReportingScheduleInfo reportingScheduleInfo, boolean isClickOK) {
		LicMgrEditHIPReportingScheduleWidget hipReportingScheduleDetaiPg = LicMgrEditHIPReportingScheduleWidget
				.getInstance();
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();
		String value = "";

		logger
				.info("Edit HIP Reporting schedule info from HIP Reporting schedule list page. schedule id = "
						+ reportingScheduleInfo.getScheduleID());
		this
				.gotoHIPReportingScheduleDetailPgFromHIPReportingScheduleListPg(reportingScheduleInfo
						.getScheduleID());
		hipReportingScheduleDetaiPg.editHIPReportingScheduleInfo(
				reportingScheduleInfo.getExecutionDates(),
				reportingScheduleInfo.getStatus());
		if (isClickOK) {
			hipReportingScheduleDetaiPg.clickOK();
		} else {
			hipReportingScheduleDetaiPg.clickCancel();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(hipReportingScheduleDetaiPg,
				hipReportingScheduleListPg);
		if (hipReportingScheduleListPg == pages) {
			value = hipReportingScheduleListPg
					.getHIPReportScheduleID(reportingScheduleInfo);
		} else {
			value = hipReportingScheduleDetaiPg.getMessage();
		}
		hipReportingScheduleListPg.waitLoading();
		return value;
	}

	/**
	 * Edit HIP Reporting schedule info from HIP Reporting schedule list page
	 * with click OK
	 * 
	 * @param reportingScheduleInfo
	 */
	public String editHIPReportingScheduleInfoFromHIPReportingScheduleListPgWithClickOK(
			HIPReportingScheduleInfo reportingScheduleInfo) {
		return this.editHIPReportingScheduleInfoFromHIPReportingScheduleListPg(
				reportingScheduleInfo, true);
	}

	/**
	 * Edit HIP Reporting schedule info from HIP Reporting schedule list page
	 * with click Cancel
	 * 
	 * @param reportingScheduleInfo
	 */
	public String editHIPReportingScheduleInfoFromHIPReportingScheduleListPgWithClickCancel(
			HIPReportingScheduleInfo reportingScheduleInfo) {
		return this.editHIPReportingScheduleInfoFromHIPReportingScheduleListPg(
				reportingScheduleInfo, false);
	}

	/**
	 * Filter HIP Reporting schedule from HIP Reporting schedule list page
	 * 
	 * @param filterStatus
	 * @param isShowCurrentRecordOnly
	 */
	public void filterHIPReportingScheduleFromHIPReportingScheduleListPg(
			String filterStatus, boolean isShowCurrentRecordOnly) {
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();

		logger.info("Filter HIP Reporting schedule info.");
		hipReportingScheduleListPg.setFilterHIPReportingSchedule(filterStatus,
				isShowCurrentRecordOnly);
		hipReportingScheduleListPg.clickGo();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
	}

	/**
	 * Deactivated HIP Reporting schedule
	 */
	public void deactivateHIPReportingSchedule(String scheduleID) {
		LicMgrEditHIPReportingScheduleWidget hipReportingScheduleDetaiPg = LicMgrEditHIPReportingScheduleWidget
				.getInstance();
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage
				.getInstance();

		logger.info("Deactivate HIP Reporting schedule.");
		this
				.gotoHIPReportingScheduleDetailPgFromHIPReportingScheduleListPg(scheduleID);
		hipReportingScheduleDetaiPg
				.selectStatus(INACTIVE_STATUS);
		hipReportingScheduleDetaiPg.clickOK();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
	}

	/**
	 * Go to HIP Reporting Job list page from HIP reporting common page
	 */
	public void gotoHIPReportingJobListPageFromHIPReprotingCommonPage() {
		LicMgrHIPReportingCommonPage hipReportingCommonPg = LicMgrHIPReportingCommonPage
				.getInstance();
		LicMgrHIPReportingJobsListPage hipReportingJobListPg = LicMgrHIPReportingJobsListPage
				.getInstance();

		logger
				.info("Go to HIP Reporting Job list page from HIP Reproting common page.");
		hipReportingCommonPg.clickHIPReportingJobsLabel();
		ajax.waitLoading();
		hipReportingJobListPg.waitLoading();
	}

	/**
	 * Search HIP Reporting job info from HIP Reporting job list page
	 * 
	 * @param searchType
	 * @param searchValue
	 * @param reportingJobStatus
	 */
	public void searchHIPReportingJobInfoFromHIPReportingJobListPg(
			String searchType, String searchValue, String reportingJobStatus) {
		LicMgrHIPReportingJobsListPage hipReportingJobListPg = LicMgrHIPReportingJobsListPage
				.getInstance();

		logger.info("Search HIP Reporting Job info.");
		hipReportingJobListPg.setSearchCriteria(searchType, searchValue,
				reportingJobStatus);
		hipReportingJobListPg.clickSearch();
		ajax.waitLoading();
		hipReportingJobListPg.waitLoading();
	}

	public void deactivateDatePeriod() {
		LicMgrDatePeriodsDetailPage detailsPage = LicMgrDatePeriodsDetailPage
				.getInstance();

		detailsPage.selectStatus(INACTIVE_STATUS);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}

	public void gotoDatePeriodLicenseYearTab() {
		LicMgrDatePeriodsDetailPage datePeriodDetailsPage = LicMgrDatePeriodsDetailPage
				.getInstance();
		LicMgrDatePeriodLicenseYearsListPage licenseYearPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();

		datePeriodDetailsPage.clickDatePeriodLicenseYearsTab();
		ajax.waitLoading();
		licenseYearPage.waitLoading();
	}
	
	public void gotoDatePeriodLicenseYearDetailsWidget(DatePeriodLicenseYearInfo year){
		LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();

		logger.info("Go to Date Period License Year(LicenseYear=" + year.getLicenseYear()
				+ ") details widget.");
		listPage.searchLicenseYear(year.getStatus(), year.getLicenseYear());
		String id = "";
		if(StringUtil.notEmpty(year.getId())){
			id = year.getId();
		}else{
			id = listPage.getLicenseYearID(year.getLicenseYear());
		}
		listPage.clickID(id);
		ajax.waitLoading();
		LicMgrEditDatePeriodLicenseYearWidget.getInstance().waitLoading();
	}

	public void gotoDatePeriodLicenseYearDetailsWidget(String licenseYear) {
		LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();

		logger.info("Go to Date Period License Year(LicenseYear=" + licenseYear
				+ ") details widget.");
		listPage.searchLicenseYear(licenseYear);
		String id = listPage.getLicenseYearID(licenseYear);
		listPage.clickID(id);
		ajax.waitLoading();
		LicMgrEditDatePeriodLicenseYearWidget.getInstance().waitLoading();
	}

	public void gotoDatePeriodLicenseYearAddWidgetFromListPage() {
		logger
				.info("Go to Date Period License Year add widget from list page.");

		LicMgrDatePeriodLicenseYearsListPage.getInstance()
				.clickAddLicenseYear();
		ajax.waitLoading();
		LicMgrAddDatePeriodLicenseYearWidget.getInstance().waitLoading();
	}

	public void gotoDatePeriodLicenseYearListPageFromDetailsWidget() {
		logger
				.info("Go to Date Period License Year list page from details page.");

		LicMgrEditDatePeriodLicenseYearWidget.getInstance().clickCancel();
		ajax.waitLoading();
		LicMgrDatePeriodLicenseYearsListPage.getInstance().waitLoading();
	}

	/**
	 * update a Date period From: Date period list page End: Date period list
	 * page
	 */
	public void updateDatePeriodInfo(DatePeriodInfo info) {
		LicMgrDatePeriodsListPage listPg = LicMgrDatePeriodsListPage
				.getInstance();
		LicMgrDatePeriodsDetailPage detailPg = LicMgrDatePeriodsDetailPage
				.getInstance();

		logger.info("Update date period of -->" + info.getCode());
		this.gotoDatePeriodDetailPgFromListPg(info.getCode());
		detailPg.updateDatePeriod(info);
		listPg.waitLoading();
	}

	/**
	 * Add new Date periods, start from Date periods list page, end at add Date
	 * periods list page
	 */
	public String addDatePeriods(DatePeriodInfo info) {
		LicMgrDatePeriodsListPage listPg = LicMgrDatePeriodsListPage
				.getInstance();
		LicMgrAddDatePeriodsPage addPg = LicMgrAddDatePeriodsPage.getInstance();
		LicMgrAddDatePeriodCategoryWidget cateWidget = LicMgrAddDatePeriodCategoryWidget
				.getInstance();

		logger.info("Adding new Date Period.....Code==>" + info.getCode()
				+ ", Desc==>" + info.getDescription());
		if (!addPg.exists()) {
			this.gotoAddDatePeriodPgFromListPg();
		}

		if (!StringUtil.isEmpty(info.getCode())) {
			addPg.setCode(info.getCode());
		}

		if (!StringUtil.isEmpty(info.getDescription())) {
			addPg.setDesc(info.getDescription());
		}

		if (!StringUtil.isEmpty(info.getLicenseYears().get(0).getLicenseYear())) {
			addPg.selectLicenseYear(info.getLicenseYears().get(0)
					.getLicenseYear());
		}

		if ((info.getLicenseYears().get(0).getDates() != null)
				&& (info.getLicenseYears().get(0).getDates().size() > 0)) {

			List<String> lCate = addPg.getAllCategory();
			boolean skip = false;

			for (Dates span : info.getLicenseYears().get(0).getDates()) {
				if (span.getCategory() != null) {
					skip = false;
					for (String cate : lCate) {
						if (cate.equalsIgnoreCase(span.getCategory())) {
							skip = true;
							break;
						}
					}

					if (!skip)// need add new category
					{
						addPg.clickAddNewCategory();
						ajax.waitLoading();
						cateWidget.waitLoading();
						cateWidget.setCategoryName(span.getCategory());
						cateWidget.clickOK();
						ajax.waitLoading();
						addPg.waitLoading();

						lCate = addPg.getAllCategory();
					}
				}
			}

			// click 'add dates'
			for (int i = 0; i < info.getLicenseYears().get(0).getDates().size() - 1; i++) {
				addPg.clickAddDates();
				ajax.waitLoading();
				addPg.waitLoading();
			}
			// input date span values
			for (int j = 0; j < info.getLicenseYears().get(0).getDates().size(); j++) {
				addPg.setFromDate(info.getLicenseYears().get(0).getDates().get(
						j).getFromDate(), j);
				addPg.setEndDate(info.getLicenseYears().get(0).getDates()
						.get(j).getToDate(), j);
				addPg.selectCategory(info.getLicenseYears().get(0).getDates()
						.get(j).getCategory(), j);
			}

		}

		addPg.clickOK();
		ajax.waitLoading();

		Object pg = browser.waitExists(listPg, addPg);
		if (pg instanceof LicMgrAddDatePeriodsPage)// something error when add
													// new date period, return
													// error msg.
		{
			String msg = addPg.getErrorMsg();
			addPg.clickCancel();
			ajax.waitLoading();
			listPg.waitLoading();
			return msg;
		} else {
			return info.getCode();
		}
	}

	/** Add Quotas from Quota List page */
	public String addQuotas(QuotaInfo quota, boolean isOK) {
		LicMgrQuotaListPage listPg = LicMgrQuotaListPage.getInstance();
		LicMgrAddQuotaPage addPg = LicMgrAddQuotaPage.getInstance();
		logger.info("Add Quota and click " + (isOK ? "OK" : "Cancle"));
		if (!addPg.exists()) {
			this.gotoAddQuotaPgFromListPg();
		}
		addPg.setUpQuotaTypeInfo(quota);
		if (isOK) {
			addPg.clickOK();
		} else {
			addPg.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(listPg, addPg);
		String toReturn = "";
		if (addPg == page) {
			toReturn = addPg.getErrorMsg();
		} else if (isOK) {
			toReturn = listPg.getQuotaID(quota.getDescription());
		}
		return toReturn;
	}
	
	/**
	 * Add new Quota, start from Quota list page or add Quota page, end at Quota
	 * list page
	 */
	public String addQuotas(QuotaInfo quota) {
		return this.addQuotas(quota, true);
	}

	public String addPointType(Data<PointTypesAttr> pointType, boolean isOK) {
		LicMgrPointTypesListPage pointTypeListPg = LicMgrPointTypesListPage.getInstance();
		LicMgrPointTypeDetailsPage pointTypeDetailsPg = LicMgrPointTypeDetailsPage.getInstance();
		logger.info("Add point type and click " + (isOK ? "OK" : "Cancle"));
		
		if (!pointTypeDetailsPg.exists()) {
			this.gotoPointTypesListPgFromLotteriesProdListPg();
			this.gotoPointTypesDetailsPgFromPointTypeListPg(StringUtil.EMPTY);
		}
		pointTypeDetailsPg.setupPointType(pointType);
		if (isOK) {
			pointTypeDetailsPg.clickOK();
		} else {
			pointTypeDetailsPg.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(pointTypeListPg, pointTypeDetailsPg);
		String toReturn = "";
		if (pointTypeDetailsPg == page) {
			toReturn = pointTypeDetailsPg.getErrorMsg();
		} else if (isOK) {
			toReturn = pointTypeListPg.getPointTypeID(pointType.stringValue(PointTypesAttr.code));
		}
		return toReturn;
	}
	
	public String addPointType(Data<PointTypesAttr> pointType) {
		return addPointType(pointType, true);
	}
	
	/**
	 * Edit Quota Info from Quota List page
	 * @param quota
	 * @param isOK
	 * @author Lesley
	 * @date Jan 26, 2014
	 */
	public void editQuotaInfo(QuotaInfo quota, boolean isOK) {
		LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
		
		logger.info("Edit Quota info from quota list page for quota id=" + quota.getQuotaId());
		this.gotoQuotaDetailsPgFromQuotaListPg(quota.getQuotaId());
		quotaDetailsPg.setQuotaInfo(quota);
		this.saveOrCancelToEditQuotaDetails(isOK);
	}
	
	public void editQuotaInfo(QuotaInfo quota) {
		this.editQuotaInfo(quota, true);
	}
	
	/** Go to quota history page from quota list page */
	public void gotoQuotaHistoryPgFromQuotaListPg(String quotaDes) {
		LicMgrQuotaListPage quotaListPg = LicMgrQuotaListPage.getInstance();
		LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
		LicMgrQuotaChangeHistoryPage historyPg = LicMgrQuotaChangeHistoryPage.getInstance();
		
		logger.info("Go to quota history page from quota list page for quota=" + quotaDes);
		this.gotoQuotaDetailsPgFromQuotaListPg(quotaListPg.getQuotaID(quotaDes));
		quotaDetailsPg.clickChangeHistory();
		ajax.waitLoading();
		historyPg.waitLoading();
	}
	
	/***
	 * Add hunt sub location category from add hunt location page or hunt location details page 
	 * @param subLocs
	 */
	public void addHuntSubLocCategories(List<LocationInfo.SubLocation> subLocs) {
		LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget cateWidget = LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget
				.getInstance();
		LicMgrAddHuntLocationPage addPg = LicMgrAddHuntLocationPage.getInstance();
		LicMgrHuntLocationDetailPage huntLocDetailPg = LicMgrHuntLocationDetailPage.getInstance();
		
		logger.info("Add hunt sub location categories if not exist....");
		if (subLocs != null && subLocs.size() > 0) {
			boolean isAddPgExist = addPg.exists();
			boolean isDetailsPgExist = huntLocDetailPg.exists();
			boolean needClickRemove = false;
			if (isAddPgExist && !addPg.checkRemoveIsExisting()) {
				addPg.clickAddSubLocation();
				ajax.waitLoading();
				addPg.waitLoading();
				needClickRemove = true;
			} else if (isDetailsPgExist && !huntLocDetailPg.isRemoveExist()) {
				huntLocDetailPg.clickAddSubLocAndWait();
				needClickRemove = true;
			}
			
			List<String> slCate = new ArrayList<String> ();
			if (isAddPgExist) {
				slCate = addPg.getAllCategory();
			} else if (isDetailsPgExist) {
				slCate = huntLocDetailPg.getAllCategory();
			}
			
			for (SubLocation sl : subLocs) {
				if (!slCate.contains(sl.getCategory())) {
					logger.info("add category - " + sl.getCategory());
					if (isAddPgExist) {
						addPg.clickAddNewCategory(0);
					} else if (isDetailsPgExist) {
						huntLocDetailPg.clickAddNewCategory();
					}
					ajax.waitLoading();
					cateWidget.waitLoading();
					cateWidget.setCategoryName(sl.getCategory());
					cateWidget.clickOK();
					ajax.waitLoading();
					browser.waitExists(addPg, huntLocDetailPg);
					if (isAddPgExist) {
						slCate = addPg.getAllCategory();
					} else if (isDetailsPgExist) {
						slCate = huntLocDetailPg.getAllCategory();
					}
				}
			}
			
			if (needClickRemove && isAddPgExist) {
				addPg.clickRemove(0);
				ajax.waitLoading();
				addPg.waitLoading();
			} else if (needClickRemove && isDetailsPgExist) {
				huntLocDetailPg.clickRemoveAndWait();
			}
		}
	}
	
	/**
	 * Add new Hunt Location, start from Hunt Location list page or add hunt
	 * location page, end at add Hunt Location list page
	 */
	public void addHuntLocation(LocationInfo location) {
		LicMgrHuntLocationsListPage listPg = LicMgrHuntLocationsListPage
				.getInstance();
		LicMgrAddHuntLocationPage addPg = LicMgrAddHuntLocationPage
				.getInstance();

		if (!addPg.exists()) {
			this.gotoAddHuntLocationPgFromListPg();
		}

		this.addHuntSubLocCategories(location.getSubLocations());
		addPg.addHuntLocation(location);
		ajax.waitLoading();
		listPg.waitLoading();
	}

	/**Edit hunt location details, starts from any page, ends at hunt location list page */
	public void editHuntLocation(LocationInfo location) {
		LicMgrHuntLocationsListPage listPg = LicMgrHuntLocationsListPage
				.getInstance();
		LicMgrHuntLocationDetailPage huntLocDetailPg = LicMgrHuntLocationDetailPage.getInstance();
		
		logger.info("Edit hunt location for code=" + location.getCode());
		this.gotoHuntLocDetailsPg(location.getCode());
		this.addHuntSubLocCategories(location.getSubLocations());
		List<String> huntImgs = huntLocDetailPg.getAllLocImages();
		if (!huntImgs.contains(location.getImage()) && StringUtil.notEmpty(location.getLocationImgFilePath())) {
			this.addHuntLocImage(location.getLocationImgFilePath());
		}
		huntLocDetailPg.setUpHuntLocationInfo(location);
		huntLocDetailPg.clickOK();
		listPg.waitLoading();
	}
	
	/** Add hunt location image */
	public void addHuntLocImage(String locImgFilePath) {
		LicMgrHuntLocationDetailPage huntLocDetailPg = LicMgrHuntLocationDetailPage.getInstance();
		LicMgrHuntLocationImagePopupPage imagePopup = LicMgrHuntLocationImagePopupPage.getInstance();
		
		logger.info("add hunt location image: " + locImgFilePath);
		huntLocDetailPg.clickAddNewImage();
		imagePopup.waitLoading();
		imagePopup.setImgFilePath(locImgFilePath);
		imagePopup.clickOK();
		huntLocDetailPg.waitLoading();
	}
	
	/** Go to hunt location details page, starts from any page, ends at location details page */
	public void gotoHuntLocDetailsPg(String code) {
		LicMgrHuntLocationsListPage listPg = LicMgrHuntLocationsListPage
				.getInstance();
		LicMgrHuntLocationDetailPage huntLocDetailPg = LicMgrHuntLocationDetailPage.getInstance();
		
		if (!listPg.exists() && !huntLocDetailPg.exists()) {
			this.gotoLotteriesProductListPgFromTopMenu();
			this.gotoHuntLocationListPgFromLotteriesProdListPg();
		}
		if (listPg.exists()) {
			logger.info("Go to hunt location details page for code=" + code);
			listPg.clickHuntLocCode(code);
			huntLocDetailPg.waitLoading();
		}
	}
	
	/**
	 * Go to hunt detail page through by click hunt code, start from hunt list
	 * page, end at hunt detail page
	 * 
	 * @param huntCode
	 */
	public void gotoHuntDetailPgFromHuntsListPg(String huntCode) {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		LicMgrHuntComponentsSubPage huntDetailPg = LicMgrHuntComponentsSubPage
				.getInstance();

		logger.info("Go to hunt detail page, hunt code = " + huntCode);
		huntsListPg.clickHuntIdAccoringToHuntCode(huntCode);
		ajax.waitLoading();
		huntDetailPg.waitLoading();
	}

	/**
	 * Go to hunt permit list page from hunt detail page
	 */
	public void gotoHuntPermitListPgFromHuntDetailPg() {
		LicMgrHuntDetailPage huntDetailPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();

		logger.info("Go to hunt permit page from hunt detail page.");
		huntDetailPg.clickLicencesOrPermitsTab();
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}
	
	public void gotoHuntQuotaPageFromHuntDetailsPage() {
		LicMgrHuntDetailPage detailsPage = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntQuotaListPage quotaListPage = LicMgrHuntQuotaListPage.getInstance();
		
		logger.info("Goto Hunt-Quota sub page from hunt details page.");
		detailsPage.clickQuotaTab();
		ajax.waitLoading();
		quotaListPage.waitLoading();
	}
	
	public void gotoHuntQuotaListPageFromHuntListPg(String huntCode){
		this.gotoHuntDetailPgFromHuntsListPg(huntCode);
		this.gotoHuntQuotaPageFromHuntDetailsPage();
	}

	/**
	 * Go to hunt permit list page from hunt list page
	 * 
	 * @param huntCode
	 */
	public void gotoHuntPermitListPgFromHuntListPg(String huntCode) {
		this.gotoHuntDetailPgFromHuntsListPg(huntCode);
		this.gotoHuntPermitListPgFromHuntDetailPg();
	}

	/**
	 * Go to add hunt permit page from hunt permit list page
	 */
	public void gotoAddHuntPermitPgFromHuntPermitListPg() {
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();
		LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget
				.getInstance();

		logger.info("Go to add hunt permit page from permit list page.");
		huntPermitListPg.clickAddHuntPermitButton();
		ajax.waitLoading();
		addHuntPermitPg.waitLoading();
	}

	/**
	 * edit hunt permit
	 * 
	 * @param huntPermitInfo
	 * @param isClickOK
	 */
	void editHuntPermitInfo(HuntPermitInfo huntPermitInfo,
			boolean isClickOK) {
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget
				.getInstance();

		this.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermitInfo
				.getHuntPermitID());
		huntPermitDetailPg.setupHuntPermitInfo(huntPermitInfo);
		if (isClickOK) {
			huntPermitDetailPg.clickOK();
		} else {
			huntPermitDetailPg.clickCancel();
		}

		ajax.waitLoading();
		browser.waitExists(huntPermitListPg, huntPermitDetailPg);
		// if(huntPermitListPg == pages){
		// huntPermitListPg.getHuntPermitIDByHuntPermitInfo(huntPermitInfo);
		// }
		// huntPermitListPg.waitExists();
	}

	/**
	 * edit hunt permit info
	 * 
	 * @param huntPermitInfo
	 */
	public void editHuntPermitInfoWithClickOK(HuntPermitInfo huntPermitInfo) {
		this.editHuntPermitInfo(huntPermitInfo, true);
	}

	/**
	 * Add hunt permit info with click OK, or click Cancel
	 * 
	 * @param huntPermitInfos
	 * @param isClickOK
	 */
	void addHuntPermitInfo(List<HuntPermitInfo> huntPermitInfos,
			boolean isClickOK) {
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();
		LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget
				.getInstance();

		logger.info("Add hunt permit info.");
		this.gotoAddHuntPermitPgFromHuntPermitListPg();
		addHuntPermitPg.setupHuntPermitInfo(huntPermitInfos);
		if (isClickOK) {
			addHuntPermitPg.clickOK();
		} else {
			addHuntPermitPg.clickCancel();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(addHuntPermitPg,huntPermitListPg);
		if (huntPermitListPg == pages) {
			huntPermitListPg.getHuntPermitIDByHuntPermitInfo(huntPermitInfos);
		}

	}

	/**
	 * Add hunt permit info with click OK
	 * 
	 * @param huntPermitInfos
	 */
	public void addHuntPermitInfoWithClickOk(
			List<HuntPermitInfo> huntPermitInfos) {
		this.addHuntPermitInfo(huntPermitInfos, true);
	}

	/**
	 * Add hunt permit info with click cancel
	 * 
	 * @param huntPermitInfos
	 */
	public void addHuntPermitInfoWithClickCancel(
			List<HuntPermitInfo> huntPermitInfos) {
		this.addHuntPermitInfo(huntPermitInfos, false);
	}

	/**
	 * Go to hunt permit detail page from hunt permit list page
	 * 
	 * @param huntPermitID
	 */
	public void gotoHuntPermitDetailPgFromHuntPermitListPg(String huntPermitID) {
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget
				.getInstance();

		logger.info("Go to hunt permit detail page, hunt permit id = "
				+ huntPermitID);
		huntPermitListPg.clickHuntPermitID(huntPermitID);
		ajax.waitLoading();
		huntPermitDetailPg.waitLoading();
	}

	/**
	 * Filter hunt permit with select status
	 * 
	 * @param status
	 */
	public void filterHuntPermit(String status) {
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();

		logger.info("Filter Hunt Permit by status is " + status);
		huntPermitListPg.filterHuntPermitStatus(status);
		ajax.waitLoading();
		huntPermitListPg.waitLoading();
	}

	/**
	 * Edit hunt permit status, start from hunt permit list page, end at hunt
	 * permit list page
	 * 
	 * @param status
	 */
	void editHuntPermtStatus(String status, String huntPermitID) {
		LicMgrEditHuntPermitWidget huntPermitDetailPg = LicMgrEditHuntPermitWidget
				.getInstance();
		LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage
				.getInstance();

		logger.info("Edit Hunt Permit Status for " + status);
		this.gotoHuntPermitDetailPgFromHuntPermitListPg(huntPermitID);
		huntPermitDetailPg.selectStatus(status);
		huntPermitDetailPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(huntPermitDetailPg, huntPermitListPg);
	}

	/**
	 * Active hunt permit, start from hunt permit list page, end at hunt permit
	 * list page
	 */
	public void activateHuntPermit(String huntPermitID) {
		this.editHuntPermtStatus("Active", huntPermitID);
	}

	/**
	 * Inactive hunt permit, start from hunt permit list page, end at hunt
	 * permit list page
	 */
	public void deactivateHuntPermit(String huntPermitID) {
		this.editHuntPermtStatus("Inactive", huntPermitID);
	}

	/**
	 * Go to hunt license year list page from hunt detail page
	 */
	public void gotoHuntLicYearListPgFromHuntDetailPg() {
		LicMgrHuntDetailPage huntDetailPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage
				.getInstance();

		logger.info("Go to hunt license year list page from hunt detail page.");
		huntDetailPg.clickLicenceYearTab();
		ajax.waitLoading();
		huntLicYearListPg.waitLoading();
	}
	
	/**
	 * Go to hunt license year list page from hunts list page
	 */
	public void gotoHuntLicYearListFromHuntsListPg(String huntCode) {
		this.gotoHuntDetailPgFromHuntsListPg(huntCode);
		this.gotoHuntLicYearListPgFromHuntDetailPg();
	}
	
	/**
	 * Add hunt license year and click OK or Cancle button, from Hunt licnese year list page, to hunt license year list page or add hunt license year widget
	 * @param ly
	 * @param clickOK
	 */
	public void addHuntLicenseYear(LicenseYear ly, boolean clickOK) {
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		LicMgrAddHuntLicYearWidget addHuntLicYearPg = LicMgrAddHuntLicYearWidget.getInstance();
		
		logger.info("Add hunt license year from hunt license year list page, and click " + (clickOK?"OK":"Cancle") + "...");
		huntLicYearListPg.clickAddLicenseYear();
		addHuntLicYearPg.waitLoading();
		addHuntLicYearPg.setHuntLicYearInfo(ly);
		if (clickOK) {
			addHuntLicYearPg.clickOKAndWait();
			browser.waitExists(addHuntLicYearPg, huntLicYearListPg);
		} else {
			addHuntLicYearPg.clickCancel();
			ajax.waitLoading();
			huntLicYearListPg.waitLoading();
		}
	}
	
	public void editHuntLicenseYear(LicenseYear ly, boolean clickOK){
		LicMgrEditHuntLicYearWidget huntLicYearPg = LicMgrEditHuntLicYearWidget.getInstance();
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		logger.info("Update hunt license year with id:" + ly.id + " and click " + (clickOK?"OK":"Cancle") + "...");
		this.gotoHuntLicenseYearWigetFromListPg(ly.id);
		huntLicYearPg.setHuntLicYearInfo(ly);
		if (clickOK) {
			huntLicYearPg.clickOKAndWait();
			browser.waitExists(huntLicYearPg, huntLicYearListPg);
		} else {
			huntLicYearPg.clickCancel();
			ajax.waitLoading();
			huntLicYearListPg.waitLoading();
		}
	}
	
	public void addHuntLicenseYear(LicenseYear ly) {
		this.addHuntLicenseYear(ly, true);
	}
	
	public void desctiveHuntLicenseYears(List<String> ids){
		for(String id:ids){
			this.deactiveHuntLicenseYear(id);
		}
	}
	
	public void deactiveHuntLicenseYear(String id){
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		LicMgrEditHuntLicYearWidget huntLicYearPg = LicMgrEditHuntLicYearWidget.getInstance();
		huntLicYearListPg.clickLicenseYearId(id);
		ajax.waitLoading();
		huntLicYearPg.waitLoading();
		logger.info("Change status of license year:" + id + " to inactive.");
		huntLicYearPg.selectStatus(INACTIVE_STATUS);
		huntLicYearPg.clickOK();
		ajax.waitLoading();
		huntLicYearListPg.waitLoading();
	}
	
	public void gotoHuntLicenseYearWigetFromListPg(String id){
		LicMgrEditHuntLicYearWidget huntLicYearPg = LicMgrEditHuntLicYearWidget.getInstance();
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		logger.info("Go to hunt license year detail widget, the license year id is:" + id);
		huntLicYearListPg.clickLicenseYearId(id);
		ajax.waitLoading();
		huntLicYearPg.waitLoading();
	}
	
	public void gotoHuntLicenseYearListPageFromEditWidget(){
		LicMgrEditHuntLicYearWidget huntLicYearPg = LicMgrEditHuntLicYearWidget.getInstance();
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		logger.info("Go to hunt license year list page");
		huntLicYearPg.clickCancel();
		ajax.waitLoading();
		huntLicYearListPg.waitLoading();
	}
	
	public void gotoHuntLicenseYearListPageFromAddWidget(){
		LicMgrAddHuntLicYearWidget huntLicYearPg = LicMgrAddHuntLicYearWidget.getInstance();
		LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
		logger.info("Go to hunt license year list page");
		huntLicYearPg.clickCancel();
		ajax.waitLoading();
		huntLicYearListPg.waitLoading();
	}
	
	/**
	 * Go to product configuration page from top menu
	 * 
	 * @param tabName
	 *            target tab you want to arrive
	 */
	public void gotoProdConfPgFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		String adminValue = topMenuPg.getAdminValue();

		logger.info("Go to Product Configuration Page From Top Menu");

		if (!adminValue.equals("Configuration")) {
			topMenuPg.selectAdminOptions("Configuration");
		} else {
			topMenuPg.clickAdmin();
		}

		prodConfPg.waitLoading();

	}
	
	public void gotoLandownerConfigFromTopMenu(){
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
		
		logger.info("Goto Landowner County Quantity Page From Top Menu.");
		String adminValue = topMenuPg.getAdminValue();
		
		if (!adminValue.equals("Landowner")) {
			topMenuPg.selectAdminOptions("Landowner");
		} else {
			topMenuPg.clickAdmin();
		}
		listPg.waitLoading();		
	}
	
	public void addCountyLicenseYearQuantity(Data<LandownerCountyQuantityAttr> attr){
		LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
		LicMgrAddCountyLicenseYearQtyWidget addQtyPg = LicMgrAddCountyLicenseYearQtyWidget.getInstance();
		
		logger.info("Start to Add Landowner County Quantity.");
		
		listPg.clickAddCountyLicenseYearQty();
		ajax.waitLoading();
		addQtyPg.waitLoading();
		addQtyPg.setCountyLicenseYearQty(attr);
		addQtyPg.clickOKAndWait();
		listPg.waitLoading();		
	}
	
	public void editCountyQuantity(String type,String licenseYear,HashMap<String, String> map){
		LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
		LicMgrEditLandownerCountyQtyPage editPg = LicMgrEditLandownerCountyQtyPage.getInstance();
		
		listPg.searchCountyQty(type, licenseYear);
		listPg.clickEditQuantity();
		ajax.waitLoading();
		editPg.waitLoading();
		editPg.editCountQty(map);
		listPg.waitLoading();		
	}
	
	public void editCountyQuantity(String type,String licenseYear,String county,String qty){
		HashMap<String, String> map = new HashMap<>();
		map.put(county, qty);
		this.editCountyQuantity(type, licenseYear, map);
	}
	
	public void gotoChangeHistoryPg(){
		LicMgrLandownerViewCountyQtyListPage listPg = LicMgrLandownerViewCountyQtyListPage.getInstance();
		LicMgrLandownerCountyQtyChangeHistoryPage historyPg = LicMgrLandownerCountyQtyChangeHistoryPage.getInstance();
		
		logger.info("Goto Landowner Couty Quantity Change History.");
		
		listPg.clickChangeHistory();
		ajax.waitLoading();
		historyPg.waitLoading();
	}

	/**
	 * Go to report categories configuration sub page from product configuration
	 * main page
	 */
	public void gotoReportCategoriesPageFromProductConfigPage() {
		LicMgrProductConfigurationPage productConfigPage = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrReportCategoriesConfigurationPage reportCategoriesPage = LicMgrReportCategoriesConfigurationPage
				.getInstance();

		logger
				.info("Go to Report Categories configuration sub page from Product configuration page.");
		productConfigPage.switchToReportCategoryTab();
		reportCategoriesPage.waitLoading();
	}

	/**
	 * This method is used to add season, start from product configuration page,
	 * end at product configration page
	 * 
	 * @param harvDesig
	 * @param printOrder
	 * @param description
	 * @param isUseDefaultHarvestDesig
	 * @param isClickOk
	 * @return harvest designation
	 */
	public String addSeasons(String harvDesig, String printOrder,
			String description, boolean isUseDefaultHarvestDesig,
			boolean isClickOk) {
		LicMgrAddSeasonWidget addSeasonWidget = LicMgrAddSeasonWidget
				.getInstance();
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		String harvestDesignation = "";

		logger.info("Add Seasons.");
		if (!addSeasonWidget.exists()) {
			prodConfPg.clickAdd();
			ajax.waitLoading();
			addSeasonWidget.waitLoading();
		}

		if (isUseDefaultHarvestDesig) {
			harvestDesignation = addSeasonWidget.getHarvestDesignation();
			addSeasonWidget.setSeasonInfo(printOrder, description);
		} else {
			harvestDesignation = harvDesig;
			addSeasonWidget.setSeasonInfo(harvDesig, printOrder, description);
		}

		if (isClickOk) {
			addSeasonWidget.clickOK();
		} else {
			addSeasonWidget.clickCancel();
		}
		ajax.waitLoading();
		browser.waitExists(addSeasonWidget, prodConfPg);

		return harvestDesignation;
	}

	/**
	 * This method is used to add season, and use default harvest designation
	 * 
	 * @param printOrder
	 * @param description
	 * @param isClickOk
	 * @return default harvest designation
	 */
	public String addSeasons(String printOrder, String description,
			boolean isClickOk) {
		String harvestDesignation = this.addSeasons(null, printOrder,
				description, true, isClickOk);
		return harvestDesignation;
	}

	/**
	 * This case is used to add species, start from product configuration page,
	 * end at product configuration page or end at add species page
	 * 
	 * @param harvestDesig
	 * @param description
	 * @param isUseDefaultHarvestDesig
	 * @param isClickOK
	 * @return harvest designation
	 */
	/*
	 * Content for species has changed, this method is suitable for QA3,please
	 * notice that a new addSpecies for QA4 in licenseManagerhas already been
	 * created, please update based on that one if run cases in QA4
	 */
	public String addSpecies(String harvestDesig, String description,
			boolean isUseDefaultHarvestDesig, boolean isClickOK) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddSpeciesWidget addSpeciesWidget = LicMgrAddSpeciesWidget
				.getInstance();
		String harvestDesignation = "";

		logger.info("Add species.");
		if (!addSpeciesWidget.exists()) {
			prodConfPg.clickAdd();
			ajax.waitLoading();
			addSpeciesWidget.waitLoading();
		}

		if (isUseDefaultHarvestDesig) {
			harvestDesignation = addSpeciesWidget.getHarvestDesignation();
			addSpeciesWidget.setSpeciesInfo(description);
		} else {
			harvestDesignation = harvestDesig;
			addSpeciesWidget.setSpeciesInfo(harvestDesig, description);
		}

		if (isClickOK) {
			addSpeciesWidget.clickOK();
		} else {
			addSpeciesWidget.clickCancel();
		}
		ajax.waitLoading();
		browser.waitExists(addSpeciesWidget, prodConfPg);
		return harvestDesignation;
	}

	/**
	 * Add species, used default harvest designation
	 * 
	 * @param description
	 * @param isClickOK
	 * @return harvest designation
	 */
	public String addSpecies(String description, boolean isClickOK) {
		String harvestDesignation = this.addSpecies(null, description, true,
				isClickOK);
		return harvestDesignation;
	}

	public String addSpecies(Species species, boolean isUseDefaultHarvestDesig) {
		return this.addSpecies(species, isUseDefaultHarvestDesig, true);
	}

	public String addSpecies(Species species, boolean isUseDefaultHarvestDesig,
			boolean isClickOK) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddSpeciesWidget addSpeciesWidget = LicMgrAddSpeciesWidget
				.getInstance();
		String harvestDesignation = "";

		logger.info("Add species.");
		if (!addSpeciesWidget.exists()) {
			prodConfPg.clickAdd();
			ajax.waitLoading();
			addSpeciesWidget.waitLoading();
		}

		if (isUseDefaultHarvestDesig) {
			harvestDesignation = addSpeciesWidget.getHarvestDesignation();
		} else {
			harvestDesignation = species.speciesId;
		}
		addSpeciesWidget.setSpeciesInfo(species, isUseDefaultHarvestDesig);
		if (isClickOK) {
			addSpeciesWidget.clickOK();
		} else {
			addSpeciesWidget.clickCancel();
		}
		ajax.waitLoading();
		browser.waitExists(addSpeciesWidget, prodConfPg);
		return harvestDesignation;
	}
	
	public String editSpeciesSubType(Species species) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrEditSpeciesWidget editSpeciesWidget = LicMgrEditSpeciesWidget
				.getInstance();
		String harvestDesignation = "";

		logger.info("Edit species sub type.");
		if (!editSpeciesWidget.exists()) {
			harvestDesignation = species.speciesId = prodConfPg.clickHarvestDesignation(species.code, species.description);
			ajax.waitLoading();
			editSpeciesWidget.waitLoading();
		}

		editSpeciesWidget.setSpeciesSubTypeInfo(species);
		editSpeciesWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(editSpeciesWidget, prodConfPg);
		return harvestDesignation;
	}
	
	/**
	 * This method is used to add document template
	 * 
	 * @param docTemplateInfo
	 * @param isClickOk
	 */
	public void addDocumentTemplate(DocumentTemplateInfo docTemplateInfo,
			boolean isClickOk) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddDocTemplateWidget addDocTemplateWidget = LicMgrAddDocTemplateWidget
				.getInstance();

		logger.info("Add Document Template.");
		if (!addDocTemplateWidget.exists()) {
			prodConfPg.clickAdd();
			ajax.waitLoading();
			addDocTemplateWidget.waitLoading();
		}
		addDocTemplateWidget.setDocTemplateInfo(docTemplateInfo);
		if (isClickOk) {
			addDocTemplateWidget.clickOK();
		} else {
			addDocTemplateWidget.clickCancel();
		}
		ajax.waitLoading();
		browser.waitExists(prodConfPg, addDocTemplateWidget);
	}

	/**
	 * activate or Inactivate all document templates
	 * 
	 * @param activate
	 * @param schema
	 */
	public void activateOrInactivateAllDocumentTemplates(boolean activate,
			String schema) {
		String sql = "update  P_DOCUMENT_TEMPLATE  set Active_IND="
				+ (activate ? "1" : "0");
		logger.info(sql);
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	/**
	 * Go to product configuration page from top menu
	 * 
	 * @param tabName
	 *            target tab you want to arrive
	 */
	public void gotoProdConfPgFromTopMenu(String tabName) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrSeasonsConfigurationPage seasonpage = LicMgrSeasonsConfigurationPage
				.getInstance();
		LicMgrWeaponsConfigurationPage weaponPg = LicMgrWeaponsConfigurationPage
				.getInstance();

		logger.info("Go to Product Configuration " + tabName
				+ " Page From Top Menu");

		this.gotoProdConfPgFromTopMenu();

		if (tabName.equalsIgnoreCase("Document Templates")) {
			LicMgrDocumentTemplatesConfigurationPage docTempListPage = LicMgrDocumentTemplatesConfigurationPage
					.getInstance();
			prodConfPg.clickDocTemplatesTab();
			ajax.waitLoading();
			docTempListPage.waitLoading();
		} else if (tabName.equalsIgnoreCase("Display Category")) {
			prodConfPg.clickDisplayCategoriesTab();
			ajax.waitLoading();
			prodConfPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Display Sub-Category")) {
			prodConfPg.clickDisplaySubCategoriesTab();
			ajax.waitLoading();
			prodConfPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Seasons")) {
			prodConfPg.clickSeasonsTab();
			ajax.waitLoading();
			seasonpage.waitLoading();
		} else if (tabName.equalsIgnoreCase("Weapons")) {
			prodConfPg.clickWeaponsTab();
			ajax.waitLoading();
			weaponPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unknown Tab Name....");
		}
	}

	/**
	 * Add new weapon on weapon list page.
	 * 
	 * @param info
	 *            WeaponInfo
	 */
	public String addNewWeapon(WeaponInfo info) {
		LicMgrAddWeaponWidget addPg = LicMgrAddWeaponWidget.getInstance();
		LicMgrWeaponsConfigurationPage listPg = LicMgrWeaponsConfigurationPage
				.getInstance();
		String errorMsg = "";

		logger.info("Adding new Weapon(" + info.getCode()
				+ ") on LicMgrWeaponsConfigurationPage...");
		listPg.clickAdd();
		ajax.waitLoading();
		addPg.waitLoading();
		addPg.setWeaponInfo(info);
		addPg.clickOK();
		ajax.waitLoading();

		Object obj = browser.waitExists(addPg, listPg);
		if (obj instanceof LicMgrAddWeaponWidget) {
			errorMsg = addPg.getErrorMsg().trim();
			addPg.clickCancel();
			ajax.waitLoading();
			listPg.waitLoading();
			logger.info("Failed to add new Weapon, error-->" + errorMsg);
			return errorMsg;
		} else {
			logger.info("Add new Weapon success...");
			return errorMsg;
		}
	}

	/**
	 * Go to season page from product configuration page
	 */
	public void gotoSeasonProdConfPgFromTopMenu() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();

		this.gotoProdConfPgFromTopMenu();
		logger.info("Go to Season Page From Product Configuration Page");

		prodConfPg.clickSeasonsTab();
		ajax.waitLoading();
		prodConfPg.waitLoading();
	}

	/**
	 * Go to product question page from product configuration page
	 */
	public void gotoQuestionConfigPgFromTopMenu() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrProductQuestionsConfigurationPage questionConfPg = LicMgrProductQuestionsConfigurationPage
				.getInstance();

		this.gotoProdConfPgFromTopMenu();
		logger.info("Go to Question Page From Product Configuration Page");

		prodConfPg.clickProdQuestionsTab();
		ajax.waitLoading();
		questionConfPg.waitLoading();
	}

	/**
	 * Go to report category page from product configuration page
	 */
	public void gotoReportCategoryConfigPgFromTopMenu() {
		this.gotoProdConfPgFromTopMenu();
		this.gotoReportCategoriesPageFromProductConfigPage();
	}

	/**
	 * Go to display category page from product configuration page
	 */
	public void gotoDisplayCategoryConfigPgFromTopMenu() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();

		this.gotoProdConfPgFromTopMenu();
		logger
				.info("Go to Display Category Page From Product Configuration Page");

		prodConfPg.clickDisplayCategoriesTab();
		ajax.waitLoading();
		displayCategoryConfigPg.waitLoading();
	}

	/**
	 * Go to display sub category page from product configuration page
	 */
	public void gotoDisplaySubCategoryConfigPgFromTopMenu() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();

		this.gotoProdConfPgFromTopMenu();
		logger
				.info("Go to Display Sub-Category Page From Product Configuration Page");

		prodConfPg.clickDisplaySubCategoriesTab();
		ajax.waitLoading();
		displaySubCategoryPg.waitLoading();
	}

	/**
	 * This method used to go to edit privilege report page by category id
	 * 
	 * @param id
	 */
	public void gotoEditPrivilegeReportCategory(String id) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget
				.getInstance();

		logger
				.info("Go to edit privilege report page from product configration page.");
		prodConfPg.clickLink(id);
		ajax.waitLoading();
		widget.waitLoading();
	}

	/**
	 * This method used to add privilege display sub category
	 * 
	 * @param category
	 * @param isCancel
	 */
	public String addPrivilegeDisplaySubCategory(
			PrivilegeDisplayCategory category, boolean isCancel) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget
				.getInstance();

		logger.info("add privilege display sub category ...");

		if (!widget.exists()) {
			displaySubCategoryConfigPg.clickAdd();
			ajax.waitLoading();
			widget.waitLoading();
		}
		widget.setDisplaySubCategoryInfo(category.description,
				category.displayOrder);

		if (isCancel) {
			widget.clickCancel();
		} else {
			widget.clickOK();
		}

		ajax.waitLoading();
		Object page = browser.waitExists(widget, displaySubCategoryConfigPg);
		String value = "";
		if (page == displaySubCategoryConfigPg) {
			value = displaySubCategoryConfigPg
					.getDisplaySubCategoryIdByDescription(category.description);
		} else if (page == widget) {
			value = widget.getErrorMsg();
		}
		return value;
	}

	public void editPrivilegeReportCategory(PrivilegeReportCategory report) {
		this.editPrivilegeReportCategory(report, false);
	}

	/**
	 * This method used to edit privilege report category
	 * 
	 * @param report
	 * @param isCancel
	 */
	public void editPrivilegeReportCategory(PrivilegeReportCategory report,
			boolean isCancel) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget
				.getInstance();

		logger.info("edit privilege report category ...");

		widget.setReportCategoryInfo(report.description, report.displayOrder,
				report.groupNum);
		if (isCancel) {
			widget.clickCancel();
		} else {
			widget.clickOK();
		}
		ajax.waitLoading();
		browser.waitExists(prodConfPg, widget);
	}

	/**
	 * Go to Configuration-Product Configuration-Product Questions-Question
	 * Assignments page from top menu
	 * 
	 * @param questDisplayText
	 */
	public void gotoProductCongurationQuestionsDetailsPg(String questDisplayText) {
		LicMgrProductConfigurationPage productConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrProductConfigurationQuestionsDetailPage questionDetailPg = LicMgrProductConfigurationQuestionsDetailPage
				.getInstance();

		this.gotoQuestionConfigPgFromTopMenu();
		logger
				.info("Go to Question Assignments detail page from Product Questions List page.");
		productConfPg.clickLink(questDisplayText);
		ajax.waitLoading();
		questionDetailPg.waitLoading();
	}

	/**
	 * Go to document template page from product configuration page
	 */
	public void gotoDocumentTemplateConfigPgFromTopMenu() {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrDocumentTemplatesConfigurationPage docPage = LicMgrDocumentTemplatesConfigurationPage
				.getInstance();

		this.gotoProdConfPgFromTopMenu();
		logger.info("go to Document templates from configuration page");
		prodConfPg.clickDocTemplatesTab();
		ajax.waitLoading();
		docPage.waitLoading();
	}

	/**
	 * Go to document template detail page, through click document template name
	 * link on document list page
	 * 
	 * @param docTemplateName
	 */
	public void gotoProductConfigurationDocumentTemplateDetailsPg(
			String docTemplateName) {
		LicMgrDocumentTemplatesConfigurationPage docPage = LicMgrDocumentTemplatesConfigurationPage
				.getInstance();
		LicMgrProductConfigurationDocumentTemplateDetailPage docTemplateDetailPg = LicMgrProductConfigurationDocumentTemplateDetailPage
				.getInstance();

		this.gotoDocumentTemplateConfigPgFromTopMenu();
		logger
				.info("go to Document templates assignment page from document template list page");
		docPage.clickDocumentTemplateName(docTemplateName);
		ajax.waitLoading();
		docTemplateDetailPg.waitLoading();
	}

	/**
	 * The method used to add a display category
	 * 
	 * @param description
	 * @param displayOrd
	 * @param hiddenInSale
	 * @return
	 */
	public String addDisplayCategory(PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
				.getInstance();

		logger.info("Add Privilege Display Category.");
		String toReturn = "";
		displayCategoryConfigPg.clickAdd();
		ajax.waitLoading();
		widget.waitLoading();
		widget
				.setDisplayCategoryInfo(displayCategory.description,
						displayCategory.displayOrder,
						displayCategory.hiddenInSalesFlow);
		widget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(widget, displayCategoryConfigPg);
		if (widget == page) {
			toReturn = widget.getErrorMsg();
		}
		if (displayCategoryConfigPg == page) {
			toReturn = displayCategoryConfigPg
					.getDisplayCategoryId(displayCategory.description);
		}

		return toReturn;
	}

	/**
	 * This method is used to edit an existing display category. Work flow
	 * starts with Display Category configuration list page and ends with it too
	 * 
	 * @param displayCategory
	 */
	public void editDisplayCategory(PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
				.getInstance();

		logger.info("Edit Privilege Display Category-" + displayCategory.id);
		this.gotoDisplayCategoryDetailPg(displayCategory.id);
		widget
				.setDisplayCategoryInfo(displayCategory.description,
						displayCategory.displayOrder,
						displayCategory.hiddenInSalesFlow);
		widget.clickOK();
		ajax.waitLoading();
		displayCategoryConfigPg.waitLoading();
	}

	/**
	 * This method is used to edit an existing display sub-category record. Work
	 * flow starts with Display Sub-Category configuration list page and ends
	 * with it too
	 * 
	 * @param displaySubCategory
	 */
	public void editDisplaySubCategory(
			PrivilegeDisplayCategory displaySubCategory) {
		LicMgrDisplaySubCategoriesConfigurationPage subCategoryPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget
				.getInstance();

		logger.info("Edit Privilege Display Sub-Category-"
				+ displaySubCategory.id);
		subCategoryPg.clickLink(displaySubCategory.id);
		ajax.waitLoading();
		widget.waitLoading();
		widget.setDisplaySubCategoryInfo(displaySubCategory.description,
				displaySubCategory.displayOrder);
		widget.clickOK();
		ajax.waitLoading();
		subCategoryPg.waitLoading();
	}

	/**
	 * The method used goto privilege detail page from category list page
	 * 
	 * @param categoryId
	 */
	public void gotoDisplayCategoryDetailPg(String categoryId) {
		LicMgrProductConfigurationPage configPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
				.getInstance();

		logger.info("Goto Display Category Detail Page.");

		configPg.clickLink(categoryId);
		ajax.waitLoading();
		widget.waitLoading();
	}

	/**
	 * This method is used to verify the display category list info in Display
	 * Category configuration page and detail info in detail widget
	 * 
	 * @param displayCategory
	 */
	public void verifyDisplayCategoryInfo(
			PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategortPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();

		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
				.getInstance();

		displayCategortPg.verifyDisplayCategoryInfo(displayCategory);

		this.gotoDisplayCategoryDetailPg(displayCategory.id);
		widget.verifyDisplayCategoryDetailsInfo(displayCategory);
		widget.clickOK();
		ajax.waitLoading();
		displayCategortPg.waitLoading();
	}

	/**
	 * Verify whether display sub-category list and detail info are correct or
	 * not
	 * 
	 * @param displayCategory
	 */
	public void verifyDisplaySubCategoryInfo(
			PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget
				.getInstance();

		displaySubCategoryPg.verifyDisplaySubCategoryInfo(displayCategory);
		displaySubCategoryPg.clickLink(displayCategory.id);
		ajax.waitLoading();
		widget.waitLoading();
		boolean result = widget
				.compareDisplaySubCategoryDetailInfo(displayCategory);
		if (!result) {
			throw new ErrorOnPageException(
					"Display Sub-Category detail info is incorrect.");
		}
		widget.clickOK();
		ajax.waitLoading();
		displaySubCategoryPg.waitLoading();
	}

	/**
	 * Verify whether report category list and detail info are correct or not
	 * 
	 * @param reportCategory
	 */
	public void verifyReportCategoryInfo(PrivilegeReportCategory reportCategory) {
		LicMgrReportCategoriesConfigurationPage reportCategoryPg = LicMgrReportCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget
				.getInstance();

		reportCategoryPg.verifyReportCategoryInfo(reportCategory);

		reportCategoryPg.clickLink(reportCategory.id);
		ajax.waitLoading();
		widget.waitLoading();
		boolean result = widget.compareReportDetailInfo(reportCategory);
		if (!result) {
			throw new ErrorOnPageException(
					"Report Category detail info is incorrect.");
		}
		widget.clickOK();
		ajax.waitLoading();
		reportCategoryPg.waitLoading();
	}

	/**
	 * Add display sub-category record
	 * 
	 * @param description
	 * @param disOrd
	 * @return
	 */
	public String addDisplaySubCategory(String description, String disOrd) {
		LicMgrDisplaySubCategoriesConfigurationPage configPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget
				.getInstance();

		logger.info("Add privilege display sub category.");
		String toReturn = "";
		configPg.clickAdd();
		ajax.waitLoading();
		widget.waitLoading();
		widget.setDisplaySubCategoryInfo(description, disOrd);
		widget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(widget, configPg);
		if (widget == page) {
			toReturn = widget.getErrorMsg();
		}
		if (configPg == page) {
			toReturn = configPg
					.getDisplaySubCategoryIdByDescription(description);
		}

		return toReturn;
	}

	/**
	 * Get display categories and their corresponding display orders
	 * 
	 * @return map-key: display category description -value: display order
	 */
	public Map<String, Integer> getDisplayCategoriesAndOrders() {
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();

		if (!displayCategoryConfigPg.exists()) {
			this.gotoProdConfPgFromTopMenu("Display Category");
		} else {
			displayCategoryConfigPg.clickDisplayCategoriesTab();
			ajax.waitLoading();
			displayCategoryConfigPg.waitLoading();
		}
		logger
				.info("Get Display Categories and their corresponding display orders.");
		Map<String, Integer> displayCategoriesAndOrders = displayCategoryConfigPg
				.getDisplayCategoriesAndOrders();

		return displayCategoriesAndOrders;
	}

	/**
	 * Get display sub categories and their corresponding display orders
	 * 
	 * @return map-key: display sub category description -value: display order
	 */
	public Map<String, Integer> getDisplaySubCategoriesAndOrders() {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();

		if (!displaySubCategoryConfigPg.exists()) {
			this.gotoProdConfPgFromTopMenu("Display Sub-Category");
		} else {
			displaySubCategoryConfigPg.clickDisplaySubCategoriesTab();
			ajax.waitLoading();
			displaySubCategoryConfigPg.waitLoading();
		}
		logger
				.info("Get Display Sub-Categories and their corresponding display orders.");
		Map<String, Integer> displaySubCategoriesAndOrders = displaySubCategoryConfigPg
				.getDisplaySubCategoriesAndOrders();

		return displaySubCategoriesAndOrders;
	}

	/**
	 * Verify the privilege order doesn't exists in the Undo Void page shows it
	 * cannot be undo voided
	 */
	public void verifyPrivOrderCannotBeUndoVoided() {
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();

		logger.info("Verify privilege order can not be undo voided.");

		boolean isOrderExists = voidUndoVoidPg.selectPrivilegeOrder();
		if (isOrderExists) {
			throw new ActionFailedException(
					"This privilege order should not be undo voided. But it exists in the Undo Void list.");
		} else {
			logger
					.info("----This privilege order really can not be undo voided.");
		}
	}

	/**
	 * Transfer privilege to a new customer,start from privilege item detail
	 * page,end at order cart page
	 * 
	 * @param cust
	 */
	public Object transferPrivToOrderCart(Customer cust, PrivilegeInfo privilege) {
		LicMgrPrivilegeItemDetailPage itemPg = LicMgrPrivilegeItemDetailPage
				.getInstance();
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage
				.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
				.getInstance();
		LicMgrConfirmEducationWidget confirmEduWidget = LicMgrConfirmEducationWidget
				.getInstance();
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget
				.getInstance();

		logger.info("Transfer privilege To Customer.");

		itemPg.clickTransfer();
		ajax.waitLoading();
		identifyCustPg.waitLoading();
		this.identifyAndConfirmCustomer(cust);
		Object page = browser.waitExists(residencyWidget, privQuestWidget,
				confirmEduWidget, confirmPg);
		if (page == confirmPg) {
			confirmPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(residencyWidget, privQuestWidget,
					confirmEduWidget, confirmPg);
		}
		if (page == confirmPg) {
			confirmPg.clickOK();
			ajax.waitLoading();
		}

		page = browser.waitExists(residencyWidget, privQuestWidget,
				confirmEduWidget, cartPg, confirmPg);
		if (page == residencyWidget) {
			 if (cust.residencyStatus.equalsIgnoreCase("Resident")) {
					residencyWidget.selectResident();
					ajax.waitLoading();
					if (residencyWidget.isAdditionalProofDropdownListExists()) {
						residencyWidget
								.selectAdditionalProof(cust.additionalProofOfResidency);
					}
				} else if(StringUtil.isEmpty(cust.residencyStatus) || cust.residencyStatus.equalsIgnoreCase("Non Resident")){
					residencyWidget.selectNonResident();
					ajax.waitLoading();
				} else{
					residencyWidget.selectResident(cust.residencyStatus);
					ajax.waitLoading();
				}
			 
			residencyWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(residencyWidget, privQuestWidget,
					confirmEduWidget, cartPg, confirmPg);
		}
		if (page == privQuestWidget) {
			if (null != privilege.privilegeQuestions) {
				for (QuestionInfo questionInfo : privilege.privilegeQuestions) {
					privQuestWidget
							.answerPrivilegeQuestion(
									questionInfo.questDisplayText,
									questionInfo.questionType,
									questionInfo.questAnswer);
					privQuestWidget.clickDone();
				}
				cartPg.waitLoading();
			} else {
				throw new ActionFailedException(
						"This privilege need to initial question");
			}
		}

		if (page == confirmEduWidget) {
			confirmEduWidget.setUpEduConfirmInfo(cust.education.educationNum,
					cust.education.state, cust.education.country);
			confirmEduWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(cartPg, confirmPg);
		}

		return page;
	}

	public String replacePrivInventoryFromPriOrderDetailsPgToOrderCard(String licenceNum, String inventoryNum, String replaceInventoryNum, 
			String replaceReason, String replaceNote){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrPrivilegeOrderItemsPage privOrderItemsPg = LicMgrPrivilegeOrderItemsPage.getInstance();
		LicMgrPrivilegeInventoryListPage privInventoryListPg = LicMgrPrivilegeInventoryListPage.getInstance();
		LicMgrPrivilegeInventoryWidget priviInventoryWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		LicMgrPrivilegeInventoryReplaceReasonWidget replaceReaonWidget = LicMgrPrivilegeInventoryReplaceReasonWidget.getInstance();
		
		//Click License number in order item page
		if(StringUtil.isEmpty(licenceNum)){
			privOrderItemsPg.clickLilcenceNum();
		}else privOrderItemsPg.clickLicenseNum(licenceNum);
		privInventoryListPg.waitLoading();
		
		//Replace inventory num
		privInventoryListPg.selectInvNumToReplace(inventoryNum);
		priviInventoryWidget.waitLoading();
		//Jane[20140428]:For relaceInventoryNum was empty, 
		//we would choose the default selected one and return the number at the end of this workflow
		String selectedReplacedInvNum = "";
		if(StringUtil.isEmpty(replaceInventoryNum)) {
			selectedReplacedInvNum = priviInventoryWidget.getSelectedInventoryNum();
			priviInventoryWidget.clickOK();
			ajax.waitLoading();
		} else {
			selectedReplacedInvNum = replaceInventoryNum;
			priviInventoryWidget.selectReplatedInvNum(replaceInventoryNum);
		}
		replaceReaonWidget.waitLoading();
		replaceReaonWidget.setReplaceReason(replaceReason, replaceNote);
		
		//Go to cart page
		cartPg.waitLoading();
		return selectedReplacedInvNum;
	}
	
	public String  replacePrivInventoryFromPriOrderDetailsPgToOrderCard(String inventoryNum, String replaceInventoryNum, 
			String replaceReason, String replaceNote){
		return replacePrivInventoryFromPriOrderDetailsPgToOrderCard(StringUtil.EMPTY, inventoryNum, replaceInventoryNum, replaceReason, replaceNote);
	}
	/**
	 * Verify the error message displayed in Confirm Customer page when
	 * violating business rule during Transfer privilege process
	 * 
	 * @param page
	 * @param expectMsg
	 */
	public void verifyBusinessRuleErrorMessage(Object page, String expectMsg) {
		logger.info("When violate the rule, it should displaye message("
				+ expectMsg + ") on the top of confirm customer page.");
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget
				.getInstance();

		boolean result = true;
		String messageUI = "";
		if (page == confirmPg) {
			messageUI = confirmPg.getMessage();
		} else if (page == residencyWidget) {
			messageUI = residencyWidget.getErrorMsg();
			residencyWidget.clickCancel();
			ajax.waitLoading();
		} else {
			result = false;
			logger
					.error("Expect page is LicMgrConfirmCustomerPage/LicMgrResidencyStatusSelectionWidget!");
			if (page instanceof OrmsOrderCartPage) {
				this.cancelCart();
				logger.error("Transfer is successfully!This is not expect!");
			}
		}

		if (null == messageUI || "".equals(messageUI)) {
			result = false;
			logger.error("There isn't any messge display in this page.");
		} else if (!messageUI.matches(expectMsg)) {
			result = false;
			logger.error("Displayed message(" + messageUI
					+ ") is not the same as expected(" + expectMsg + ").");
		}

		if (!result) {
			throw new ErrorOnPageException(
					"Not all the check points are passed! Please check the log above.");
		}
	}

	public void identifyAndConfirmCustomer(Customer cust) {
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage
				.getInstance();
		
		if (identifyCustPg.isIdentifyCustomerSectionExists()) {
			identifyCustPg.identifyCustomer(cust);
		}
		identifyCustPg.selectResidencyStatus(cust.residencyStatus);// if residency status exist, then select.
		ajax.waitLoading();
		identifyCustPg.setResidencyStatusRelatedIden(cust.identifier); //if selected residency status has number, country or state info, then set them
		identifyCustPg.clickOK();
		ajax.waitLoading();
	}

	/**
	 * Verify privilege item status from order page
	 * 
	 * @param privilegeNum
	 * @param expectedStatus
	 */
	public void verifyPrivilegeItemStatusFromOrderPg(String privilegeNum,
			String expectedStatus) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();

		String actualStatus = privOrderDetailsPage
				.getPrivilegeOrderItemStatus(privilegeNum);
		if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
			throw new ErrorOnPageException(
					"The privilege item actual status is " + actualStatus
							+ " but the expected status is " + expectedStatus);
		}
	}

	/**
	 * Verify the privilege item's status in privilege item detail page
	 * 
	 * @param expectedStatus
	 */
	public void verifyPrivilegeStatusFromUI(String expectedStatus) {
		LicMgrPrivilegeItemDetailPage privilegeDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Verify current privilege status is " + expectedStatus);
		String actualStatus = privilegeDetailPg.getPrivilegeStatus();
		if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
			throw new ActionFailedException("The privilege actual status is "
					+ actualStatus + ", but the expected status is "
					+ expectedStatus);
		} else {
			logger.info("----The actual status is corret.");
		}
	}

	/**
	 * Verify whether the error message shows correct or not after try to
	 * transfer privilege
	 * 
	 * @param expectedMsg
	 */
	public void verifyPrivilegeItemCannotBeTransferred(String expectedMsg) {
		LicMgrPrivilegeItemDetailPage privItemDetailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Verify the current privilege can not be transferred.");
		privItemDetailPg.clickTransfer();
		ajax.waitLoading();
		String actualMsg = privItemDetailPg.getErrorMsg();

		if (!actualMsg.equalsIgnoreCase(expectedMsg)) {
			throw new ActionFailedException(
					"The actual error message doesn't match expected.");
		} else {
			logger.info("----The error message shows correctly.");
		}
	}

	/**
	 * The method used to verify transaction name is same with given transaction
	 * from order cart page
	 * 
	 * @param tranName
	 */
	public void verifyTransactionNameInOrdCart(String tranName) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();

		String name = cartPg.getFirstTransactionName();
		String regExp = "(New - 1)";
		if (name.contains(regExp)) {
			name = name.replace(regExp, "").trim();
		}
		if (!tranName.equalsIgnoreCase(name)) {
			throw new ErrorOnPageException("Transaction name is incorrect!",
					tranName, name);
		}
		logger.info("Successfully verify transaction name like:" + tranName);
	}

	/**
	 * Verify whether privilege(s) is/are displayed under a specific order in
	 * the 'Replacement Privileges' list page
	 * 
	 * @param ordNum
	 * @param privilegePurchasingNames
	 */
	public void verifyPrivilegesGroupByOrderNum(String ordNum,
			String... privilegePurchasingNames) {
		LicMgrReplacePrivSaleAddItemPage replaceListPage = LicMgrReplacePrivSaleAddItemPage
				.getInstance();

		logger.info("Verify whether the all privileges are grouped by order - "
				+ ordNum);
		List<String> namesOnUI = replaceListPage.getPrivilegeNames(ordNum);
		if (namesOnUI.size() != privilegePurchasingNames.length) {
			throw new ErrorOnPageException(
					"The privilege instance count is wrong, expected is "
							+ privilegePurchasingNames.length
							+ ", but actual is " + namesOnUI.size());
		}

		for (int i = 0; i < privilegePurchasingNames.length; i++) {
			if (!namesOnUI.contains(privilegePurchasingNames[i])) {
				throw new ErrorOnPageException(
						"The expected privilege instance - "
								+ privilegePurchasingNames[i]
								+ " should be display in the replacement list.");
			}
		}
		logger
				.info("----All privileges are really displayed under the same Order#="
						+ ordNum + " in replacement list.");
	}

	/**
	 * Verify privilege display or do not display on the purchase list
	 * 
	 * @param isDisplay
	 *            : true:verify display ;false: verify do not display
	 * @param privilegeName
	 */
	public void verifyPrivilegeDisplayOnThePurchaseList(boolean isDisplay,
			String... privilegeName) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();

		List<String> prdName = addItemPg.getProductInThePurchaseList();
		for (String product : privilegeName) {
			if (isDisplay) {
				logger.info("Verify the privilege : " + product
						+ " display on the purchase list.");
				if (!prdName.contains(product)) {
					throw new ErrorOnDataException("The privilege : " + product
							+ " do not display on the purchase list.");
				} else {
					logger.info("The privilege : " + product
							+ " display on the purchase list.");
				}
			} else {
				logger.info("Verify the privilege : " + product
						+ " do not display on the purchase list.");
				if (prdName.contains(product)) {
					throw new ErrorOnDataException("The privilege : " + product
							+ " display on the purchase list.");
				} else {
					logger.info("The privilege : " + product
							+ " do not display on the purchase list.");
				}
			}
		}
	}

	/**
	 * Verify the privilege inventory status is the same as expected status
	 * 
	 * @param schema
	 * @param expectedStatus
	 * @param privileges
	 */
	public void verifyPrivilegeInventoryStatus(String schema,
			int expectedStatus, PrivilegeInfo... privileges) {
		for (PrivilegeInfo privilege : privileges) {
			logger.info("Verify the status of privilege - "
					+ privilege.purchasingName + "/" + privilege.inventoryNum);
			int actualStatus = getPrivInventoryStatus(schema,
					privilege.invType, privilege.purchasingName,
					privilege.inventoryNum, privilege.licenseYear);

			if (actualStatus != expectedStatus) {
				throw new ActionFailedException(
						"The privilege inventory status of privilege - "
								+ privilege.purchasingName + "/"
								+ privilege.inventoryNum);
			}

			logger.info("----The status of privilege - "
					+ privilege.purchasingName + "/" + privilege.inventoryNum
					+ " really is " + actualStatus);
		}
	}

	/**
	 * go to customer profile details page from customer search page.
	 * 
	 * @param lincenseType
	 *            -Identifier/Certification/Education type
	 * @param lincenseNum
	 *            -Identifier/Certification/Education value
	 * @param customerClass
	 *            -organization/individual
	 */
	public boolean gotoCuscomerDetailsByLincenseType(String licenseType,
			String licenseNum, String custClass) {
		LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("goto customer profile details page.....");
		customerPg.searchCustomerByIdnetifier(licenseType, licenseNum,
				custClass);
		if (customerPg.exists()) {
			if (customerPg.getWarnMes().length() > 0) {
				// could not get any result by search criteria
				return true;
			} else {
				customerPg.clickCustomerFirstNumer();
			}
		}
		browser.waitExists(detailsPage);
		return false;
	}

	/**
	 * This methods used to add a new question for the contract.
	 * 
	 * @param question
	 * @param isCancel
	 */
	public void addQuestionsForContract(QuestionInfo question, boolean isCancel) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
				.getInstance();
		LicMgrAddQuestionsWidget questionPg = LicMgrAddQuestionsWidget
				.getInstance();
		logger.info("add a new question for the contract...");

		if (!questionPg.exists()) {
			prodConfPg.clickAdd();
			ajax.waitLoading();
			questionPg.waitLoading();
		}

		questionPg.setDisplayText(question.questDisplayText);
		questionPg.setPrintText(question.questPrintText);
		questionPg.selectAnswerType(question.answerType);
		ajax.waitLoading();
		if (questionPg.checkMinLength()) {
			questionPg.setMinLength(question.minLength);
			questionPg.setMaxLength(question.maxLength);
		} else if (questionPg.checkAcceptableAnswer()) {
			for (int i = 0; i < question.anwsers.length; i++) {
				if (i > 0 && i > questionPg.getAcceptableAnswerNum() - 1) {
					questionPg.clickAdd();
					ajax.waitLoading();
				}
				questionPg.setAcceptableAnswer(question.anwsers[i].answer, i);
				questionPg.selectDefault(question.anwsers[i].defaultSelection,
						i);
				ajax.waitLoading();
				if (!"".equals(question.anwsers[i].dispalyOrder)
						&& question.anwsers[i].dispalyOrder.length() > 0) {
					questionPg.setDisplayOrder(
							question.anwsers[i].dispalyOrder, i);
				}
				if (!"".equals(question.anwsers[i].nextAction)
						&& question.anwsers[i].nextAction.length() > 0) {
					questionPg.selectNextAction(question.anwsers[i].nextAction,
							i);
					ajax.waitLoading();
				}
				if (questionPg.checkAnotherQuestionValue()
						&& null != question.anwsers[i].actionValue) {
					int questionNum = questionPg.getAnotherQuestionValueNum();
					for (int j = 0; j < question.anwsers[i].actionValue.length; j++) {
						questionPg.selectAnotherQuestionValue(
								question.anwsers[i].actionValue[j], questionNum
										- 1 + j);
					}
				}
				if (questionPg.checkStopTransactionValue()
						&& null != question.anwsers[i].actionValue) {
					int stopNum = questionPg.getStopTransactionValueNum();
					for (int j = 0; j < question.anwsers[i].actionValue.length; j++) {
						questionPg.selectStopTransactionValue(
								question.anwsers[i].actionValue[j], stopNum - 1
										+ j);
					}
				}
				if (questionPg.checkPrivilegeValue()
						&& null != question.anwsers[i].actionValue) {
					int privNum = questionPg.getPrivilegeValueNum(i);
					for (int j = 0; j < question.anwsers[i].actionValue.length; j++) {
						if (j > 0 && j > privNum - 1) {
							questionPg.clickAddPrivilege(i);
							ajax.waitLoading();
							questionPg
									.waitLoading();
						}
						questionPg.selectPrivilegeValue(
								question.anwsers[i].actionValue[j], i, j);
					}
				}
			}
		} else {
			if (questionPg.checkMinLength()
					|| questionPg.checkAcceptableAnswer()) {
				throw new ErrorOnPageException(
						"Add Question page display the wrong field.");
			}
		}

		if (isCancel) {
			questionPg.clickCancel();
		} else {
			questionPg.clickOK();
			ajax.waitLoading();
		}

		browser.waitExists(prodConfPg, questionPg);

	}

	/**
	 * This methods used to add a new question for the contract.
	 * 
	 * @param question
	 */
	public void addQuestionsForContract(QuestionInfo question) {
		this.addQuestionsForContract(question, false);
	}

	/**
	 * Switch one page to another page
	 * 
	 * @param startPage
	 */
	public void switchIndentifiersAndAddIdentifiersPg(OrmsPage startPage) {
		LicMgrAddIdentifiersPage addIdentifierPg = LicMgrAddIdentifiersPage
				.getInstance();
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		logger.info("Swich identifier and addIdentifier page.");
		if (startPage == addIdentifierPg) {
			addIdentifierPg.clickCancel();
			identifierPg.waitLoading();
		} else if (startPage == identifierPg) {
			identifierPg.clickAddIdentifier();
			ajax.waitLoading();
			addIdentifierPg.waitLoading();
		} else
			throw new ErrorOnPageException("Wrong start page...");
	}

	/**
	 * Switch one page to another page
	 * 
	 * @param startPage
	 */
	public void switchIndentifiersAndEditIdentifiersPg(OrmsPage startPage) {
		LicMgrEditIdentifierPage editIdenPg = LicMgrEditIdentifierPage
				.getInstance();
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		logger.info("Swich identifier and editIdentifier page.");
		if (startPage == editIdenPg) {
			editIdenPg.clickCancel();
			ajax.waitLoading();
			identifierPg.waitLoading();
		} else if (startPage == identifierPg) {
			identifierPg.clickAddIdentifier();
			ajax.waitLoading();
			editIdenPg.waitLoading();
		} else
			throw new ErrorOnPageException("Wrong start page...");
	}

	public void gotoCertificationFromCustomerDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		logger.info("Go to certification page from customerDetails page.");
		custDetailsPg.clickCertificationsTab();
		certificationPg.waitLoading();
	}

	public void gotoIdentifiersFromCustomerDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		logger.info("Go to identifier page from customerDetails page.");
		custDetailsPg.clickIdentifiersTab();
		ajax.waitLoading();
		identifierPg.waitLoading();
	}

	public void gotoAddressedFromCustomerDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerAdressesPage customerAddressPg = LicMgrCustomerAdressesPage
				.getInstance();
		logger.info("Go to identifier page from customerDetails page.");
		custDetailsPg.clickAddressTab();
		ajax.waitLoading();
		customerAddressPg.waitLoading();
	}

	/**
	 * Go to add identifier according click 'Add Identifier'button in
	 * identifiers page and start from customer details page
	 */
	public void gotoAddIdentifiersFromCustomerDetailsPg() {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		logger.info("Go to add identifier page from customerDetails page.");
		this.gotoIdentifiersFromCustomerDetailsPg();
		this.switchIndentifiersAndAddIdentifiersPg(identifierPg);
	}

	/**
	 * Go to edit identifier according specific identifier type and number in
	 * identifiers page and start from customer details page
	 * 
	 * @param identifierType
	 * @param identifierNum
	 */
	public void gotoEditIdentifiersFromCustomerDetailsPg(String identifierType,
			String identifierNum) {
		logger.info("Go to edit identifier page from customerDetails page.");
		this.gotoIdentifiersFromCustomerDetailsPg();
		this.gotoEditIdentifiersFromIdentifietPg(identifierType, identifierNum);
	}

	/**
	 * Go to edit identifier according specific identifier type and number in
	 * identifiers page and start from identifier page
	 * 
	 * @param identifierType
	 * @param identifierNum
	 */
	public void gotoEditIdentifiersFromIdentifietPg(String identifierType,
			String identifierNum) {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();

		this.gotoIdentifierDetailPage(identifierPg.getIdentifierID(
				identifierType, identifierNum));
	}

	/**
	 * 
	 * @param idenStatus
	 * @param idenType
	 * @param idenNum
	 */
	public void gotoEditIdentifiersFromIdentifierPg(String idenStatus,
			String idenType, String idenNum) {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();

		this.gotoIdentifierDetailPage(identifierPg.getIdentifierID(idenStatus,
				idenType, idenNum));
	}

	public void gotoIdentifierDetailPage(String identifierId) {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrEditIdentifierPage editIdentifierPg = LicMgrEditIdentifierPage
				.getInstance();

		logger.info("Goto Identifier " + identifierId + " Detail Page.");

		identifierPg.clickIdentifier(identifierId);
		ajax.waitLoading();
		editIdentifierPg.waitLoading();
	}

	public void verifyIdentifierFromDetailPg(CustomerIdentifier identifier) {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrEditIdentifierPage editIdentifierPg = LicMgrEditIdentifierPage
				.getInstance();

		logger
				.info("Verify Customer Identifier " + identifier.id
						+ " Correct.");

		editIdentifierPg.verifyCustIdentifier(identifier);
		editIdentifierPg.clickOK();
		ajax.waitLoading();
		identifierPg.waitLoading();
	}

	/**
	 * Go to edit education page according specific education number in
	 * education page and start from customer details page
	 * 
	 * @param identifierType
	 * @param identifierNum
	 */
	public void gotoEditEducationFromCustomerDetailsPg(String educationNum) {
		logger.info("Go to edit education page from customerDetails page.");
		this.gotoEducationSubTabFromCustomerDetailsPg();
		this.gotoEditEducationFromEducationPg(educationNum);
	}

	/**
	 * Go to edit education page according specific education number in
	 * education page and start from education page
	 * 
	 * @param identifierType
	 * @param identifierNum
	 */
	public void gotoEditEducationFromEducationPg(String educationNum) {
		LicMgrEditEducationPage editEdu = LicMgrEditEducationPage.getInstance();
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
				.getInstance();
		logger.info("Go to edit education page from customerDetails page.");
		custEducation.clickEducationIdByNum(educationNum);
		editEdu.waitLoading();
	}

	public void gotoSuspensionsFromCustomerDetailsPg() {
		logger.info("Go to Suspensions page from customerDetails page.");
		this.gotoCustomerSubTabPage("Suspensions");
	}

	/**
	 * Switch one page to another page
	 * 
	 * @param startPg
	 */
	public void switchIdentifiersAndSuspensionsPg(OrmsPage startPg) {
		LicMgrCustomerIdentifiersPage identifierPg = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrAddIdentifiersPage addIdentifierWidget = LicMgrAddIdentifiersPage
				.getInstance();
		LicMgrCustomerSuspensionPage suspensionPg = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrAddSuspensionWidget addSuspensionWidget = LicMgrAddSuspensionWidget
				.getInstance();

		logger.info("Swich Identifier page and Suspensions page.");
		if (startPg == identifierPg) {
			if (addIdentifierWidget.exists()) {
				addIdentifierWidget.clickCancel();
				ajax.waitLoading();
				identifierPg.waitLoading();
			}
			identifierPg.clickSuspensionsTab();
			ajax.waitLoading();
			suspensionPg.waitLoading();
		} else if (startPg == suspensionPg) {
			if (addSuspensionWidget.exists()) {
				addSuspensionWidget.clickCancel();
				ajax.waitLoading();
				suspensionPg.waitLoading();
			}
			suspensionPg.clickIdentifiersTab();
			ajax.waitLoading();
			identifierPg.waitLoading();
		} else
			throw new ErrorOnPageException("Wrong start page...");
	}

	/**
	 * Assign privilege product to stores thru location class. This method works
	 * as below flow: select 'Products' option from 'Admin' drop down list in
	 * top menu page to goto privilege list page; goto privilege details page
	 * from privilege list page by clicking privilege code link; in privilege
	 * detail page, click 'Store Assignments' tab to goto privilege store
	 * assignments sub page to do assigning action.
	 * 
	 * @param privilegeCode
	 *            - privilege code is used to click in privilege list page
	 * @param locationClass
	 *            - location class is used to check to assign privilege in
	 *            privilege store assignments page
	 */
	public void assignPrivilegeToStoresThruLocationClass(String privilegeCode,
			String locationClass) {
		gotoProductSearchListPageFromTopMenu("Privileges");
		gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);
		assignPrivilegeToStoresThruLocationClass(locationClass);
	}

	/**
	 * Assign privilege product to stores thru location class in Privilege Store
	 * Assignments Page sub of Privilege Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void assignPrivilegeToStoresThruLocationClass(
			String... locationClasses) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage
				.getInstance();

		logger
				.info("Assign privilege to stores thru Location Class in privilege store assignments page.");
		privilegeDetailPg.clickStoreAssignmentsTab();
		ajax.waitLoading();
		privilegeStoreAssignmentsPg.waitLoading();

		for (String locationClass : locationClasses) {
			privilegeStoreAssignmentsPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		privilegeStoreAssignmentsPg
				.clickAssignToStoresInSelectedLocationClasses();
		privilegeStoreAssignmentsPg.waitLoading();
	}

	/**
	 * Un-Assign privilege from stores thru location class in Privilege Store
	 * Assignments Page sub of Privilege Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are to check to do un-assigning action
	 */
	public void unassignPrivilegeFromStoresThruLocationClass(
			String... locationClasses) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage
				.getInstance();

		logger
				.info("Unassign privilege from stores thru Location Class in privilege store assignments page.");

		privilegeDetailPg.clickStoreAssignmentsTab();
		ajax.waitLoading();
		privilegeStoreAssignmentsPg.waitLoading();

		for (String locationClass : locationClasses) {
			privilegeStoreAssignmentsPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		privilegeStoreAssignmentsPg
				.clickUnassignFromStoresInSelectedLocationClasses();
		privilegeStoreAssignmentsPg.waitLoading();
	}

	/**
	 * Assign vehicle product to stores thru location class in Vehicle Store
	 * Assignments Page sub of Privilege Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void assignVehicleToStoresThruLocationClass(
			String... locationClasses) {
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage
				.getInstance();

		logger
				.info("Assign privilege to stores thru Location Class in privilege store assignments page.");
		if (!vehicleStoreAssignmentsPg.exists()) {
			vehicleDetailPg.clickStoreAssignmentsTab();
			vehicleStoreAssignmentsPg.waitLoading();
		}

		for (String locationClass : locationClasses) {
			vehicleStoreAssignmentsPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		vehicleStoreAssignmentsPg
				.clickAssignToStoresInSelectedLocationClasses();
		ajax.waitLoading();
		vehicleStoreAssignmentsPg.waitLoading();
	}

	/**
	 * Un-Assign vehicle from stores thru location class in Vehicle Store
	 * Assignments Page sub of Privilege Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are to check to do un-assigning action
	 */
	public void unassignVehicleFromStoresThruLocationClass(
			String... locationClasses) {
		LicMgrEditVehicleDetailsPage privilegeDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage
				.getInstance();

		logger
				.info("Unassign vehicle from stores thru Location Class in vehicle store assignments page.");
		if (!vehicleStoreAssignmentsPg.exists()) {
			privilegeDetailPg.clickStoreAssignmentsTab();
			vehicleStoreAssignmentsPg.waitLoading();
		}

		for (String locationClass : locationClasses) {
			vehicleStoreAssignmentsPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		vehicleStoreAssignmentsPg
				.clickUnassignFromStoresInSelectedLocationClasses();
		vehicleStoreAssignmentsPg.waitLoading();
	}

	/**
	 * Assign consumable product to stores thru location class. Works as below
	 * work flow: Switch to consumable store assignment page, then check the
	 * check boxes of location class which will be assigned Click 'Assign to
	 * Stores In Selected Location Classes' button to finish this assigning
	 * process
	 * 
	 * @param locationClasses
	 */
	public void assignConsumableToStoresThruLocationClass(
			String... locationClasses) {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage
				.getInstance();

		logger
				.info("Assign consumable to stores thru location class in consumable store assignment page.");
		consumableDetailsPg.clickAgentAssignmentTab();
		consumableStoreAssignmentPg.waitLoading();

		for (String locationClass : locationClasses) {
			consumableStoreAssignmentPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		consumableStoreAssignmentPg
				.clickAssignToStoresInSelectedLocationClasses();
		consumableStoreAssignmentPg.waitLoading();
	}

	/**
	 * Un-assign consumable product from stores thru location class in
	 * consumable store assignment page
	 * 
	 * @param locationClasses
	 */
	public void unassignConsumableFromStoresThruLocationClass(
			String... locationClasses) {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage
				.getInstance();

		logger
				.info("Unassign consumable from stores thru location class in consumable store assignment page.");
		if (!consumableStoreAssignmentPg.exists()) {
			consumableDetailsPg.clickAgentAssignmentTab();
			consumableStoreAssignmentPg.waitLoading();
		}

		for (String locationClass : locationClasses) {
			consumableStoreAssignmentPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		consumableStoreAssignmentPg
				.clickUnassignFromStoresInSelectedLocationClasses();
		consumableStoreAssignmentPg.waitLoading();
	}

	/**
	 * Get all vendor bank account store assignments info indentified by store
	 * name
	 * 
	 * @param storeName
	 * @return
	 */
	public List<List<String>> getVendorBankAccountStoreAssignmentInfo(
			String storeName) {
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrVendorBankAccountStoreAssignmentsDetailsWidget vendorBankAccountDetailsPg = LicMgrVendorBankAccountStoreAssignmentsDetailsWidget
				.getInstance();

		vendorBankAccountPg.clickViewStoreAccountAssignments();
		ajax.waitLoading();
		vendorBankAccountDetailsPg.waitLoading();
		vendorBankAccountDetailsPg.setupFilter(false, true, false, storeName,
				"");
		List<List<String>> assignments = vendorBankAccountDetailsPg
				.getVendorBankAccountStoreAssignmentByStoreName(storeName);
		vendorBankAccountDetailsPg.clickOK();
		ajax.waitLoading();
		vendorBankAccountPg.waitLoading();

		return assignments;
	}

	/**
	 * Get a specific vendor bank account store assignment record identified by
	 * store name and account id
	 * 
	 * @param storeName
	 * @param accountID
	 * @return
	 */
	public List<String> getVendorBankAccountStoreAssignmentInfo(
			String storeName, String accountID) {
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrVendorBankAccountStoreAssignmentsDetailsWidget vendorBankAccountDetailsPg = LicMgrVendorBankAccountStoreAssignmentsDetailsWidget
				.getInstance();

		vendorBankAccountPg.clickViewStoreAccountAssignments();
		vendorBankAccountDetailsPg.waitLoading();
		vendorBankAccountDetailsPg.setupActiveAssignmentByStoreName(storeName);
		List<String> assignment = vendorBankAccountDetailsPg
				.getVendorBankAccountStoreAssignmentByStoreNameAndAccountID(
						storeName, accountID);
		vendorBankAccountDetailsPg.clickOK();
		ajax.waitLoading();
		vendorBankAccountPg.waitLoading();

		return assignment;
	}

	/**
	 * Get vendor bank account store assignment id identified by store name
	 * 
	 * @param storeName
	 * @return
	 */
	public List<String> getVendorBankAccountStoreAssignmentID(String storeName) {
		List<String> ids = new ArrayList<String>();
		List<List<String>> assignments = this
				.getVendorBankAccountStoreAssignmentInfo(storeName);
		for (int i = 0; i < assignments.size(); i++) {
			ids.add(assignments.get(i).get(0));
		}

		return ids;
	}

	/**
	 * Verify the action of assigning product(Privilege/Vehicle/Consumable) to
	 * stores thru location class successfully in
	 * LicMgrPrivilegeProductStoreAssignmentsPage,
	 * LicMgrVehicleProductStoreAssignmentsPage or
	 * LicMgrConsumableProductStoreAssignmentPage.
	 * 
	 * @param locationClass
	 * @return - the assigned stores which has been assigned product thru
	 *         location class
	 */
	public List<StoreInfo> verifyAssignProductToStoresThruLocationClassAction(
			String locationClass) {
		LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage
				.getInstance();
		LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage
				.getInstance();
		LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage
				.getInstance();
		LicMgrSupplyAgentAssignmentsPage supplyStoreAssignmentPg = LicMgrSupplyAgentAssignmentsPage
				.getInstance();
		LicMgrLotteryStoreAssignmentsPage lotteryStoreAssignmentsPg = LicMgrLotteryStoreAssignmentsPage.getInstance();
		
		LicMgrProductStoreWidget storeWidget = LicMgrProductStoreWidget
				.getInstance();
		LicMgrProductAssignedStoresWidget assignedStoreWidget = LicMgrProductAssignedStoresWidget
				.getInstance();

		logger
				.info("----Verify assign products to stores thru location class successfully.");
		List<String> assignment = null;
		List<StoreInfo> storesInSelectedLocationClass = null;
		List<StoreInfo> assignedStores = null;
		ILicMgrProductAgentAssignmentsPage page = (ILicMgrProductAgentAssignmentsPage)browser.waitExists(privilegeStoreAssignmentsPg,
				vehicleStoreAssignmentsPg, consumableStoreAssignmentPg,
				supplyStoreAssignmentPg, lotteryStoreAssignmentsPg);
		// get location class store assignment info
		assignment = page.getLocationClassStoreAssignmentInfo(locationClass);

		// get all stores info in selected location class
		page.clickNumberOfAgentsInLocationClassByLocationName(locationClass);
		ajax.waitLoading();
		storeWidget.waitLoading();
		storesInSelectedLocationClass = storeWidget.getAllStoresInfo();
		storeWidget.clickOK();

		// get all assigned stores info in assigned location class
		page.clickNumberOfAgentsActivelyAssignedByLocationName(locationClass);
		ajax.waitLoading();
		assignedStoreWidget.waitLoading();
		assignedStores = assignedStoreWidget.getAllStoresInfo();
		assignedStoreWidget.clickOK();

		// verify the location class-agent assignment record
		if (!assignment.get(2).equalsIgnoreCase("Yes")
				|| Integer.parseInt(assignment.get(3)) != Integer
						.parseInt(assignment.get(4))) {
			throw new ErrorOnDataException(
					"Assign product to store thru Location Class - "
							+ locationClass + " failed.");
		}

		// verify the stores in location class are really assigned correctly
		for (int i = 0; i < storesInSelectedLocationClass.size(); i++) {
			if (!storesInSelectedLocationClass.get(i).isAssignedProductThruLocationClass) {
				throw new ErrorOnDataException("Store-"
						+ storesInSelectedLocationClass.get(i).storeName
						+ " in Location Class " + locationClass
						+ " didn't be assigned.");
			}
		}

		// verify the assigned stores equal with stores in selected location
		// class
		for (int i = 0; i < assignedStores.size(); i++) {
			if (!storesInSelectedLocationClass.contains(assignedStores.get(i))) {
				throw new ErrorOnDataException(
						"Assigned stores don't match stores in selected location class.");
			}
		}

		return assignedStores;
	}

	/**
	 * The method used to go to privilege quantity control page
	 * 
	 * @param privCode
	 */
	public void gotoPrivilegeQuantityControlPgFromTopMenue(String privCode) {
		logger.info("Go to quantity page from privilege detail page.");
		this.gotoPrivilegeSubTabPage(privCode, "Quantity Control");
	}

	/**
	 * The method is used to go to edit privilege quantity control page
	 * 
	 * @param quantityControlID
	 */
	public void gotoEditPrivilegeQuantityControlPg(String quantityControlID) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editQuanControlPg = LicMgrPrivilegeEditQuantityControlWidget
				.getInstance();

		logger.info("Go to quantity control page.");
		quantityControlPg.waitLoading();// added by Peter Zhu
		quantityControlPg.clickQuantityControlID(quantityControlID);
		ajax.waitLoading();
		editQuanControlPg.waitLoading();
	}

	/**
	 * The method is used to deactivate privilege quantity control
	 * 
	 * @param quantityControlID
	 */
	public void deactivatePrivilegeQuantityControl(String quantityControlID) {
		this.changePrivilegeQuantityControlStatus(quantityControlID,
				INACTIVE_STATUS);
	}

	/**
	 * The method is used to active privilege quantity control
	 * 
	 * @param quantityControlID
	 */
	public void activatePrivilegeQuantityControl(String quantityControlID) {
		this.changePrivilegeQuantityControlStatus(quantityControlID,
				ACTIVE_STATUS);
	}

	/**
	 * The method is used to change privilege quantity control status
	 * 
	 * @param quantityControlID
	 * @param isInactive
	 */
	public void changePrivilegeQuantityControlStatus(String quantityControlID,
			String status) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editQuanControlPg = LicMgrPrivilegeEditQuantityControlWidget
				.getInstance();

		this.gotoEditPrivilegeQuantityControlPg(quantityControlID);
		if (status.equalsIgnoreCase(INACTIVE_STATUS)) {
			logger.info("Deactive quantity control.");
			editQuanControlPg.selectStatus(INACTIVE_STATUS);
		} else if (status.equalsIgnoreCase(ACTIVE_STATUS)) {
			logger.info("Active quantity control.");
			editQuanControlPg.selectStatus(ACTIVE_STATUS);
		} else {
			throw new ObjectNotFoundException("Can't find status - " + status);
		}

		editQuanControlPg.clickOK();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}

	/**
	 * Search quantity control by criteria
	 * 
	 * @param quantityControlStatus
	 * @param locationClass
	 */
	public void searchQuantityControlByCriteria(String quantityControlStatus,
			String locationClass) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();

		logger.info("Search quantity control by criteria.");
		quantityControlPg.setQuantityControlSearchInfo(quantityControlStatus,
				locationClass);
		quantityControlPg.clickGo();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}

	/**
	 * The methods used to go to product question page in privilege detail page
	 * 
	 * @param privCode
	 *            : privilege code
	 */
	public void gotoPrivilegeQuestionPgFromTopMenu(String privCode) {
		LicMgrPrivilegeProductDetailsPage editPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeQuestionPage qusetionPage = LicMgrPrivilegeQuestionPage
				.getInstance();

		logger.info("Go to question page from privilege detail page...");
		this.gotoProductSearchListPageFromTopMenu("Privileges");
		this.gotoPrivilegeDetailsPageFromProductListPage(privCode);
		editPage.clickQuestionsTab();
		ajax.waitLoading();
		qusetionPage.waitLoading();
	}

	/**
	 * The methods used to go to product Contractor Fees page in privilege
	 * detail page
	 * 
	 * @param privCode
	 *            : privilege code
	 */
	public void gotoPrivilegeContractorFeesPgFromTopMenu(String privCode) {
		LicMgrPrivilegeProductDetailsPage editPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeContractorFeesPage contractorPage = LicMgrPrivilegeContractorFeesPage
				.getInstance();

		logger.info("Go to Contractor Fees page from privilege detail page...");
		this.gotoProductSearchListPageFromTopMenu("Privileges");
		this.gotoPrivilegeDetailsPageFromProductListPage(privCode);
		editPage.clickContractorFeesTab();
		contractorPage.waitLoading();
	}

	/**
	 * The methods used to go to product Contractor Fees page in vehicle detail
	 * page
	 * 
	 * @param vehicleCode
	 */
	public void gotoVehicleContractorFeesPgFromTopMenu(String vehicleCode) {
		LicMgrEditVehicleDetailsPage editPage = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrVehicleContractFeesPage contractorPage = LicMgrVehicleContractFeesPage
				.getInstance();

		logger.info("Go to Contractor Fees page from Vehicle detail page...");
		this.gotoVehicleProductDetailsPage(vehicleCode);
		editPage.clickContractorFeesTab();
		contractorPage.waitLoading();
	}

	/**
	 * The method used to go to privilege document page in privilege detail page
	 * 
	 * @param privCode
	 */
	public void gotoPrivilegeDocumentPgFromTopMenu(String privCode) {
		this.gotoProductPrintDocumentListPageFromTopMenu("Privilege", privCode);
	}

	/**
	 * The method used to go to privilege document page from Privileges product
	 * details page, ends at Privilege Print Document page.
	 * 
	 * @param privCode
	 */
	public void gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(String privCode) {
		this.gotoPrivilegeSubTabPage(privCode, "Print Documents");
	}

	/**
	 * This method used to go to vehicle document page. Start from top menu, end
	 * at vehicle document page
	 * 
	 * @param vehicleCode
	 */
	public void gotoVehicleDocumentPgFromTopMenu(String vehicleCode) {
		this
				.gotoProductPrintDocumentListPageFromTopMenu("Vehicle",
						vehicleCode);
	}

	/**
	 * Go to product print document sub_tab form top drop down list....
	 * 
	 * @param proType
	 * @param proCode
	 * @return the end page
	 *         (LicMgrPrivilegePrintDocumentsPage/LicMgrVehiclePrintDocumentsPage
	 *         )
	 */
	public ILicMgrProductPrintDocumentPage gotoProductPrintDocumentListPageFromTopMenu(
			String proType, String proCode) {
		this.gotoProductSearchListPageFromTopMenu(proType);
		return this.gotoProductPrintDocumentListPageFromPrivilegeListPg(
				proType, proCode);
	}

	/**
	 * Go to product print document sub_tab form privilege list page
	 * 
	 * @param proType
	 * @param proCode
	 * @return the end page
	 *         (LicMgrPrivilegePrintDocumentsPage/LicMgrVehiclePrintDocumentsPage
	 *         )
	 */
	public ILicMgrProductPrintDocumentPage gotoProductPrintDocumentListPageFromPrivilegeListPg(
			String proType, String proCode) {
		ILicMgrProductPrintDocumentPage docPage = null;

		logger.info("Go to " + proType + " print document tab.");
		if (proType.matches("(?i)Privilege(s)?")) {
			docPage = (ILicMgrProductPrintDocumentPage) LicMgrPrivilegePrintDocumentsPage
					.getInstance();
			LicMgrPrivilegeProductDetailsPage privilegeDetailPage = LicMgrPrivilegeProductDetailsPage
					.getInstance();
			this.gotoPrivilegeDetailsPageFromProductListPage(proCode);
			privilegeDetailPage.clickPrintDocumentsTab();
		} else if (proType.matches("(?i)Vehicle(s)?")) {
			docPage = (ILicMgrProductPrintDocumentPage) LicMgrVehiclePrintDocumentsPage
					.getInstance();
			LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
					.getInstance();
			this.gotoVehicleProductDetailsPageFromListPage(proCode);
			vehicleDetailPg.clickPrintDocumentsTab();
		} else {
			throw new ErrorOnDataException("Unknown product.");
		}
		docPage.waitLoading();
		return docPage;
	}

	/**
	 * Inactive privilege document assignment
	 * 
	 * @param docTemplateAssignmentID
	 *            : document template assignment ID
	 */
	public void deactivePrivilegeDocumentAssignment(
			String docTemplateAssignmentID) {
		LicMgrPrivilegePrintDocumentsPage printDocumentPage = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();

		logger.info("Deactive document template assignment ID - "
				+ docTemplateAssignmentID);
		printDocumentPage.clickPrintDocumentID(docTemplateAssignmentID);
		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.selectStatus("Inactive");
		editWidget.clickOK();
		ajax.waitLoading();
		printDocumentPage.waitLoading();
	}

	/**
	 * Deactivate all privilege document assignment in privilege print document
	 * assignment page
	 */
	public void deactivateAllPrivilegeDocumentAssignment() {
		LicMgrPrivilegePrintDocumentsPage printDocumentPage = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		if (!printDocumentPage.exists()) {
			printDocumentPage.clickPrintDocumentsTab();
			ajax.waitLoading();
			printDocumentPage.waitLoading();
		}
		printDocumentPage.searchPrintDocument(false,
				ACTIVE_STATUS, "", "");
		List<String> printDocIDs = printDocumentPage
				.getPrintDocumentAssignmentIDColumn();
		if (printDocIDs.size() > 0 && printDocIDs.get(0).equalsIgnoreCase("ID")) {
			printDocIDs.remove(0);
		}

		for (String ids : printDocIDs) {
			this.deactivePrivilegeDocumentAssignment(ids);
		}
	}

	/**
	 * Inactive vehicle document assignment
	 * 
	 * @param docTemplateAssignmentID
	 *            : document template assignment ID
	 */
	public void deactiveVehicleDocumentAssignment(String docTemplateAssignmentID) {
		LicMgrVehiclePrintDocumentsPage printDocumentPg = LicMgrVehiclePrintDocumentsPage
				.getInstance();
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
				.getInstance();

		logger.info("Deactive document template assignment ID "
				+ docTemplateAssignmentID);
		printDocumentPg.clickPrintDocumentID(docTemplateAssignmentID);
		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.selectStatus("Inactive");
		editWidget.clickOK();
		ajax.waitLoading();
		printDocumentPg.waitLoading();
	}

	/**
	 * The methods used to go to product business rule page in privilege detail
	 * page
	 * 
	 * @param privCode
	 *            : privilege code
	 */
	public void gotoProductRulePgFromTopMenu(String privCode) {
		LicMgrPrivilegeProductDetailsPage editPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		logger.info("Go to business rule page from privilege detail page...");
		this.gotoProductSearchListPageFromTopMenu("Privileges");
		this.gotoPrivilegeDetailsPageFromProductListPage(privCode);
		editPage.clickBusinessRuleTab();
		ajax.waitLoading();
		rulePage.waitLoading();
	}

	/**
	 * The methods used to go to text display page in privilege detail page
	 * 
	 * @param privCode
	 *            : privilege code
	 */
	public void gotoProductTextDisplayPgFromTopMenu(String privCode) {
		LicMgrPrivilegeProductDetailsPage editPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();

		logger.info("Go to business rule page from privilege detail page...");
		this.gotoProductSearchListPageFromTopMenu("Privileges");
		this.gotoPrivilegeDetailsPageFromProductListPage(privCode);
		editPage.clickTextDisplayTab();
		textPage.waitLoading();
	}

	/**
	 * go to product pricing page from product list page
	 * 
	 * @param prodType
	 *            privileges/Consumables/vehicles/supplies
	 * @param prodCode
	 */
	public ILicMgrProductPricingPage gotoProductPricingPageFromListPage(
			String prodType, String prodCode) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrSuppliesListPage supplyListPage = LicMgrSuppliesListPage
				.getInstance();
		LicMgrConsumableListPage cunsumableListPage = LicMgrConsumableListPage
				.getInstance();
		LicMgrVehiclesListPage vehicleListPage = LicMgrVehiclesListPage
				.getInstance();
		LicMgrLotteriesProductListPage lotteryListPage = LicMgrLotteriesProductListPage.getInstance();
		ILicMgrProductPricingPage lastPage = null;
		logger.info("Go to " + prodType
				+ " pricing page from product list page");

		if (privilegeListPage.exists()) {
			lastPage = LicMgrPrivilegePricingPage.getInstance();
		} else if (vehicleListPage.exists()) {
			lastPage = LicMgrVehiclePricingPage.getInstance();
		} else if (cunsumableListPage.exists()) {
			lastPage = LicMgrConsumableProductPricingPage.getInstance();
		} else if (supplyListPage.exists()) {
			lastPage = LicMgrSupplyPricingPage.getInstance();
		} else if (lotteryListPage.exists()) {
			lastPage = LicMgrLotteryPricingPage.getInstance();
		} else {
			throw new ErrorOnPageException("Unknown Page");
		}

		gotoProductDetailsPageFromProductListPage(prodType, prodCode, "Pricing");
		return lastPage;
	}

	public void gotoProductDetailsPageFromProductListPage(String prodType,
			String prodCode, String subTab) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrSuppliesListPage supplyListPage = LicMgrSuppliesListPage
				.getInstance();
		LicMgrConsumableListPage cunsumableListPage = LicMgrConsumableListPage
				.getInstance();
		LicMgrVehiclesListPage vehicleListPage = LicMgrVehiclesListPage
				.getInstance();
		LicMgrLotteriesProductListPage lotteryListPage = LicMgrLotteriesProductListPage.getInstance();
		logger.info("Go to " + prodType + " Product " + prodCode
				+ " Details page from product list page");

		if (privilegeListPage.exists()) {
			this.gotoPrivilegeSubTabPage(prodCode, subTab);
		} else if (vehicleListPage.exists()) {
			this.gotoVehicleProductSubTabPage(prodCode, subTab);
		} else if (cunsumableListPage.exists()) {
			LicMgrConsumableProductDetailsPage cunsumableDetailsPage = LicMgrConsumableProductDetailsPage
					.getInstance();
			String id = cunsumableListPage.getConsumableId(prodCode);
			this.gotoConsumableProductDetailsPageFromListPage(id);
			if (subTab != null && subTab.trim().length() > 0) {
				cunsumableDetailsPage.clickSubTab(subTab);
			}
		} else if (supplyListPage.exists()) {
			LicMgrSupplyProductDetailsPage supplyDetailsPage = LicMgrSupplyProductDetailsPage
					.getInstance();
			supplyListPage.clickSupplyCode(prodCode);
			ajax.waitLoading();
			supplyDetailsPage.waitLoading();
			if (subTab != null && subTab.trim().length() > 0) {
				supplyDetailsPage.clickSubTab(subTab);
				ajax.waitLoading();
			}
		} else if(lotteryListPage.exists()) {
			LicMgrLotteryProductDetailsPage lotteryDetailsPage = LicMgrLotteryProductDetailsPage.getInstance();
			this.gotoLotteryProductDetailsPageFromProductListPage(prodCode);
			if(!StringUtil.isEmpty(subTab)) {
				lotteryDetailsPage.clickSubTab(subTab);
				ajax.waitLoading();
			}
		} else {
			throw new ErrorOnPageException("Unknown Page");
		}
	}

	public void gotoEditProductPricingWidget(String pricingID,
			ILicMgrProductPricingPage pricingPage) {
		LicMgrEditProductPricingWidget editPricingWidget = LicMgrEditProductPricingWidget
				.getInstance();

		logger.info("Go to edit hf product pricing widget.");
		pricingPage.clickPricingID(pricingID);
		ajax.waitLoading();
		editPricingWidget.waitLoading();
	}

	/**
	 * Deactivate product pricing in Edit HF product pricing widget
	 * 
	 * @param pricingID
	 * @param productPricingPage
	 */
	public void deactivateProductPricing(String pricingID,
			ILicMgrProductPricingPage productPricingPage) {
		LicMgrEditProductPricingWidget editPricingWidget = LicMgrEditProductPricingWidget
				.getInstance();

		this.gotoEditProductPricingWidget(pricingID, productPricingPage);

		logger.info("Deactivate product pricing - " + pricingID + ".");
		editPricingWidget.selectStatus("Inactive");
		editPricingWidget.clickOK();
		ajax.waitLoading();
		productPricingPage.waitLoading();
	}

	/**
	 * Deactivate all active product pricing records
	 * 
	 * @param productPricingPage
	 */
	public void deactivateAllProductPricings(
			ILicMgrProductPricingPage productPricingPage) {
		productPricingPage.searchPricingRecords("Active");
		List<String> ids = productPricingPage.getPricingIDs();

		for (String str : ids) {
			this.deactivateProductPricing(str, productPricingPage);
		}

		// add click Pricing tab option in order to avoid an exception which can't check Show Current Records Only check box
		productPricingPage.clickPricingTab();
		ajax.waitLoading();
		productPricingPage.waitLoading();

		productPricingPage.selectStatus("");
		productPricingPage.checkShowCurrentRecordsOnly();
		productPricingPage.waitLoading();
	}

	/**
	 * Deactivate all active privilege quantity control records
	 */
	public void deactivatePrivilegeQuantityControlRecords() {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();

		logger
				.info("Deactivate all active privilege quantity control records.");
		if (!quantityControlPg.exists()) {
			quantityControlPg.clickQuantityControlTab();
			ajax.waitLoading();
			quantityControlPg.waitLoading();
		}
		quantityControlPg.uncheckShowCurrentRecordsOnly();
		quantityControlPg.selectStatus(ACTIVE_STATUS);
		quantityControlPg.clickGo();
		ajax.waitLoading();
		List<String> ids = quantityControlPg.getQuantityControlIDs();
		if (ids.size() > 0) {
			for (String id : ids) {
				this.deactivatePrivilegeQuantityControl(id);
			}
		}
		logger.info("There is not any matched record.");
	}

	/**
	 * Get the product pricing info from Edit HF Product Pricing widget
	 * 
	 * @param pricingID
	 * @param productPricingPage
	 * @return
	 */
	public PricingInfo getProductPricingInfo(String pricingID,
			ILicMgrProductPricingPage productPricingPage) {
		LicMgrEditProductPricingWidget editPricingWidget = LicMgrEditProductPricingWidget
				.getInstance();

		logger.info("Get the product pricing(ID#=" + pricingID
				+ ") record info");
		productPricingPage.searchPricingRecords();
		productPricingPage.clickPricingID(pricingID);
		ajax.waitLoading();
		editPricingWidget.waitLoading();
		PricingInfo pricing = editPricingWidget.getPricingInfo();
		editPricingWidget.clickCancel();
		ajax.waitLoading();
		productPricingPage.waitLoading();

		return pricing;
	}

	/**
	 * Get customer certification details information from Edit Certification
	 * widget
	 * 
	 * @param certificationID
	 * @return
	 */
	public Certification getCertificationInfo(String certificationID) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget
				.getInstance();

		logger.info("Get Customer Certification info.");
		this.gotoEditCertificationWidget(certificationID);
		Certification certification = editCertificationWidget
				.getCertificationInfo();
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		certificationPg.waitLoading();

		return certification;
	}

	/**
	 * Go to customer certification edit page, start from certification page,
	 * end at eidt certification page
	 * 
	 * @param certificationID
	 */
	public void gotoEditCertificationWidget(String certificationID) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget
				.getInstance();

		logger.info("Go to certification edit page.");
		certificationPg.clickCertificationID(certificationID);
		ajax.waitLoading();
		editCertificationWidget.waitLoading();
	}

	/**
	 * Add pricing for vehicle product. Start from vehicle pricing page
	 * 
	 * @param pricing
	 * @return
	 * @author Lesley Wang
	 * @date Jul 16, 2012
	 */
	public String addPricingForVehicleProduct(PricingInfo pricing) {
		return this.addPricingForProduct(pricing, LicMgrVehiclePricingPage
				.getInstance());
	}

	/**
	 * add pricing information for product
	 * 
	 * @param pricing
	 * @param pricingPage
	 * @return
	 */
	public String addPricingForProduct(PricingInfo pricing,
			ILicMgrProductPricingPage pricingPage) {
		return this.addPricingForProduct(pricing, pricingPage, true);
	}

	public void gotoAddPrdPricingWidgetFromPricingListPg(){
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage.getInstance();
		logger.info("Go to add prd pricing widget from pricing list page.");
		prdPricingPg.clickAddProductPricing();
		ajax.waitLoading();
		addProdPringWidget.waitLoading();
	}
	
	public void gotoPricingListPgFromFromAddPricingWidget(){
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage.getInstance();
		logger.info("Go to prd pricing lsit page from add pricing widget.");
		addProdPringWidget.clickCancel();
		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}
	
	
	public void gotoAddTaxPage(String priceId, boolean isNew){
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage.getInstance();
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget.getInstance();
		LicMgrEditProductPricingWidget editPriceingWidget=LicMgrEditProductPricingWidget.getInstance();
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		
		logger.info("Go to add tax page from pricing list page.");
		
		if(isNew){
			prdPricingPg.clickAddProductPricing();
			ajax.waitLoading();
			addProdPringWidget.waitLoading();
			addProdPringWidget.clickTax();
		}else{
			if(StringUtil.notEmpty(priceId)){
				prdPricingPg.clickPricingID(priceId);
				ajax.waitLoading();
				editPriceingWidget.waitLoading();
				editPriceingWidget.clickTax();
			}else{
				throw new ErrorOnPageException("Need price id, please check parameter...");
			}
			
		}
		
		ajax.waitLoading();
		taxWidget.waitLoading();
	}
	
	public void addTaxInfo(PricingInfo pricingInfo){
		LicMgrProductTaxWidget taxWidget = LicMgrProductTaxWidget.getInstance();
		LicMgrPrivilegePricingPage prdPricingPg = LicMgrPrivilegePricingPage.getInstance();
		
		logger.info("Add tax info at tax widget.");
		taxWidget.setTaxInfo(pricingInfo);
		taxWidget.clickOK();
		
		ajax.waitLoading();
		prdPricingPg.waitLoading();
	}
	
	public void addTaxForAnExistingPricing(PricingInfo pricingInfo){
		logger.info("Add Tax info for an existing pricing. Pricing id = " + pricingInfo.id);
		this.gotoAddTaxPage(pricingInfo.id, false);
		this.addTaxInfo(pricingInfo);
	}
	
	/**
	 * add pricing information for product
	 * 
	 * @param pricing
	 * @param pricingPage
	 * @param ok
	 *            click 'OK'(true) or "Cancel"(false) after entering all
	 *            information
	 */
	public String addPricingForProduct(PricingInfo pricing,
			ILicMgrProductPricingPage pricingPage, boolean ok) {
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget
				.getInstance();
		LicMgrPrivilegePricingPage privilegePricingPage = LicMgrPrivilegePricingPage
				.getInstance();
		LicMgrConsumableProductPricingPage consumablePricingPage = LicMgrConsumableProductPricingPage
				.getInstance();
		LicMgrVehiclePricingPage vehiclePricingPage = LicMgrVehiclePricingPage
				.getInstance();
		LicMgrSupplyPricingPage supplyPricingPage = LicMgrSupplyPricingPage
				.getInstance();
		LicMgrLotteryPricingPage lotteryPricingPage = LicMgrLotteryPricingPage.getInstance();
		
		logger.info("Add pricing for " + pricing.prodType + ":"
				+ pricing.prodCode);
		if (!addProdPringWidget.exists()) {
			pricingPage.clickAddProductPricing();
			ajax.waitLoading();
			addProdPringWidget.waitLoading();
		}
		addProdPringWidget.setPricingInfo(pricing);
		if (ok) {
			addProdPringWidget.clickOK();
		} else {
			addProdPringWidget.clickCancel();
		}

		ajax.waitLoading();
		Object page = browser.waitExists(addProdPringWidget,
				privilegePricingPage, consumablePricingPage,
				vehiclePricingPage, supplyPricingPage, lotteryPricingPage);
		String toReturn = "";
		if (addProdPringWidget == page) {
			toReturn = addProdPringWidget.getErrorMsg();
		} else if (ok) {
			toReturn = pricingPage.getPricingID(pricing);
		}

		return toReturn;
	}

	/**
	 * get Question Info From EditQuestionWidget Start
	 * From:LicMgrPrivilegeQuestionPage End
	 * with:From:LicMgrPrivilegeQuestionPage
	 * 
	 * @param id
	 * @return
	 */
	public QuestionInfo getQuestionInfoFromEditQuestionWidget(String id) {
		LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrEditProductQuestionWidget editpage = LicMgrEditProductQuestionWidget
				.getInstance();

		logger.info("get question info from Edit Question widget");
		QuestionInfo question = null;
		if (!privilegeQuestionPg.exists()) {
			privilegeQuestionPg.clickQuestionsTab();
			ajax.waitLoading();
			privilegeQuestionPg.waitLoading();
		}
		privilegeQuestionPg.unSelectShowCurrentOnly();
		privilegeQuestionPg.clickGo();
		ajax.waitLoading();
		privilegeQuestionPg.clickQuestionAssignmentID(id);
		editpage.waitLoading();
		question = editpage.getQuestionInfo();
		editpage.clickCancel();
		return question;
	}

	public Map<String, String> addQuestionsForProduct(QuestionInfo question) {
		return safeAddQuestionsForProduct(question, new NoCompare());
	}

	public Map<String, String> safeAddQuestionsForProduct(
			QuestionInfo question, QuestionCompare qc) {
		LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrConsumableProductQuestionsPage questionConsumPage = LicMgrConsumableProductQuestionsPage
				.getInstance();
		LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage
				.getInstance();

		Map<String, String> map = new HashMap<String, String>();

		this.safeAddQuestionsForProduct(question, false, qc);
		if (privilegeQuestionPg.exists()) {
			map = privilegeQuestionPg
					.getProductQuestionAssignmentLocationClassIDMap(question);
		} else if (questionConsumPage.exists()) {
			map = questionConsumPage
					.getProductQuestionAssignmentLocationClassIDMap(question);
		} else if (supplyQuestionPg.exists()) {
			map = supplyQuestionPg
					.getProductQuestionAssignmentLocationClassIDMap(question);
		} else {
			throw new ActionFailedException("Unknow page.");
		}

		return map;
	}

	public static interface QuestionCompare {

		/**
		 * Check if the question exists
		 * 
		 * @return Question id
		 */
		public String exist(QuestionInfo question);
	}

	public static class NoCompare implements QuestionCompare {

		@Override
		public String exist(QuestionInfo question) {
			return null; // Always return null, will not check existing record
		}

	}

	public void safeAddQuestionsForProduct(QuestionInfo question,
			boolean isCancel, QuestionCompare qc) {
		LicMgrPrivilegeProductDetailsPage editPrivilegePage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeQuestionPage privilegeQuestionPage = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrConsumableProductQuestionsPage questionConsumPage = LicMgrConsumableProductQuestionsPage
				.getInstance();
		LicMgrConsumableProductDetailsPage consumableDetailsPage = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrSupplyProductDetailsPage supplyDetailPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrAddProductQuestionWidget questionWidget = LicMgrAddProductQuestionWidget
				.getInstance();
		LicMgrSupplyQuestionPage supplyQuestioPg = LicMgrSupplyQuestionPage
				.getInstance();

		logger.info("add a new question for the privilege...");

		ILicMgrProductQuestionPage questionPg = null;
		if (!questionWidget.exists()) {
			if (editPrivilegePage.exists()) {
				if (!privilegeQuestionPage.exists()) {
					editPrivilegePage.clickQuestionsTab();
					ajax.waitLoading();
					privilegeQuestionPage.waitLoading();
				}

				questionPg = privilegeQuestionPage;
				ajax.waitLoading();
			} else if (consumableDetailsPage.exists()) {
				if (!questionConsumPage.exists()) {
					consumableDetailsPage.clickQuestionsTab();
					ajax.waitLoading();
					questionConsumPage.waitLoading();
				}

				questionPg = questionConsumPage;
				ajax.waitLoading();
			} else if (supplyDetailPg.exists()) {
				if (!supplyQuestioPg.exists()) {
					supplyDetailPg.clickQuestionsTab();
					ajax.waitLoading();
					supplyQuestioPg.waitLoading();
				}
				questionPg = supplyQuestioPg;
			}

			String existingQuestionId = qc.exist(question);
			if (StringUtil.notEmpty(existingQuestionId)) {
				this.deactivateProductQuestion(questionPg, existingQuestionId);
			}

			questionPg.clickAddQuestion();
			ajax.waitLoading();

			questionWidget.waitLoading();
		}
		questionWidget.setupQuestionAssignmentInfo(question);

		if (isCancel) {
			questionWidget.clickCancel();
			ajax.waitLoading();
		} else {
			questionWidget.clickOK();
			ajax.waitLoading();
		}

		browser.waitExists(questionWidget, privilegeQuestionPage,
				questionConsumPage, supplyQuestioPg);
	}

	void deactivateProductQuestion(
			ILicMgrProductQuestionPage questionPg, String questionId) {
		questionPg.clickQuestionAssignmentID(questionId);
		ajax.waitLoading();
		this.deactiveProductQuestion();
	}

	void deactiveProductQuestion() {
		LicMgrAddProductQuestionWidget questionWidget = LicMgrAddProductQuestionWidget
				.getInstance();
		questionWidget.waitLoading();
		questionWidget.selectStatus("Inactive");
		questionWidget.clickOK();
		ajax.waitLoading();
	}

	public void addQuestionsForProduct(QuestionInfo question, boolean isCancel) {
		safeAddQuestionsForProduct(question, isCancel, new NoCompare());
	}

	/**
	 * This method is used to add privilege product, start from privilege list
	 * page, end privilege list page
	 * 
	 * @param privilege
	 */
	public void addPrivilegeProduct(PrivilegeInfo privilege) {
		this.addPrivilegeProduct(privilege, false);
	}

	/**
	 * This method is used to add privilege product, start from privilege list
	 * page, end privilege list page
	 * 
	 * @param privilege
	 * @param isCancel
	 */
	public void addPrivilegeProduct(PrivilegeInfo privilege, boolean isCancel) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();

		logger.info("Add Privilege Product(Code#=" + privilege.code + ").");

		this.gotoCreatePrivilegeProductPageFromPrivilegeListPage();
		createwPrivilegeProductPage.setupPrivilegeInfo(privilege);
		if (isCancel) {
			createwPrivilegeProductPage.clickCancel();
		} else {
			createwPrivilegeProductPage.clickOk();
		}
		ajax.waitLoading();
		browser.waitExists(privilegeListPage, createwPrivilegeProductPage);
	}

	public void addLotteryProduct(HFLotteryProduct prd) {
		this.addLotteryProduct(prd, false);
	}

	public void addLotteryProduct(HFLotteryProduct prd, boolean isCancel) {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();
		LicMgrCreateNewLotteryProductPage createProductPage = LicMgrCreateNewLotteryProductPage
				.getInstance();

		logger.info("Add lottery Product(Code#=" + prd.getCode() + ").");

		this.gotoNewLotteryProductPageFromListPage();
		createProductPage.setupProductInfo(prd);
		if (isCancel) {
			createProductPage.clickCancel();
		} else {
			createProductPage.clickOk();
		}
		ajax.waitLoading();
		browser.waitExists(lotteriesProductListPg, createProductPage);
	}

	public void gotoLotteryProductDetailsPageFromProductListPage(String code) {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage
				.getInstance();

		LicMgrLotteryProductDetailsPage detailsPg = LicMgrLotteryProductDetailsPage
				.getInstance();

		logger
				.info("Go to LicMgrLotteryProductDetailsPage From lotteriesProductListPg: product code ="
						+ code);
		lotteriesProductListPg.clickCode(code);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

	public void gotoLotteryProductContractorFeesPage() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryContractFeesPage feesPage = LicMgrLotteryContractFeesPage.getInstance();
		
		logger.info("Goto lottery product - Contractor Fees tab.");
		detailsPage.clickContractFeesTab();
		ajax.waitLoading();
		feesPage.waitLoading();
	}
	
	public void gotoLotteryProductLicenseYearsPage() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryLicenseYearsPage licYearPage = LicMgrLotteryLicenseYearsPage.getInstance();
		
		logger.info("Goto lottery product - License Years tab.");
		detailsPage.clickLicenseYearsTab();
		ajax.waitLoading();
		licYearPage.waitLoading();
	}
	
	public void gotoLotteryProductAgentAssignmentPage() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryStoreAssignmentsPage assignmentPage = LicMgrLotteryStoreAssignmentsPage.getInstance();
		
		logger.info("Goto lottery product - Agent Assignment tab.");
		detailsPage.clickAgentAssignmentsTab();
		ajax.waitLoading();
		assignmentPage.waitLoading();
	}
	
	public void gotoLotteryProductParametersPg() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryParametersListPage paramsPage = LicMgrLotteryParametersListPage.getInstance();
		
		logger.info("Goto lottery product - Parameters tab.");
		detailsPage.clickParametersTab();
		ajax.waitLoading();
		paramsPage.waitLoading();
	}
	
	/**
	 * Add Lottery Parameters
	 * @param params
	 * @return
	 * @author Lesley Wang
	 * @Date Mar 25, 2014
	 */
	public List<String> addLotteryParameters(List<Data<LotteryParameterInfo>> params) {
		LicMgrLotteryParametersListPage paramListPg = LicMgrLotteryParametersListPage.getInstance();
		LicMgrAddLotteryParameterWidget addWidget = LicMgrAddLotteryParameterWidget.getInstance();

		logger.info("Add Lottery Parameter Info.");
		paramListPg.clickAddParameter();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setParameterInfo(params);
		addWidget.clickOK();
		ajax.waitLoading();
		List<String> toReturn = new ArrayList<String>();
		Object page = browser.waitExists(addWidget, paramListPg);
		if (page == addWidget) {
			toReturn.add(addWidget.getErrorMsg());
		} else if (page == paramListPg) {
			paramListPg.waitLoading();
			paramListPg.searchLotteryParameters(ACTIVE_STATUS);
			for(int i=0; i<params.size(); i++){
				toReturn.add(paramListPg.getLotteryParamID(LotteryParameterInfo.Description.getStrValue(params.get(i))));
			}
		}
		return toReturn;
	}
	
	public void gotoLotteryProductQuantityControlPage() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryQuantityControlsPage qtyControlPage = LicMgrLotteryQuantityControlsPage.getInstance();
		
		logger.info("Goto lottery product - Quantity Controls tab.");
		detailsPage.clickQuantityControlsTab();
		ajax.waitLoading();
		qtyControlPage.waitLoading();
	}

	public String addLotteryQuantityControl(QuantityControlInfo quantityControl) {
		return addLotteryQuantityControl(quantityControl, true);
	}
	
	public String addLotteryQuantityControl(QuantityControlInfo quantityControl, boolean isClickOk) {
		LicMgrLotteryQuantityControlsPage quantityControlPg = LicMgrLotteryQuantityControlsPage.getInstance();
		LicMgrLotteryQuantityControlDetailsWidget addWidget = LicMgrLotteryQuantityControlDetailsWidget.getInstance();

		logger.info("Add Lottery Quantity Control.");
		if (!quantityControlPg.exists()) {
			quantityControlPg.clickQuantityControlsTab();
			ajax.waitLoading();
			quantityControlPg.waitLoading();
		}
		quantityControlPg.clickAddQuantityControl();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setQuantityControlInfo(quantityControl);
		if (isClickOk) {
			addWidget.clickOK();
		} else {
			addWidget.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(addWidget, quantityControlPg);
		String toReturn = "";
		if (addWidget == page) {
			toReturn = addWidget.getErrorMessage();
		}
		if (quantityControlPg == page && isClickOk) {
			toReturn = quantityControlPg.getQuantityControlID(
					quantityControl.locationClass,
					quantityControl.multiSalesAllowance);
			logger.info("----New Quantity Control id - " + toReturn);
		}

		return toReturn;
	}
	
	/**
	 * Assign or unassign privilege lottery product to stores thru location class in Privilege Lottery Store
	 * Assignments Page sub of Privilege Lottery Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void assignOrUnassignLotteryProductToStoresThruLocationClass(boolean isAssigned,
			String... locationClasses) {
		LicMgrLotteryProductDetailsPage detailPg = LicMgrLotteryProductDetailsPage
				.getInstance();
		LicMgrLotteryStoreAssignmentsPage storeAssignmentsPg = LicMgrLotteryStoreAssignmentsPage
				.getInstance();

		logger
				.info("Assign lottery product to stores thru Location Class in lottery store assignments page.");
		detailPg.clickAgentAssignmentsTab();
		ajax.waitLoading();
		storeAssignmentsPg.waitLoading();

		for (String locationClass : locationClasses) {
			storeAssignmentsPg.selectLocationClassCheckboxByName(locationClass);
		}
		if (isAssigned) {
			storeAssignmentsPg.clickAssignToStoresInSelectedLocationClasses();
		} else {
			storeAssignmentsPg.clickUnassignFromStoresInSelectedLocationClasses();
		}
		ajax.waitLoading();
		storeAssignmentsPg.waitLoading();
	}

	/**
	 * Assign privilege lottery product to stores thru location class in Lottery Store
	 * Assignments Page sub of Lottery Product Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void assignLotteryProductToStoresThruLocationClass(
			String... locationClasses) {
		this.assignOrUnassignLotteryProductToStoresThruLocationClass(true, locationClasses);
	}
	
	/**
	 * Unassign privilege lottery product to stores thru location class in Lottery Store
	 * Assignments Page sub of Lottery Product Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void unassignLotteryProductToStoresThruLocationClass(
			String... locationClasses) {
		this.assignOrUnassignLotteryProductToStoresThruLocationClass(false, locationClasses);
	}
	
	public void gotoLotteryProductHuntsPage() {
		LicMgrLotteryProductDetailsPage detailsPage = LicMgrLotteryProductDetailsPage.getInstance();
		LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
		
		detailsPage.clickHuntsTab();
		ajax.waitLoading();
		huntsPg.waitLoading();
	}
	
	public void openAssignHuntWidgetFromLotteryDetailsPg() {
		LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
		LicMgrAssignHuntWidget assignHunt = LicMgrAssignHuntWidget.getInstance();
		
		if (!huntsPg.exists()) {
			gotoLotteryProductHuntsPage();
		}
		huntsPg.clickAssignHunt();
		ajax.waitLoading();
		assignHunt.waitLoading();
	}
	
	public void assignHuntToLotteryFromLotteryDetailsPg(boolean isOK, String... huntCodes) {
		LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
		LicMgrAssignHuntWidget assignHunt = LicMgrAssignHuntWidget.getInstance();
		
		logger.info("Assign hunt to lottery from lottery product details page...");
		openAssignHuntWidgetFromLotteryDetailsPg();
		for (String code : huntCodes) {
			if (StringUtil.notEmpty(code)) {
				logger.info("assigned hunt code=" + code);
				assignHunt.selectAssignmentCheckbox(code);
			}
		}
		if (isOK) {
			assignHunt.clickOK();
		} else {
			assignHunt.clickCancel();
		}
		ajax.waitLoading();
		huntsPg.waitLoading();
	}
	
	public void assignHuntToLotteryFromLotteryDetailsPg(String... huntCodes) {
		this.assignHuntToLotteryFromLotteryDetailsPg(true, huntCodes);
	}
	/**
	 * Edit hunt assignment details
	 * @param assignID
	 * @param isActive
	 * @param isOK
	 * @author Lesley
	 * @date Jan 16, 2014
	 */
	public void editHuntAssignmentDetails(String assignID, boolean isActive, boolean isOK) {
		LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
		LicMgrViewHuntAssignmentWidget viewAssignmentPg = LicMgrViewHuntAssignmentWidget.getInstance();
		
		logger.info("Edit hunt assignment details... Assign ID=" + assignID);
		huntsPg.clickAssignID(assignID);
		ajax.waitLoading();
		viewAssignmentPg.waitLoading();
		
		// insert checkpoint to verify assignment info
		check(Check_ID.editHuntAssignmentDetails);
		
		viewAssignmentPg.selectStatus(isActive);
		if (isOK) {
			viewAssignmentPg.clickOK();
		} else {
			viewAssignmentPg.clickCancel();
		}
		ajax.waitLoading();
		huntsPg.waitLoading();
	}
	
	public void activateHuntAssignment(String assignID) {
		this.editHuntAssignmentDetails(assignID, true, true);
	}
	
	public void dectivateHuntAssignment(String assignID) {
		this.editHuntAssignmentDetails(assignID, false, true);
	}
	/**
	 * Deactivate product(including Privileges and Consumables) question
	 * assignment identified by assignment id
	 * 
	 * @param assignmentID
	 */
	public void deactivateProductQuestionAssignment(String... assignmentIDS) {
		LicMgrPrivilegeQuestionPage privilegeQuestionPg = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();
		LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage
				.getInstance();
		LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
				.getInstance();

		for (String assignmentID : assignmentIDS) {
			logger.info("Deactivate Product question assignment(ID="
					+ assignmentID + ").");
			if (privilegeQuestionPg.exists()) {
				privilegeQuestionPg.clickQuestionAssignmentID(assignmentID);
			} else if (consumableQuestionPg.exists()) {
				consumableQuestionPg.clickQuestionAssignmentID(assignmentID);
			} else if (supplyQuestionPg.exists()) {
				supplyQuestionPg.clickQuestionAssignmentID(assignmentID);
			}

			editQuestionWidget.waitLoading();
			editQuestionWidget.selectStatus("Inactive");
			editQuestionWidget.clickOK();
			browser.waitExists(privilegeQuestionPg, consumableQuestionPg,
					supplyQuestionPg);
		}
	}
	
	/**
	 * Go to edit privilege question page
	 * 
	 * @param quesAssignmentID
	 * @param isInactive
	 */
	public void gotoEditProductQuestionPg(String quesAssignmentID) {
		LicMgrPrivilegeQuestionPage privilegequestionPage = LicMgrPrivilegeQuestionPage
				.getInstance();
		LicMgrEditProductQuestionWidget editQuestionWidget = LicMgrEditProductQuestionWidget
				.getInstance();
		LicMgrConsumableProductQuestionsPage consumableQuestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();
		LicMgrSupplyQuestionPage supplyQuestionPg = LicMgrSupplyQuestionPage
				.getInstance();

		logger.info("Go to edit product question detail page.");
		if (privilegequestionPage.exists()) {
			privilegequestionPage.clickQuestionAssignmentID(quesAssignmentID);
		} else if (consumableQuestionPg.exists()) {
			consumableQuestionPg.clickQuestionAssignmentID(quesAssignmentID);
		} else if (supplyQuestionPg.exists()) {
			supplyQuestionPg.clickQuestionAssignmentID(quesAssignmentID);
		}

		editQuestionWidget.waitLoading();
	}

	/**
	 * Verify specific education status from DB
	 * 
	 * @param eduID
	 * @param eduStatus
	 *            : 1--Active 2--Inactive
	 * @param expectVerifyStatus
	 *            : 1--Failed 2--Pending 3--Verified 4--Not Application
	 * @param schema
	 */
	public void verifyEducationVerifyStatusFromDB(String eduID,
			String eduStatus, String expectVerifyStatus, String schema) {
		// Initialize education status
		if (eduStatus.equals("Active")) {
			eduStatus = "1";
		} else if (eduStatus.equals("Inactive")) {
			eduStatus = "2";
		} else if (eduStatus.equals("Verified")) {
			eduStatus = "4";
		}
		// Initialize education verify status
		if (expectVerifyStatus
				.equals(VERIFICATION_STATUS_FAILED)) {
			expectVerifyStatus = VERIFICATION_STATUS_FAILED_ID;
		} else if (expectVerifyStatus
				.equals(VERIFICATION_STATUS_PENDING)) {
			expectVerifyStatus = VERIFICATION_STATUS_PENDING_ID;
		} else if (expectVerifyStatus
				.equals(VERIFICATION_STATUS_VERIFIED)) {
			expectVerifyStatus = VERIFICATION_STATUS_VERIFIED_ID;
		} else if (expectVerifyStatus
				.equals(VERIFICATION_STATUS_NOTAPPLICABLE)) {
			expectVerifyStatus = VERIFICATION_STATUS_NOTAPPLICABLE_ID;
		}
		// Verify warning message
		String sql = "select verify_status_id from c_cust_hfp_education where id = '"
				+ eduID
				+ "' and status_id = '"
				+ eduStatus
				+ "' and deleted_ind = '0'";
		db.resetSchema(schema);
		List<String> verifyStatus = db.executeQuery(sql, "VERIFY_STATUS_ID");
		logger.info("Execute sql: " + sql);
		if (verifyStatus.size() < 0 || verifyStatus.size() != 1) {
			throw new ErrorOnDataException("Query result is incorrect.");
		} else {
			if (!expectVerifyStatus.equals(verifyStatus.get(0))) {
				throw new ErrorOnDataException("The actual verify statsu: '"
						+ verifyStatus.get(0)
						+ "' is not match the expect verify status: '"
						+ expectVerifyStatus + "'");
			}
		}
	}

	/**
	 * switch the specified page to another page
	 * 
	 * @param startPage
	 */
	public void switchEducationAndAddEducationPg(OrmsPage startPage) {
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrAddEducationPage addEducation = LicMgrAddEducationPage
				.getInstance();
		logger.info("Swich education and addEducation page.");
		if (startPage == addEducation) {
			addEducation.clickCancel();
			ajax.waitLoading();
			custEducation.waitLoading();
		} else if (startPage == custEducation) {
			custEducation.clickAddEducation();
			addEducation.waitLoading();
		} else
			throw new ErrorOnDataException("Wrong page...");
	}

	/**
	 * switch the specified page to another page
	 * 
	 * @param startPage
	 */
	public void switchEducationAndEditEducationPg(OrmsPage startPage) {
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrEditEducationPage editEducation = LicMgrEditEducationPage
				.getInstance();
		logger.info("Swich education and editEducation page.");
		if (startPage == editEducation) {
			editEducation.clickCancel();
			custEducation.waitLoading();
		} else if (startPage == custEducation) {
			custEducation.clickAddEducation();
			editEducation.waitLoading();
		} else {
			throw new ErrorOnDataException("Wrong Page..");
		}
	}

	public void gotoEducationSubTabFromCustomerDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerEducationPage education = LicMgrCustomerEducationPage
				.getInstance();
		logger.info("Go to education page from customerDetails page.");
		custDetailsPg.clickEducationTab();
		ajax.waitLoading();
		education.waitLoading();
	}

	/**
	 * Go to ViewEducationDeferralRecordsPage From CustomerDetailsPg
	 */
	public void gotoViewEducationDeferralRecordsFromCustomerDetailsPg() {
		LicMgrCustomerEducationPage education = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrViewEducationDeferralRecordsPage viewEducationDeferralRecordsPg = LicMgrViewEducationDeferralRecordsPage
				.getInstance();

		logger
				.info("Go to View Education Deferral Records page from customer details page.");
		this.gotoEducationSubTabFromCustomerDetailsPg();

		education.clickViewEducationDeferralRecords();
		ajax.waitLoading();
		viewEducationDeferralRecordsPg.waitLoading();
	}

	/**
	 * This method is used to goto Education page from
	 * ViewEducationDeferralRecordsPage
	 */
	public void gotoEducationPageFromViewEducationDeferralRecordsPage() {
		LicMgrCustomerEducationPage education = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrViewEducationDeferralRecordsPage viewEducationDeferralRecordsPg = LicMgrViewEducationDeferralRecordsPage
				.getInstance();

		logger
				.info("Go to Education page from View Education Deferral Records Page.");
		viewEducationDeferralRecordsPg.clickOK();
		ajax.waitLoading();
		education.waitLoading();
	}

	/**
	 * Get customer suspension record detail info by an unique suspension
	 * comment
	 * 
	 * @param comment
	 * @return
	 */
	public Suspension getCustomerSuspensionByComment(String comment) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();

		String id = suspensionPage.getSuspensionIdByComment(comment);
		Suspension suspension = this.getCustomerSuspensionById(id);
		return suspension;
	}

	/**
	 * Get customer suspension record detail info by suspension id
	 * 
	 * @param suspensionID
	 * @return
	 */
	public Suspension getCustomerSuspensionById(String suspensionID) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage
				.getInstance();

		Suspension suspension = suspensionPage.getSuspensionById(suspensionID);
		return suspension;
	}

	/**
	 * Deactivate privilege product in privilege details page identified by
	 * privilege code 1. Go to privilege search list page from selecting
	 * 'Products' option of 'Admin' drop down list in top menu 2. Go to
	 * privilege product details page by click product code link in list page 3.
	 * In privilege details page, select 'Inactive' option of Status drop down
	 * list. 4. Click 'OK' button to finish this process
	 * 
	 * @param privilegeCode
	 */
	public void deactivatePrivilegeProduct(String privilegeCode) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger
				.info("Deactivate privilege product in LicMgrPrivilegeProductDetailsPage.");
		gotoPrivilegeSearchListPageFromTopMenu();
		gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);
		privilegeDetailPg
				.selectPrivilegeStatus(INACTIVE_STATUS);
		privilegeDetailPg.clickApply();
	}

	/**
	 * Deactivate vehicle product in vehicle details page identified by product
	 * code 1. Go to vehicle search list page from selecting 'Products' option
	 * of 'Admin' drop down list in top menu 2. Go to vehicle product details
	 * page by click product code link in list page 3. In vehicle details page,
	 * select 'Inactive' option of Status drop down list. 4. Click 'OK' button
	 * to finish this process
	 * 
	 * @param vehicleCode
	 */
	public void deactivateVehicleProduct(String vehicleCode) {
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();

		logger
				.info("Deactivate vehicle product in LicMgrEditVehicleDetailsPage.");
		gotoVehicleSearchListPageFromTopMenu();
		gotoVehicleProductDetailsPage(vehicleCode);
		vehicleDetailPg.selectStatus("Inactive");
		vehicleDetailPg.clickApply();
	}

	/**
	 * Deactivate consumable product identified by consumable product id. This
	 * method works below work flow: 1. Go to consumable search list page from
	 * selecting 'Products' option of 'Admin' drop down list in top menu 2. Go
	 * to consumable product details page by click product code link in list
	 * page 3. In consumable details page, select 'Inactive' option of Status
	 * drop down list. 4. Click 'OK' button to finish this process
	 * 
	 * @param consumableID
	 */
	public void deactivateConsumableProduct(String consumableID) {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();

		logger
				.info("Deactivate consumable product in LicMgrConsumableProductDetailsPage.");
		gotoConsumableSearchListPageFromTopMenu();
		gotoConsumableProductDetailsPageFromListPage(consumableID);
		consumableDetailsPg.selectStatus("Inactive");
	}

	/**
	 * Go to edit education widget according specific education number in
	 * education page and start from customer details page or
	 * LicMgrEditEducationPage
	 * 
	 * @param eduNum
	 */
	public void gotoCustomerEditEducationWiget(String eduNum) {
		LicMgrCustomerEducationPage custEducationPg = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrEditEducationPage editEducationPg = LicMgrEditEducationPage
				.getInstance();
		logger.info("Go to edit education page from customerDetails page.");
		if (!custEducationPg.exists())
			this.gotoEducationSubTabFromCustomerDetailsPg();
		custEducationPg.clickEducationIdByNum(eduNum);
		editEducationPg.waitLoading();
	}

	/**
	 * go to customer profile details page from customer search page.
	 * 
	 * @param lincenseType
	 *            -Identifier/Certification/Education type
	 * @param lincenseNum
	 *            -Identifier/Certification/Education value
	 * @param customerClass
	 *            -organization/individual
	 */
	public void gotoCuscomerDetailsFromSearchPg(String licenseType,
			String licenseNum, String custClass) {
		LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();

		logger.info("goto customer profile details page.....");
		customerPg.searchCustomerByIdnetifier(licenseType, licenseNum,
				custClass);
		Object page = browser.waitExists(detailsPage, customerPg);
		if (page == customerPg) {
			customerPg.clickCustomerNumber(licenseNum);
			ajax.waitLoading();
			browser.waitExists(detailsPage);
		}
	}

	/**
	 * goto privilege product license year page, start from privilege product
	 * details page.
	 */
	public void gotoPrivilegeLicenseYearPage() {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		if (!licenseYearPg.exists()) {
			privilegeDetailsPg.clickLicenseYearTab();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
		}
	}

	/**
	 * search customer using customer profile...
	 */
	public void searchCustomer(Customer cust) {
		this.searchCustomer(cust, null);
	}

	/**
	 * search customer using customer profile...
	 */
	public void searchCustomer(Customer cust, CleanUpSwitch clearupList) {
		LicMgrCustomersSearchPage custSearch = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrAlertPopupWidget alertWidget = LicMgrAlertPopupWidget.getInstance();
		
		logger.info("search customer using customer profile.....");
		if (clearupList != null) {
			custSearch.clearUpSearchCriteria(clearupList);
		}
		custSearch.setSearchCriteria(cust);
		custSearch.clickSearch();
		ajax.waitLoading();

		browser.waitExists(custSearch, alertWidget, detailsPage);
	}

	/**
	 * Search customer from home page customer section
	 * 
	 * @param cust
	 * @author tchen
	 */
	public void searchCustomerFromHomePageCustomerSection(Customer cust) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		logger.info("Search customer from home page customer section.");
		homePg.setSearchCustomerCriteria(cust);
		homePg.clickSearchInCustomers();
		ajax.waitLoading();
		if (browser.url().contains("CustomerDetail"))
		{
			custDetailPg.waitLoading();
			custDetailPg.clickHome();
		}
		else {
			custSearchPg.waitLoading();
			custSearchPg.verifySearchCustomerProfileResult(cust);

		}

	}
	
	
	/**
	 * Search vendor from home page vendor section
	 * 
	 * @param vendor
	 * @author tchen
	 */
	public void searchVendorFromHomePageVendorSection(VendorInfo vendor) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		

		logger.info("Search vendor from home page vendor section.");
		homePg.selectVendorOrStore(vendor.translation);
		homePg.setStoreIDOrVendorNum(vendor.number);
		homePg.setVendorName(vendor.name);
		homePg.clickSearchInVendorsStores();
		ajax.waitLoading();
		if (browser.url().contains("VendorDetailsPage"))
		{
			vendorDetailPg.waitLoading();
			vendorDetailPg.clickHome();
		}
		else {
			vendorSearchPg.waitLoading();
			vendorSearchPg.checkVendorInfoIsExistInListPg(vendor.number);
		}

	}
	
	
	/**
	 * Search store from home page store section
	 * 
	 * @param store
	 * @author tchen
	 */
	public void searchStoreFromHomePageStoreSection(StoreInfo store) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVendorSearchListPage storeSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		

		logger.info("Search store from home page store section.");
		homePg.selectVendorOrStore(store.translation);
		homePg.setStoreIDOrVendorNum(store.storeID);
		homePg.setVendorName(store.storeName);
		homePg.clickSearchInVendorsStores();
		ajax.waitLoading();
		if (browser.url().contains("StoreDetailsPage"))
		{
			storeDetailPg.waitLoading();
			storeDetailPg.clickHome();
		}
		else {
			storeSearchPg.waitLoading();
			storeSearchPg.checkVendorInfoIsExistInListPg(store.storeID);
		}

	}

	/**
	 * goto privilege product print document page, start from privilege product
	 * details page.
	 */
	public void gotoPrivilegePrintDocumentPage() {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		if (!printDocSubTab.exists()) {
			privilegeDetailsPg.clickPrintDocumentsTab();
			ajax.waitLoading();
			printDocSubTab.waitLoading();
		}
	}

	/**
	 * add license year info, start from license Year Page, ends at license year
	 * page. if the license year ID exist based on given locationClass and
	 * License Year, return. otherwise add the license year based on given
	 * license year info.
	 * 
	 * @param ly
	 * @return LicenseYear data collection that match with the given location
	 *         class and lic Year
	 */
	public LicenseYear addLicenseYear(LicenseYear ly) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeAddLicYearWidget addYearWidget = LicMgrPrivilegeAddLicYearWidget
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editYearWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		LicenseYear newLy = new LicenseYear();
		
		if (!licenseYearPg.exists()) {
			licenseYearPg.clickLicenseYearTab();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
		}

		String licYearId = licenseYearPg.getLicenseYearId(ly.locationClass,
				ly.licYear);

		if (licYearId == null || licYearId.trim().length() < 1) {
			licenseYearPg.clickAddLicenseYear();
			addYearWidget.waitLoading();
			addYearWidget.setLicenseYearInfo(ly);
			addYearWidget.clickOK();
		} else {
			licenseYearPg.clickLicenseYear(licYearId);
			ajax.waitLoading();
			editYearWidget.waitLoading();
			editYearWidget.setLicenseYearInfo(ly);
			editYearWidget.clickOK();
		}

		ajax.waitLoading();
		licenseYearPg.waitLoading();

		newLy = licenseYearPg.getLicenseYearInfo(ly.locationClass, ly.licYear);
		return newLy;
	}

	/**
	 * Deactivate privilege license year
	 * 
	 * @param licYearId
	 */
	public void deactivatePrivilegeLicenseYear(String licYearId) {
		LicMgrPrivilegeLicenseYearPage licenseYearPage = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editWidget = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();

		logger.info("Deactivate Privilege License Year - " + licYearId);
		licenseYearPage.clickLicenseYear(licYearId);
		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.selectStatus(INACTIVE_STATUS);
		editWidget.clickOK();
		ajax.waitLoading();
		licenseYearPage.waitLoading();
	}

	/*
	 * Deactivate all license year of privilege in privilege license year page.
	 */
	public void deactivateAllPrivilegeLicenseYear() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		logger.info("Deactivate all license year.");
		List<String> licYearIDs = licenseYearPg.getLicenseYearIds();

		if (licYearIDs.size() > 0 && !licYearIDs.get(0).matches("\\d+")) {
			licYearIDs.remove(0);
		}

		for (String ids : licYearIDs) {
			this.deactivatePrivilegeLicenseYear(ids);
		}
	}

	public void deactiveBusinessRuleForProduct(String... ruleId) {
		if (null != ruleId && ruleId.length > 0) {
			for (int i = 0; i < ruleId.length; i++) {
				logger.info("deactive business rule " + ruleId[i]
						+ " for privilege...");
				this
						.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleId[i]);
				this.deactivateBusinessRule();
			}
		} else {
			throw new ErrorOnDataException("There is no rule need to deactive.");
		}

	}

	public void deactivateBusinessRule() {
		LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget
				.getInstance();

		editPg.selectStatus("Inactive");
		editPg.clickOK();
		ajax.waitLoading();
		rulePg.waitLoading();
	}

	/**
	 * Deactivate business rule from privilege business rule list page
	 * 
	 * @param ids
	 */
	public void deactivateBusinessRules(String... ids) {
		for (String id : ids) {
			logger.info("Deactivate business rule - " + id);
			this.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(id);
			this.deactivateBusinessRule();
		}
	}

	/**
	 * safe add privilege business rule method, before adding, deactivate all
	 * ACTIVE rules of the product
	 * 
	 * @param rule
	 * @return
	 */
	public String[] safeAddBusinessRuleForProduct(PrivilegeBusinessRule rule) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		List<String> ids = rulePage.getRuleIdsByStatus(true);
		if (ids.size() > 0) {
			this.deactivateBusinessRules(ids.toArray(new String[0]));
		}
		return this.addBusinessRuleForProduct(rule);
	}

	public String[] addBusinessRuleForProduct(PrivilegeBusinessRule ruleInfo) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		this.addBusinessRuleForProduct(ruleInfo, false);
		String[] ruleId = null;
		if (rulePage.exists()) {
			if (ruleInfo.isProductMuti) {
				ruleId = new String[1];
				ruleId[0] = rulePage.getRuleInfoFromBusinessRulePg(ruleInfo, 0).id;

			} else {
				ruleId = new String[ruleInfo.parameters.length];
				for (int i = 0; i < ruleInfo.parameters.length; i++) {
					ruleId[i] = rulePage.getRuleInfoFromBusinessRulePg(
							ruleInfo, i).id;
				}
			}
		}
		return ruleId;
	}

	public void addBusinessRuleForProduct(PrivilegeBusinessRule ruleInfo,
			boolean isCancel) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		LicMgrPrivilegeAddBusinessRuleWidget addRulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();
		logger.info("add a new business rule for the privilege...");

		if (!addRulePg.exists()) {
			rulePage.clickAddBusinessRule();
			ajax.waitLoading();
			addRulePg.waitLoading();
		}

		addRulePg.selectRuleCategory(ruleInfo.ruleCategory);
		ajax.waitLoading();
		addRulePg.selectBusinessRule(ruleInfo.name);
		ajax.waitLoading();
		addRulePg.waitLoading();
		if (ruleInfo.parameters != null) {
			for (int i = 0; i < ruleInfo.parameters.length; i++) {
				if (i > 0 && addRulePg.getRemoveButtonNum() - 1 != i) {
					addRulePg.clickAddButton();
					ajax.waitLoading();
					addRulePg.waitLoading();
				}

				if ("Privilege Cross Reference".equals(ruleInfo.ruleCategory) || "Licence Cross Reference".equals(ruleInfo.ruleCategory)) { // Lesley[20130912]: update per different rule category name in SK
					if (StringUtil.notEmpty(ruleInfo.parameters[i].product)) {
						addRulePg.selectPrivilege(
								ruleInfo.parameters[i].product, i);
					}
					if (addRulePg.isPurchaseTypeExist()) {
						addRulePg.selectPurchaseType(
								ruleInfo.parameters[i].purchaseType, i);
					}
					if (!StringUtil
							.isEmpty(ruleInfo.parameters[i].requiredQuantity)) {
						addRulePg.setRequiredQuantity(
								ruleInfo.parameters[i].requiredQuantity, i);
					}
					if (!StringUtil
							.isEmpty(ruleInfo.parameters[i].freeQuantity)) {
						addRulePg.setFreeQuantity(
								ruleInfo.parameters[i].freeQuantity, i);
					}

					// Added by Jane
					if (addRulePg.isMatchLYExist()
							&& Boolean.TRUE
									.equals(ruleInfo.parameters[i].matchLicYear)) {
						addRulePg.selectMatchLY(i);
						ajax.waitLoading();
					}

					if (null != ruleInfo.parameters[i].postedToParas
							&& ruleInfo.parameters[i].postedToParas.size() > 0) {
						for (int j = 0; j < ruleInfo.parameters[i].postedToParas
								.size(); j++) { // updated by pzhu
							String para = ruleInfo.parameters[i].postedToParas
									.get(j);
							addRulePg.selectPostedToParameter(para, i);
						}
					}
					// //Added by Jane
					// if(addRulePg.isPriNumExist() &&
					// ruleInfo.parameters[i].requirePriNum){
					// addRulePg.selectPriNum(i);
					// }
					// //Added by Jane
					// if(addRulePg.isInvNumExist() &&
					// ruleInfo.parameters[i].requireInvNum){
					// addRulePg.selectInvNum(i);
					// }
					//
					// if(addRulePg.isValidFromDateExist() &&
					// ruleInfo.parameters[i].validFromDate){
					// addRulePg.selectValidFromDate(i);
					// }
					//
					// if(addRulePg.isValidToDateExist() &&
					// ruleInfo.parameters[i].validToDate){
					// addRulePg.selectValidToDate(i);
					// }
					//
					// if(addRulePg.isValidFromTimeExist() &&
					// ruleInfo.parameters[i].validFromTime){
					// addRulePg.selectValidFromTime(i);
					// }
					//
					// if(addRulePg.isValidToTimeExist() &&
					// ruleInfo.parameters[i].validToTime){
					// addRulePg.selectValidToTime(i);
					// }
				} else if ("Customer Demographic".equals(ruleInfo.ruleCategory)) {
					if (addRulePg.isOnDateExist()) {
						addRulePg.setOnDate(ruleInfo.parameters[i].onDate);
					}
					if (addRulePg.isAgeExist()) {
						addRulePg.setAge(ruleInfo.parameters[i].age);
					}
					if (StringUtil.notEmpty(ruleInfo.parameters[i].prompt)) {
						addRulePg.setPrompt(ruleInfo.parameters[i].prompt);
					}
//					if (StringUtil
//							.notEmpty(ruleInfo.parameters[i].effectiveDate)) {
//						addRulePg.setEffectiveDate(
//								ruleInfo.parameters[i].effectiveDate, 0);
//					}
					// Added by Jane
					if (null != ruleInfo.parameters[i].residencyProofsParas
							&& ruleInfo.parameters[i].residencyProofsParas
									.size() > 0) {
						for (String para : ruleInfo.parameters[i].residencyProofsParas) {
							addRulePg.unSelectResidencyProofsParameter(para);
						}
					}
					
					if(null != ruleInfo.parameters[i].attributeInfos && ruleInfo.parameters[i].attributeInfos.size()>0){
						for(int j=0;j<ruleInfo.parameters[i].attributeInfos.size();j++){
							if(j>0){
								addRulePg.clickAddButton();
								ajax.waitLoading();
							}
							
							addRulePg.selectAttributeInfo(ruleInfo.parameters[i].attributeInfos.get(j)[0], ruleInfo.parameters[i].attributeInfos.get(j)[1],j);
						}
					}
					
				} else if ("Suspension/Revocation"
						.equals(ruleInfo.ruleCategory)) {
					addRulePg.selectSuspensionType(
							ruleInfo.parameters[i].suspensionType, i);
				} else {
					if (addRulePg.isEducationTypeExist()) {
						addRulePg.selectEducationType(
								ruleInfo.parameters[i].educationType, i);
						ajax.waitLoading();
					}
					//Lesley[20140324]Optional checkbox for Provide Education Rule
					if (addRulePg.isOptionalExist() && ruleInfo.parameters[i].isOptional) {
						addRulePg.selectOptional(i);
					}
					if (addRulePg.isStateExist(ruleInfo.parameters[i].matchState) && StringUtil.notEmpty(ruleInfo.parameters[i].matchState)){ //ruleInfo.parameters[i].matchState) {
						addRulePg.selectState(i, ruleInfo.parameters[i].matchState);
					}
					if (addRulePg.isLocationClassExist()) {
						addRulePg.selectLocationClass(
								ruleInfo.parameters[i].locationClass, i);
					}
					if (addRulePg.isCertificationTypeExist()) {
						addRulePg.selectCertificationType(
								ruleInfo.parameters[i].certificationType, i);
					}
					if (addRulePg.isPassNumExist()) {
						addRulePg.setPassNum(ruleInfo.parameters[i].passNum, i);
					}
					
					if(addRulePg.isDateOfBirthExist() && StringUtil.notEmpty(ruleInfo.parameters[i].dateOfBirth)){
						addRulePg.setDateOfBirth(ruleInfo.parameters[i].dateOfBirth, i);
					}
					
					if(addRulePg.isPromptExist() && StringUtil.notEmpty(ruleInfo.parameters[i].prompt)){
						addRulePg.setPrompt(ruleInfo.parameters[i].prompt, i);
					}
				}
				if (addRulePg.isWorkActionExist()
						&& !"".equals(ruleInfo.parameters[i].workAction)) {
					addRulePg.selectWorkAction(
							ruleInfo.parameters[i].workAction, i);
				}
				//Lesley[20140213]: handle the waiting period dropdown list.
				if (addRulePg.isWaitingPeriodExist() 
						&& !"".equals(ruleInfo.parameters[i].waitingPeriod)) {
					addRulePg.selectWaitingPeriod(ruleInfo.parameters[i].waitingPeriod);
				}
				/*
				 * Please keep this the last field to be set, for the date
				 * component overlay will cover some component under this field,
				 * if some action happens (like set text) on there element,
				 * sometimes the overlay will be clicked and wrong date will be
				 * set
				 */
				if (!"".equals(ruleInfo.parameters[i].effectiveDate)) {
					addRulePg.setEffectiveDate(
							ruleInfo.parameters[i].effectiveDate, i);
					browser.inputKey(KeyInput.get(KeyInput.TAB));
					browser.clickGuiObject(".id", "codebaseDropdown");
				}
			}
		}

		if (isCancel) {
			addRulePg.clickCancel();
		} else {
			addRulePg.clickOK();
		}
		ajax.waitLoading();
		browser.waitExists(rulePage, addRulePg);
	}

	public String editTextDisplayForProduct(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		this.editTextDisplayForProduct(textInfo, false);

		String id = textPage.getTextDisplayIdByText(textInfo.text);
		return id;
	}

	public void editTextDisplayForProduct(PrivilegeTextDisplay textInfo,
			boolean isCancel) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget textDisplayWidget = LicMgrPrivilegeEditTextDisplayWidget
				.getInstance();
		logger.info("Edit privilege text display - " + textInfo.id);

		if (!textDisplayWidget.exists()) {
			textPage.clickTextDisplayId(textInfo.id);
			ajax.waitLoading();
			textDisplayWidget.waitLoading();
		}
		textDisplayWidget.setEffectiveFromDate(textInfo.effectiveFromDate);
		textDisplayWidget.setEffectiveToDate(textInfo.effectiveToDate);
		textDisplayWidget.setText(textInfo.text);
		textDisplayWidget.selectDisplayType(textInfo.displayType);

		if (isCancel) {
			textDisplayWidget.clickCancel();
		} else {
			textDisplayWidget.clickOK();
		}
		ajax.waitLoading();
		browser.waitExists(textPage, textDisplayWidget);
	}

	public String editProductPricing(PricingInfo pricing,
			ILicMgrProductPricingPage pricingPage) {
		return this.editProductPricing(pricing, true, pricingPage);
	}

	/**
	 * Edit product including privilege, consumable,vehicle and supply in Edit
	 * HF Pricing Widget
	 * 
	 * @param pricing
	 *            - the new pricing info
	 * @param isClickOK
	 *            - true, click OK button to finish editing; false, click cancel
	 *            to abort the process of editing
	 * @param pricingPage
	 *            - the start page: LicMgrPrivilegePricingPage,
	 *            LicMgrConsumableProductPricingPage, LicMgrVehiclePricingPage
	 *            and LicMgrSupplyPricingPage
	 * @return
	 */
	public String editProductPricing(PricingInfo pricing, boolean isClickOK,
			ILicMgrProductPricingPage pricingPage) {
		LicMgrEditProductPricingWidget editPricingWidget = LicMgrEditProductPricingWidget
				.getInstance();
		LicMgrPrivilegePricingPage privilegePricingPage = LicMgrPrivilegePricingPage
				.getInstance();
		LicMgrConsumableProductPricingPage consumablePricingPage = LicMgrConsumableProductPricingPage
				.getInstance();
		LicMgrVehiclePricingPage vehiclePricingPage = LicMgrVehiclePricingPage
				.getInstance();
		LicMgrSupplyPricingPage supplyPricingPage = LicMgrSupplyPricingPage
				.getInstance();

		logger.info("Edit " + pricing.prodType + " pricing(ID=" + pricing.id
				+ ").");

		pricingPage.clickPricingID(pricing.id);
		ajax.waitLoading();
		editPricingWidget.waitLoading();

		editPricingWidget.editProductPricing(pricing);
		if (isClickOK) {
			editPricingWidget.clickOK();
		} else {
			editPricingWidget.clickCancel();
		}

		ajax.waitLoading();

		Object page = browser.waitExists(editPricingWidget,
				privilegePricingPage, consumablePricingPage,
				vehiclePricingPage, supplyPricingPage);
		String toReturn = "";
		if (editPricingWidget == page) {
			toReturn = editPricingWidget.getErrorMsg();
		} else {
			if (isClickOK && pricing.status.equals("Active")) {
				pricingPage.searchPricingRecords();
				toReturn = pricingPage.getPricingID(pricing);
			}
		}

		return toReturn;
	}

	/**
	 * The method used to goto privilege license year detail page by location
	 * class and license year to identify the unique year, start from license
	 * year page, ends at Edit privilege license year page.
	 * 
	 * @param locClass
	 *            - location class
	 * @param licenseYear
	 */
	public void gotoPrivilegeLicenseYearDetailPg(String locClass,
			String licenseYear) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		String id = licenseYearPg.getLicenseYearId(locClass, licenseYear);
		this.gotoPrivilegeLicenseYearDetailPg(id);
	}

	/**
	 * The method used to goto privilege license year detail page by location
	 * class and license year to identify the unique year, start from license
	 * year page, ends at Edit privilege license year page.
	 * 
	 * @param status
	 * @param locClass
	 *            - location class
	 * @param licenseYear
	 */
	public void gotoPrivilegeLicenseYearDetailPg(String status,
			String locClass, String licenseYear) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		String id = licenseYearPg.getLicenseYearId(status, locClass,
				licenseYear);
		this.gotoPrivilegeLicenseYearDetailPg(id);
	}

	/**
	 * search privilege license year info on license year page, start from
	 * privilege product details/license year page, ends at license year page
	 * 
	 * @param lyInfo
	 */
	public void searchPrivilegeLicenseYearByInfo(LicenseYear lyInfo) {
		this.searchPrivilegeLicenseYearByInfo(lyInfo.status, lyInfo.licYear,
				lyInfo.locationClass);
	}

	/**
	 * Search privilege license year record by status, license year and location
	 * class, etc.
	 * 
	 * @param status
	 * @param licenseYear
	 * @param locationClass
	 */
	public void searchPrivilegeLicenseYearByInfo(String status,
			String licenseYear, String locationClass) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		logger.info("Search privilege license year record by Status=" + status
				+ ", License Year=" + licenseYear + ", Location Class="
				+ locationClass);
		licenseYearPg.unSelectShowCurrentRecordsOnly();
		if (status == null || status.length() == 0) {
			licenseYearPg.selectStatus(ALL_STATUS);
		} else {
			licenseYearPg.selectStatus(status);
		}
		if (licenseYear == null || licenseYear.trim().length() == 0) {
			licenseYearPg.selectLicenseYear(0);
		} else {
			licenseYearPg.selectLicenseYear(licenseYear);
		}
		if (locationClass == null || locationClass.length() == 0) {
			licenseYearPg.selectLocationClass(0);
		} else {
			licenseYearPg.selectLocationClass(locationClass);
		}
		licenseYearPg.clickGo();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
	}

	/**
	 * Search privilege text display record by status and display type
	 * 
	 * @param status
	 * @param displayType
	 */
	public void searchPrivilegeTextDisplayByInfo(String status,
			String displayType) {
		LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();

		logger.info("Search privilege text display record by Status=" + status
				+ ", Display Type=" + displayType);
		textDisplayPage.uncheckShowCurrentRecordsOnly();
		if (status == null || status.length() == 0) {
			textDisplayPage.selectStatus(0);
		} else {
			textDisplayPage.selectStatus(status);
		}
		if (displayType == null || displayType.length() == 0) {
			textDisplayPage.selectDisplayType(0);
		} else {
			textDisplayPage.selectDisplayType(displayType);
		}
		textDisplayPage.clickGo();
		ajax.waitLoading();
		textDisplayPage.waitLoading();
	}

	public void gotoStoreChangeHistoryPage() {
		LicMgrStoreDetailsPage storeDetailPage = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreChangeHistoryPage changeHistoryPage = LicMgrStoreChangeHistoryPage
				.getInstance();

		logger.info("Go to store change history info page.");
		storeDetailPage.clickChangeHistory();
		ajax.waitLoading();
		changeHistoryPage.waitLoading();
	}

	/**
	 * make search on license year batch add page based on given LicenseYear
	 * Info.
	 * 
	 * @param ly
	 */
	public void searchPrivilegeLicenseYearOnBatchAddPg(LicenseYear ly) {
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage
				.getInstance();
		priLicYearBatchAddPg.makeSearch(ly);
	}

	/**
	 * Goto given license year detail page, start from license year page
	 * 
	 * @param licenseYearId
	 */
	public void gotoPrivilegeLicenseYearDetailPg(String licenseYearId) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget detailPg = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();

		logger.info("Goto Specific Privilege License Year Detail Page.");
		licenseYearPg.unSelectShowCurrentRecordsOnly();
		licenseYearPg.clickGo();
		ajax.waitLoading();

		licenseYearPg.clickLicenseYear(licenseYearId);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	public void gotoAddPrivLicenseYearWidgetFromLicenseYearList() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeAddLicYearWidget addYearWidget = LicMgrPrivilegeAddLicYearWidget.getInstance();
		logger.info("Goto add license license year widget from license year list page.");
		licenseYearPg.clickAddLicenseYear();
		ajax.waitLoading();
		addYearWidget.waitLoading();
	}
	
	public void gotoLicenseYearListFromAddPrivLicenseYearWidget() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeAddLicYearWidget addYearWidget = LicMgrPrivilegeAddLicYearWidget.getInstance();
		logger.info("Goto add license license year widget from license year list page.");
		addYearWidget.clickCancel();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
	}
	
	public void gotoLicenseYearListFromEditPrivLicenseYearWidget() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editYearWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		logger.info("Goto edit license license year widget from license year list page.");
		editYearWidget.clickCancel();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
	}
	
	/**
	 * This method used to go to business rule detail page in privilege detail
	 * page
	 * 
	 * @param ruleId
	 */
	public void gotoBusinessRuleDetailPageFromPrivilegeDetailPage(String ruleId) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		LicMgrPrivilegeEditRuleWidget editrule = LicMgrPrivilegeEditRuleWidget
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		logger.info("Go to business rule " + ruleId
				+ " detail page from privilege detail page...");

		if(!rulePage.exists()){
			privilegeDetailsPg.clickBusinessRuleTab();
			ajax.waitLoading();
			rulePage.waitLoading();
		}
		
		rulePage.clickRuleId(ruleId);
		ajax.waitLoading();
		editrule.waitLoading();
	}
	
	public void gotoLandownerTabFromPrivDetailsPg(){
		LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPriLandownerSubPage landOwnerPg = LicMgrPriLandownerSubPage.getInstance();
		logger.info("Go to landowner sub tab from privilege details page.");
		privDetailsPg.clickLandownerTab();
		ajax.waitLoading();
		landOwnerPg.waitLoading();
	}

	/**
	 * go to Customer Harvest Sub Tab from customer details page
	 */
	public void gotoCustomerHarvestSubTab() {
		gotoCustomerSubTabPage("Harvest");
	}

	/**
	 * Go to customer identifiers sub tab from customer details page
	 */
	public void gotoCustomerIdentifierSubTab() {
		gotoCustomerSubTabPage("Identifiers");
	}

	/**
	 * Go to customer sub-tab from customer details page
	 * 
	 * @param tabName
	 */
	public void gotoCustomerSubTabPage(String tabName) {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerAdressesPage custAddressPg = LicMgrCustomerAdressesPage
				.getInstance();
		LicMgrCustomerIdentifiersPage custIdentifier = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
				.getInstance();
		LicMgrCustomerCertificationPage custCertification = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrCustomerSuspensionPage custSuspension = LicMgrCustomerSuspensionPage
				.getInstance();
		LicMgrCustomerHarvestPage custHarvestPage = LicMgrCustomerHarvestPage
				.getInstance();
		LicMgrCustomerVehiclesPage custVehiclePage = LicMgrCustomerVehiclesPage
				.getInstance();
		LicMgrCustomerPrivilegePage custPrivilegePage = LicMgrCustomerPrivilegePage
				.getInstance();

		logger.info("Go to customer sub tab :'" + tabName + "'");
		if (tabName.equalsIgnoreCase("Addresses")) {
			custDetailsPg.clickAddressTab();
			ajax.waitLoading();
			custAddressPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Identifiers")) {
			custDetailsPg.clickIdentifiersTab();
			ajax.waitLoading();
			custIdentifier.waitLoading();
		} else if (tabName.equalsIgnoreCase("Education")) {
			custDetailsPg.clickEducationTab();
			ajax.waitLoading();
			custEducation.waitLoading();
		} else if (tabName.equalsIgnoreCase("Certifications")) {
			custDetailsPg.clickCertificationsTab();
			ajax.waitLoading();
			custCertification.waitLoading();
		} else if (tabName.equalsIgnoreCase("Suspensions")) {
			custDetailsPg.clickSuspensionsTab();
			ajax.waitLoading();
			custSuspension.waitLoading();
		} else if (tabName.equalsIgnoreCase("Harvest")) {
			custDetailsPg.clickHarvestTab();
			ajax.waitLoading();
			custHarvestPage.waitLoading();
		} else if (tabName.equalsIgnoreCase("Vehicles")) {
			custDetailsPg.clickVehiclesTab();
			ajax.waitLoading();
			custVehiclePage.waitLoading();
		} else if (tabName.equalsIgnoreCase("Privileges")) {
			custDetailsPg.clickPrivilegesTab();
			ajax.waitLoading();
			custPrivilegePage.waitLoading();
		} else {
			throw new ErrorOnDataException("Unknown tab name:" + tabName);
		}
	}

	/**
	 * Goto Batch Edit License Year page start from privileges List page
	 */
	public void gotoBatchEditLicenseYearPg() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage
				.getInstance();

		logger
				.info("Goto Batch Edit License Year page from privileges List page");
		if (!privilegeListPage.exists()) {
			this.gotoPrivilegeSearchListPageFromTopMenu();
		}
		privilegeListPage.clickBatchEditLicenseYearButton();
		batchEditLicenseYearPg.waitLoading();
	}

	/**
	 * Activate or Inactivate privilege from privilege list page and the end
	 * page is the same as the starting page
	 * 
	 * @param privilegeCode
	 * @param operation
	 */
	public void activateOrInactivatePrivilege(String privilegeCode,
			String operation) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info(operation + " Privilege");
//		this.gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);
		this.gotoPrivilegeDetailsPageFromProductListPage(privilegeCode, operation.equalsIgnoreCase("InActivate")); //Lesley[20140319]: update due to Inactive status is not selected by default

		if (operation.equalsIgnoreCase("Activate")) {
			privilegeDetailsPg.selectPrivilegeStatus("Active");
		} else if (operation.equalsIgnoreCase("InActivate")) {
			privilegeDetailsPg.selectPrivilegeStatus("Inactive");
		}
		privilegeDetailsPg.clickOk();
		privilegePage.waitLoading();
	}

	/**
	 * Activate or Inactivate privilege license year from privilege list page
	 * and the end page is privilege detail page
	 * 
	 * @param privilegeCode
	 * @param licenseYearID
	 * @param operation
	 */
	public void activateOrInactivatePrivilegeLicenseYear(String privilegeCode,
			String licenseYearID, String operation) {
		LicMgrPrivilegeEditLicenseYearWidget privilegeEditLicenseYearPg = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info(operation + " Privilege License Year");
		this.gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);
		this.gotoPrivilegeLicenseYearPage();
		this.gotoPrivilegeLicenseYearDetailPg(licenseYearID);

		if (operation.equalsIgnoreCase("Activate")) {
			privilegeEditLicenseYearPg.selectStatus("Active");
		} else if (operation.equalsIgnoreCase("InActivate")) {
			privilegeEditLicenseYearPg.selectStatus("Inactive");
		}

		privilegeEditLicenseYearPg.clickOK();
		privilegeDetailsPg.waitLoading();
	}

	/**
	 * Activate or Inactivate privilege product Print Document. start and end at
	 * Print Documents page
	 * 
	 * @param productCode
	 * @param licenseYearID
	 * @param operation
	 */
	public void activateOrInactivatePrivilegePrintDocument(
			String printDocumentID, String operation) {
		LicMgrPrivilegePrintDocumentsPage privilegeEditPrintDocWidget = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		this.gotoEditPrintDocumentWidget(printDocumentID);

		if (operation.equalsIgnoreCase("Activate")) {
			privilegeEditPrintDocWidget.selectStatus("Active");
		} else if (operation.equalsIgnoreCase("InActivate")) {
			privilegeEditPrintDocWidget.selectStatus("Inactive");
		}

		privilegeEditPrintDocWidget.clickOk();
		privilegeEditPrintDocWidget.waitLoading();
	}

	public void gotoSupplyQuestionPgFromTopMenu(String supplyCode) {
		this.gotoSupplySearchListPageFromTopMenu();
		this.gotoSupplyDetailFromListPage(supplyCode);
		this.gotoSupplyQuestionPgFromDetailPg();
	}

	public void gotoSupplyContractorFeesPgFromTopMenu(String supplyCode) {
		LicMgrSupplyProductDetailsPage detailsPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrSupplyProductContractFeesPage contractFeesPage = LicMgrSupplyProductContractFeesPage
				.getInstance();

		this.gotoSupplySearchListPageFromTopMenu();
		this.gotoSupplyDetailFromListPage(supplyCode);

		logger.info("Go to Supply Contractor Fee page from detail page.");
		detailsPg.clickContractorFeesTab();
		contractFeesPage.waitLoading();
	}

	public void gotoSupplyDetailFromListPage(String supplyCode) {
		LicMgrSuppliesListPage supplyListPage = LicMgrSuppliesListPage
				.getInstance();
		LicMgrSupplyProductDetailsPage detailPg = LicMgrSupplyProductDetailsPage
				.getInstance();

		logger.info("Goto Supply Detail Page.");

		supplyListPage.clickSupplyCode(supplyCode);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	public void gotoSupplyQuestionPgFromDetailPg() {
		LicMgrSupplyProductDetailsPage detailsPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrSupplyQuestionPage questionPg = LicMgrSupplyQuestionPage
				.getInstance();

		logger.info("Go to Supply question page from detail page.");
		detailsPg.clickQuestionsTab();
		questionPg.waitLoading();
	}

	/**
	 * This method is used to go to add new vehicle product page. Start from
	 * vehicle list page, end at add new vehicle page
	 */
	public void gotoAddVehicleProductPgFromVehicleListPg() {
		LicMgrVehiclesListPage vehicleListPg = LicMgrVehiclesListPage
				.getInstance();
		LicMgrCreateNewVehiclePage createNewvehiclePg = LicMgrCreateNewVehiclePage
				.getInstance();

		logger
				.info("Go to add new vehicle product page from vehicle list page.");
		vehicleListPg.clickAddVehicleProduct();
		createNewvehiclePg.waitLoading();
	}

	public void gotoPrivilegeListPgFromGivePage(Object startPage) {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage
				.getInstance();
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();

		logger.info("Go to privilege list page from " + startPage);
		if (startPage == batchEditLicenseYearPg) {
			batchEditLicenseYearPg.clickPrivilegesTab();
			ajax.waitLoading();
		} else if (startPage == licenseYearPg) {
			licenseYearPg.clickPrivilegesTab();
			ajax.waitLoading();
		} else if (startPage == printDocSubTab) {
			printDocSubTab.clickPrivilegesTab();
			ajax.waitLoading();
		} else
			throw new PageNotFoundException(startPage + " can't be found.");

		privilegeListPage.waitLoading();
	}

	/**
	 * assgin Product To Store In Store Details Page
	 * 
	 * @param productCode
	 * @param string
	 * 
	 * @return assignment id
	 */
	public String assginOrUnAssignProductToStoreInStoreDetailsPage(
			boolean isAssign, String proCode, String productType) {
		LicMgrStoreProductAssignmentsPage storeProductAssignmentPg = LicMgrStoreProductAssignmentsPage
				.getInstance();
		String id = "";
		logger.info("assgin Product To Store In Store Details Page");
		storeProductAssignmentPg
				.uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly();
		storeProductAssignmentPg.waitLoading();
		if (productType.matches("(?i)privilege( Products)?")) {
			storeProductAssignmentPg.selectPrivilegeProducts();
		} else if (productType.matches("(?i)vehicle( Products)?")) {
			storeProductAssignmentPg.selectVehicleProducts();
		} else if (productType.matches("(?i)consumable( Products)?")) {
			storeProductAssignmentPg.selectConsumableProducts();
		} else if (productType.matches("(?i)Supply( Products)?")) {
			storeProductAssignmentPg.selectSupplyProducts();
		} else {
			throw new ErrorOnDataException("Product type is unknown");
		}
		storeProductAssignmentPg.waitLoading();
		storeProductAssignmentPg.clickGo();
		ajax.waitLoading();
		storeProductAssignmentPg.waitLoading();
		storeProductAssignmentPg.selectProductRecord(proCode);
		if (isAssign) {
			storeProductAssignmentPg.clickAssign();
			ajax.waitLoading();
			id = storeProductAssignmentPg
					.getProductStoreAssignmentIdByCode(proCode);
		} else {
			id = storeProductAssignmentPg
					.getProductStoreAssignmentIdByCode(proCode);
			storeProductAssignmentPg.clickUnassign();
			ajax.waitLoading();
		}
		return id;
	}

	/**
	 * Go to system configuration page from top menu
	 */
	public void gotoSysConfPgFromTopMenu() {
		LicMgrSystemConfigurationPage sysConfigPg = LicMgrSystemConfigurationPage
				.getInstance();
		LicMgrProductConfigurationPage prodConfigPg = LicMgrProductConfigurationPage
				.getInstance();

		logger.info("Go to System Configuration Page From Top Menu.");

		this.gotoProdConfPgFromTopMenu();
		prodConfigPg.clickSystemConfiguration();
		ajax.waitLoading();
		sysConfigPg.waitLoading();
	}

	/**
	 * Go to privilege inventory type page from top menu
	 */
	public void gotoPrivilegeInventoryTypePgFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();

		logger.info("Go to privilege inventory page from top menue.");
		String adminValue = topMenuPg.getAdminValue();
		if (!adminValue.matches("(Privilege|Licence) Inventory")) { // Lesley[20130828]: For SK contract, the option is Licence Inventory; for MS, it is Privilege Inventory
			if (topMenuPg.isAdminOptionExist("Privilege Inventory")) {
				topMenuPg.selectAdminOptions("Privilege Inventory");
			} else if (topMenuPg.isAdminOptionExist("Licence Inventory")) {
				topMenuPg.selectAdminOptions("Licence Inventory");
			}
		} else {
			topMenuPg.clickAdmin();
		}

		inventoryTypePg.waitLoading();
	}

	/**
	 * Go to create privilege inventory type page, start from inventory type
	 * page, end at create privilege inventory type page.
	 */
	public void gotoAddPrivilegeInventoryTypeWidge() {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrAddInventoryTypeWidget addInventoryTypePg = LicMgrAddInventoryTypeWidget
				.getInstance();

		inventoryTypePg.clickAddInventoryType();
		ajax.waitLoading();
		addInventoryTypePg.waitLoading();
	}

	/**
	 * This method is used to go to inventory type license year page, start from
	 * inventory type page, end at inventory type license year page
	 */
	public void gotoInventoryTypeLicenseYearPageFromInventoryTypePage() {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();

		logger
				.info("Go to inventory type License year page from inventory type page.");
		inventoryTypePg.clickInvTypeLicenseYear();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
	}

	/**
	 * This method is used to go to inventory type license year page, start from
	 * privilege inventory page, end at inventory type license year page
	 */
	public void gotoInventoryTypeLicenseYearPageFromPrivilegeInventoryPg() {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();

		logger
				.info("Go to inventory type license year page from privilege inventory page.");
		privilegeInventoryPg.clickInventoryTypeLicenseYears();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
	}

	/**
	 * This method is used to goto privilege inventory page, start from
	 * inventory type page, end at privilege inventory page
	 */
	public void gotoPrivilegeInventoryPageFromInventoryTypePage() {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();

		logger.info("Go to privilege inventory page from inventory type page.");
		inventoryTypePg.clickPrivilegeInventory();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to got privilege inventory page, start from inventory
	 * type license year page end at privilege inventory page
	 */
	public void gotoPrivilegeInventoryPageFromInventoryTypeLicenseYearPage() {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();

		logger
				.info("Go to privilege inventory page from inventory type license year page.");
		invTypeLicenseYearPg.clickPrivilegeInventory();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to goto create inventory page, start from privilege
	 * inventory page, end at create inventory page
	 */
	public void gotoCreatePriInventoryPageFromPrivilegeInventoryPg() {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrCreateInventoryWidget createInventoryPg = LicMgrCreateInventoryWidget
				.getInstance();

		logger
				.info("Go to create inventory page from privilege inventory page.");
		privilegeInventoryPg.clickCreateInventory();
		ajax.waitLoading();
		createInventoryPg.waitLoading();
	}

	/**
	 * This method is used to add privilege inventory, start from privilege
	 * inventory page, end at privilege inventory page
	 * 
	 * @param priInventory
	 */
	public void addPrivilegeInventory(PrivilegeInventory priInventory) {
		LicMgrCreateInventoryWidget createInventoryPg = LicMgrCreateInventoryWidget
				.getInstance();
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();

		logger.info("Add privilege inventory info.");
		this.gotoCreatePriInventoryPageFromPrivilegeInventoryPg();
		createInventoryPg.setPrivilegeInventory(priInventory);
		createInventoryPg.clickOK();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to goto allocate privilege inventory page, start at
	 * privilege inventory page, end at allocate privilege inventory page
	 */
	public void gotoAllocatePrivilegeInventoryPgFromPrivilegeInventoryPg() {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrAllocatePrivilegeInventoryWidget allocatePriInventoryPg = LicMgrAllocatePrivilegeInventoryWidget
				.getInstance();

		logger
				.info("Go to allocate privilege inventory page from privilege inventory page.");
		privilegeInventoryPg.clickAllocatePrivilegeInventory();
		ajax.waitLoading();
		allocatePriInventoryPg.waitLoading();
	}

	/**
	 * This method is used to allocate privilege inventory, start from privilege
	 * inventory page, end at privilege inventory page
	 * 
	 * @param priInvAllocation
	 */
	public void allocatePrivilegeInventory(
			PrivilegeInventoryAllocation priInvAllocation) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrAllocatePrivilegeInventoryWidget allocatePriInventoryPg = LicMgrAllocatePrivilegeInventoryWidget
				.getInstance();

		this.gotoAllocatePrivilegeInventoryPgFromPrivilegeInventoryPg();
		allocatePriInventoryPg
				.setPrivilegeInventoryAllocationInfo(priInvAllocation);
		allocatePriInventoryPg.clickOK();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to search privilege inventory info by search
	 * criteria, start from privilege inventory page, end at privilege inventory
	 * page
	 * 
	 * @param priInventory
	 */
	public void searchPrivilegeInventory(PrivilegeInventory priInventory) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();

		logger.info("Search privielge inventory info.");
		privilegeInventoryPg.setSearchCriteria(priInventory);
		privilegeInventoryPg.clickGo();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to goto reallocate inventory page, start from
	 * privilege inventory page, end at reallocate inventory page
	 * 
	 * @param inventoryNum
	 */
	public void gotoReallocateInventoryPageFromPrivilegeInventoryPg(
			String inventoryNum) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrReallocateInventoryWidget reallocateInventoryPg = LicMgrReallocateInventoryWidget
				.getInstance();

		logger
				.info("Goto reallocate inventory page from privilege inventory page.");
		privilegeInventoryPg.selectPriInventoryItemCheckBox(inventoryNum);
		privilegeInventoryPg.clickReallocateInventory();
		ajax.waitLoading();
		reallocateInventoryPg.waitLoading();
	}

	/**
	 * This method is used to reallocate privilege inventory item, start from
	 * privilege inventory page, end at privilege inventory page
	 * 
	 * @param inventoryNum
	 * @param priInvAllocation
	 */
	public void reallocatePrivilegeInventoryItem(String inventoryNum,
			PrivilegeInventoryAllocation priInvAllocation) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrReallocateInventoryWidget reallocateInventoryPg = LicMgrReallocateInventoryWidget
				.getInstance();

		logger.info("Reallocate privilege invetory item.");
		this.gotoReallocateInventoryPageFromPrivilegeInventoryPg(inventoryNum);
		reallocateInventoryPg.setReallocateInventoryInfo(
				priInvAllocation.vendorNum, priInvAllocation.vendorName,
				priInvAllocation.agentID, priInvAllocation.agentName);
		reallocateInventoryPg.clickOK();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	public void withdrawFirstPrivilegeInventoryItem() {
		withdrawPrivilegeInventoryItem(null, true);
	}

	/**
	 * This method is used to withdraw privilege inventory, start from privilege
	 * inventory page, end at privilege inventory page
	 * 
	 * @param inventoryNum
	 * @param isClickOK
	 * @return
	 */
	public String withdrawPrivilegeInventoryItem(String inventoryNum,
			boolean isClickOK) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Withdraw privilege inventory item.");
		if (!StringUtil.isEmpty(inventoryNum)) {
			privilegeInventoryPg.selectPriInventoryItemCheckBox(inventoryNum);
		} else {
			privilegeInventoryPg.selectFirstPriInventoryItemCheckBox();
		}
		privilegeInventoryPg.clickWithdrawInventory();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
		String message = confirmDialogWidget.getMessage();
		if (isClickOK) {
			confirmDialogWidget.clickOK();
		} else {
			confirmDialogWidget.clickCancel();
		}

		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();

		return message;
	}

	public void returnFirstPrivilegeInventoryItem() {
		returnPrivilegeInventoryItem(null, true);
	}

	/**
	 * This method is used to return unused privilege inventory, start at
	 * privilege inventory page, end at privilege inventory page
	 * 
	 * @param inventoryNum
	 * @param isClickOK
	 * @return
	 */
	public String returnPrivilegeInventoryItem(String inventoryNum,
			boolean isClickOK) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Return unused privielge inventory.");
		if (!StringUtil.isEmpty(inventoryNum)) {
			privilegeInventoryPg.selectPriInventoryItemCheckBox(inventoryNum);
		} else {
			privilegeInventoryPg.selectFirstPriInventoryItemCheckBox();
		}
		privilegeInventoryPg.clickMarkInventoryAsReturned();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
		String message = confirmDialogWidget.getMessage();
		if (isClickOK) {
			confirmDialogWidget.clickOK();
		} else {
			confirmDialogWidget.clickCancel();
		}

		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
		return message;
	}

	/**
	 * This method is used to go to privilege inventory history page, start from
	 * privilege inventory page, end at privilege inventory history page
	 * 
	 * @param inventoryNum
	 */
	public void gotoPrivilegeInventoryChangeHistoryPgFromPrivilegeInventoryPg(
			String inventoryNum) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrPrivilegeInventoryChangeHistoryPage priInventoryChangeHistoryPg = LicMgrPrivilegeInventoryChangeHistoryPage
				.getInstance();

		logger
				.info("Go to privilege inventory history page. Inventory number = "
						+ inventoryNum);
		privilegeInventoryPg.clickInventoryNumber(inventoryNum);
		ajax.waitLoading();
		priInventoryChangeHistoryPg.waitLoading();
	}

	/**
	 * This method is used to go to reinstate inventory page, start from
	 * privilege inventory page, end at reinstate inventory page
	 * 
	 * @param inventoryNum
	 */
	public void gotoReinstateInventoryPgFromPrivilegeInventoryPg(
			String inventoryNum) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrReinstateInventoryWidget reinstateInventoryPg = LicMgrReinstateInventoryWidget
				.getInstance();

		logger
				.info("Go to reinstate inventory page from privilege inventory page.");
		privilegeInventoryPg.selectPriInventoryItemCheckBox(inventoryNum);
		privilegeInventoryPg.clickReinstateInventory();
		ajax.waitLoading();
		reinstateInventoryPg.waitLoading();
	}

	/**
	 * This method is used to reinstate inventory, start from privilege
	 * inventory page, end at privilege inventory page
	 * 
	 * @param inventoryNum
	 * @param priInvAllocation
	 */
	public void reinstatePrivilegeInventoryItem(String inventoryNum,
			PrivilegeInventoryAllocation priInvAllocation) {
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();
		LicMgrReinstateInventoryWidget reinstateInventoryPg = LicMgrReinstateInventoryWidget
				.getInstance();

		logger.info("Reinstate privilege inventory.");
		this.gotoReinstateInventoryPgFromPrivilegeInventoryPg(inventoryNum);
		reinstateInventoryPg.setReinstateInventoryInfo(
				priInvAllocation.vendorNum, priInvAllocation.vendorName,
				priInvAllocation.agentID, priInvAllocation.agentName);
		reinstateInventoryPg.clickOK();
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to goto add inventory type license year page, start
	 * from inventory type page, end at add inventory type license year page
	 */
	public void gotoAddInvTypeLicenseYearPageFromInvTypeLicenseYearPage() {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrAddInvTypeLicenseYearWidget addInvTypeLicenseYearPg = LicMgrAddInvTypeLicenseYearWidget
				.getInstance();

		logger
				.info("Go to add inventory type license year page from inventory type license year page.");
		invTypeLicenseYearPg.clickAddInventoryTypeLicenseYear();
		ajax.waitLoading();
		addInvTypeLicenseYearPg.waitLoading();
	}

	/**
	 * This method is used to add inventory type license year, start from
	 * inventory type page, end at inventory type page
	 * 
	 * @param invTypeLicenseYear
	 */
	public String addInvTypeLicenseYear(
			InventoryTypeLicenseYear invTypeLicenseYear) {
		LicMgrAddInvTypeLicenseYearWidget addInvTypeLicenseYearPg = LicMgrAddInvTypeLicenseYearWidget
				.getInstance();
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();

		logger.info("Add inventory type license year.");
		this.gotoAddInvTypeLicenseYearPageFromInvTypeLicenseYearPage();
		addInvTypeLicenseYearPg.setInvTypeLicenseYearInfo(invTypeLicenseYear);
		addInvTypeLicenseYearPg.clickOK();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
		String id = invTypeLicenseYearPg
				.getInvTypeLicenseYearID(invTypeLicenseYear);
		return id;
	}

	/**
	 * Go to edit inventory type license year page from inventory license year
	 * list page
	 * 
	 * @param invTypeLicYearID
	 */
	public void gotoEditInvTypeLicenseYearPageFromInvTypeLicYearList(
			String invTypeLicYearID) {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrEditInvTypeLicenseYearWidget eidtInvTypeLicenseYearPg = LicMgrEditInvTypeLicenseYearWidget
				.getInstance();

		logger
				.info("Go to Edit Inventory Type License Year page from Inventory Type License Year List.");
		invTypeLicenseYearPg.clickInvTypeLicenseYearID(invTypeLicYearID);
		ajax.waitLoading();
		eidtInvTypeLicenseYearPg.waitLoading();
	}

	/**
	 * Go to inventory type license year page, start from edit inventory type
	 * license year page, end at inventory type license year list page
	 */
	public void gotoInvTypeLicenseYearPageFromEidtInvTypeLicenseYearPage() {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrEditInvTypeLicenseYearWidget eidtInvTypeLicenseYearPg = LicMgrEditInvTypeLicenseYearWidget
				.getInstance();

		logger
				.info("Go to inventory type license year page from edit inventory type license year page.");
		eidtInvTypeLicenseYearPg.clickCancel();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
	}

	/**
	 * This method is used to goto inventory type license year change history
	 * page, start from inventory type license year page, end at inventory type
	 * license year change history page
	 * 
	 * @param invTypeLicYearID
	 */
	public void gotoInvTypeLicenseYearChangeHistoryPageFromInvTypeLicenseYearPage(
			String invTypeLicYearID) {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrInvTypeLicenseYearChangeHistoryPage invTypeLicYearChangeHistoryPg = LicMgrInvTypeLicenseYearChangeHistoryPage
				.getInstance();

		logger.info("Go to inventory type license year change history page.");
		invTypeLicenseYearPg.clickChangeHistory(invTypeLicYearID);
		ajax.waitLoading();
		invTypeLicYearChangeHistoryPg.waitLoading();
	}

	/**
	 * Go to privilege inventory page through click view inventory in inventory
	 * license year page start from inventory license year page, end at
	 * privilege inventory page
	 * 
	 * @param invTypeLicYearID
	 */
	public void gotoPrivilegeInventoryPgThroughClickViewInventory(
			String invTypeLicYearID) {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage
				.getInstance();

		logger
				.info("Go to privilege inventory page through click view inventory.");
		invTypeLicenseYearPg.clickViewInventory(invTypeLicYearID);
		ajax.waitLoading();
		privilegeInventoryPg.waitLoading();
	}

	/**
	 * This method is used to goto inventory type license year page, start from
	 * inventory type license year change history page, end at inventory type
	 * license year page
	 */
	public void gotoInvTypeLicYearPgFromInvTypeLicYearChangeHistoryPg() {
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		LicMgrInvTypeLicenseYearChangeHistoryPage invTypeLicYearChangeHistoryPg = LicMgrInvTypeLicenseYearChangeHistoryPage
				.getInstance();

		invTypeLicYearChangeHistoryPg.clickReturn();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
	}

	/**
	 * This method is used to edit inventory type license year info, start from
	 * inventory type license year list page end at inventory type license year
	 * list page.
	 * 
	 * @param invTypeLicenseYear
	 */
	public void editInvTypeLicenseYearInfo(
			InventoryTypeLicenseYear invTypeLicenseYear) {
		LicMgrEditInvTypeLicenseYearWidget eidtInvTypeLicenseYearPg = LicMgrEditInvTypeLicenseYearWidget
				.getInstance();
		LicMgrInventoryTypeLicenseYearPage invTypeLicenseYearPg = LicMgrInventoryTypeLicenseYearPage
				.getInstance();
		logger.info("Edit inventory type license year info.");

		this
				.gotoEditInvTypeLicenseYearPageFromInvTypeLicYearList(invTypeLicenseYear.id);
		eidtInvTypeLicenseYearPg.setInvTypeLicenseYearInfo(invTypeLicenseYear);
		eidtInvTypeLicenseYearPg.clickOK();
		ajax.waitLoading();
		invTypeLicenseYearPg.waitLoading();
	}

	/**
	 * Add privilege inventory type info, start from inventory type page, end at
	 * inventory type page
	 * 
	 * @param inventoryType
	 */
	public void addPrivilegeInventoryTypeInfo(String inventoryType) {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrAddInventoryTypeWidget addInventoryTypeWidget = LicMgrAddInventoryTypeWidget
				.getInstance();
		logger.info("Add Privilege Inventory type info.");

		this.gotoAddPrivilegeInventoryTypeWidge();
		addInventoryTypeWidget.setInventoryType(inventoryType);
		addInventoryTypeWidget.clickOK();
		ajax.waitLoading();
		inventoryTypePg.waitLoading();
	}

	/**
	 * Deactivate privilege inventory type info by inventory type ID
	 * 
	 * @param inventoryTypeID
	 */
	public String deactivatePrivilegeInventoryType(String inventoryTypeID) {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		String confirmValue = "";
		logger
				.info("Deactivate privilege inventory type info by inventory type ID.");

		inventoryTypePg.clickDeactivateButton(inventoryTypeID);
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmDialogWidget, inventoryTypePg);
		if (confirmDialogWidget == pages) {
			confirmValue = confirmDialogWidget.getMessage();
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			inventoryTypePg.waitLoading();
		}

		return confirmValue;
	}

	/**
	 * Go to assign/unassign privilege product to/from inventory type page,
	 * start from inventory type page, end at assign/unassign privilege product
	 * to/from inventory type page
	 * 
	 * @param inventoryTypeID
	 */
	public void gotoAssignUnAssignInventoryTypePage(String inventoryTypeID) {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrAssignUnassignInventoryTypeWidget assignUnassignInventoryPg = LicMgrAssignUnassignInventoryTypeWidget
				.getInstance();
		logger
				.info("Go to assign/unassign privilege product to/from inventory type page.");

		inventoryTypePg.clickSelectPrivilegeProduct(inventoryTypeID);
		ajax.waitLoading();
		assignUnassignInventoryPg.waitLoading();
	}

	/**
	 * Go to inventory type page from assign/unassign inventory type page, start
	 * from assign/unassign inventory type page, end at inventory type page
	 */
	public void gotoInventoryTypePgFromAssignUnassignInventoryTypePage() {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		LicMgrAssignUnassignInventoryTypeWidget assignUnassignInventoryPg = LicMgrAssignUnassignInventoryTypeWidget
				.getInstance();

		logger
				.info("Go to inventory type page, from assign/unassign inventory page.");
		assignUnassignInventoryPg.clickOK();
		ajax.waitLoading();
		inventoryTypePg.waitLoading();
	}

	/**
	 * Assign privilege product to inventory type, start from inventory type
	 * page, end at inventory type page
	 * 
	 * @param privilegeInfos
	 */
	public void assignPrivilegeProductToInventoryType(String inventoryTypeID,
			List<String> privilegeInfos) {
		LicMgrAssignUnassignInventoryTypeWidget assignUnassignInventoryPg = LicMgrAssignUnassignInventoryTypeWidget
				.getInstance();
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();

		logger.info("Assign Privilege product to inventory type.");
		this.gotoAssignUnAssignInventoryTypePage(inventoryTypeID);
		for (int i = 0; i < privilegeInfos.size(); i++) {
			assignUnassignInventoryPg
					.selectPrivielgeProductCheckBox(privilegeInfos.get(i));
		}
		assignUnassignInventoryPg.clickOK();
		ajax.waitLoading();
		inventoryTypePg.waitLoading();
	}

	/**
	 * Unassign privilege product to Inventory type, start from inventory type
	 * page, end at inventory type page
	 * 
	 * @param inventoryTypeID
	 * @param privilegeInfos
	 */
	public void unassignPrivilegeProductToInventoryType(String inventoryTypeID,
			List<String> privilegeInfos) {
		LicMgrAssignUnassignInventoryTypeWidget assignUnassignInventoryPg = LicMgrAssignUnassignInventoryTypeWidget
				.getInstance();
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();

		logger.info("unAssign Privilege product to inventory type.");
		this.gotoAssignUnAssignInventoryTypePage(inventoryTypeID);
		for (int i = 0; i < privilegeInfos.size(); i++) {
			assignUnassignInventoryPg
					.unSelectPrivilegeProductCheckBox(privilegeInfos.get(i));
		}
		assignUnassignInventoryPg.clickOK();
		ajax.waitLoading();
		inventoryTypePg.waitLoading();
	}

	/**
	 * Activate privilege inventory type info by inventory type ID
	 * 
	 * @param inventoryTypeID
	 */
	public void activatePrivilegeInventoryType(String inventoryTypeID) {
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage
				.getInstance();
		logger
				.info("Activate privilege inventory type info by inventory type ID.");

		inventoryTypePg.clickActivateButton(inventoryTypeID);
		ajax.waitLoading();
		inventoryTypePg.waitLoading();
	}

	/**
	 * Go to vendor status reason page from system configuration page.
	 */
	public void gotoVendorStatusReasonPgFromSysConfigPg() {
		LicMgrSystemConfigurationPage systemConfigPage = LicMgrSystemConfigurationPage
				.getInstance();
		LicMgrVendorStatusReasonMgtConfigurationPage vendorStatusReasonPg = LicMgrVendorStatusReasonMgtConfigurationPage
				.getInstance();

		logger
				.info("Go to vendor status reason page, from system configuration page.");
		systemConfigPage.clickVendorStatusReasonMgtTab();
		ajax.waitLoading();
		vendorStatusReasonPg.waitLoading();
	}

	/**
	 * This method is used to add vendor status reason
	 * 
	 * @param status
	 * @param reason
	 * @return status reson ID or error message
	 */
	public String addVendorStatusReason(String status, String reason) {
		LicMgrVendorStatusReasonMgtConfigurationPage vendorStatusReasonPg = LicMgrVendorStatusReasonMgtConfigurationPage
				.getInstance();
		LicMgrAddVendorStatusReasonWidget addVendorStatusWidget = LicMgrAddVendorStatusReasonWidget
				.getInstance();
		String value = "";

		logger.info("Add vendor status reason.");
		vendorStatusReasonPg.clickAdd();
		ajax.waitLoading();
		addVendorStatusWidget.waitLoading();
		addVendorStatusWidget.setVendorStatusReason(status, reason);
		addVendorStatusWidget.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(vendorStatusReasonPg,
				addVendorStatusWidget);
		if (pages == addVendorStatusWidget) {
			value = addVendorStatusWidget.getErrorMessage();
		}
		if (pages == vendorStatusReasonPg) {
			this.gotoVendorStatusReasonPgFromSysConfigPg();
			value = vendorStatusReasonPg.getStatusReasonID(status, reason);
		}
		return value;
	}

	/**
	 * Go to Bond Issuers page from system configuration page
	 */
	public void gotoBondIssuersPg() {
		LicMgrSystemConfigurationPage sysConfigPg = LicMgrSystemConfigurationPage
				.getInstance();
		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();

		logger.info("Go to Bond Issuers page from system configuration page.");

		sysConfigPg.clickBondIssuersTab();
		ajax.waitLoading();
		bondIssuersPg.waitLoading();
	}

	/**
	 * Go to Bond Issuers page from details page
	 */
	public void gotoBondIssuersPgFromDetailsPg() {
		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();
		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();

		logger.info("Go to Bond Issuers page from details page.");

		detailsPg.clickOK();
		ajax.waitLoading();
		bondIssuersPg.waitLoading();
	}

	/**
	 * Add a new bond issuer
	 */
	public void addBondIssuer(LicMgrBondIssuerInfo bondIssuerInfo) {
		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();
		LicMgrAddBondIssuerWidget addBondIssuerPage = LicMgrAddBondIssuerWidget
				.getInstance();

		logger.info("Add an bond issuer.The business name is:"
				+ bondIssuerInfo.businessNm);

		bondIssuersPg.clickAdd();
		ajax.waitLoading();
		addBondIssuerPage.waitLoading();
		addBondIssuerPage.addNewBondIssuer(bondIssuerInfo);
		addBondIssuerPage.clickOK();
		ajax.waitLoading();
		bondIssuersPg.waitLoading();
	}

	/**
	 * View a bond issuer
	 */
	public void gotoBondIssuerDetailsPage(String businessNm) {

		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();
		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();

		logger.info("Go to bond issuer " + businessNm + " detail page.");

		bondIssuersPg.clickBusinessNmLink(businessNm);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

	/**
	 * View a bond issuer from change info page
	 */
	public void gotoBondIssuerDetailsPageFromChangeInfoPg() {

		LicMgrBondIssuersChangeHistoryPage changeHistoryPg = LicMgrBondIssuersChangeHistoryPage
				.getInstance();
		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();

		logger.info("Go to bond issuer details page from change info page.");

		changeHistoryPg.clickReturntoBondIssuerDetails();
		detailsPg.waitLoading();

	}

	/**
	 * View the changed history of the bond issuer.
	 */
	public void viewBondIssuerChangeHistory(String businessNm) {

		LicMgrBondIssuerDetailsPage detailPg = LicMgrBondIssuerDetailsPage
				.getInstance();
		LicMgrBondIssuersChangeHistoryPage changeHistoryPg = LicMgrBondIssuersChangeHistoryPage
				.getInstance();

		logger.info("View the changed history of the bond issuer.");

		detailPg.clickChangeHistory();
		changeHistoryPg.waitLoading();
	}

	/**
	 * Change the details of the bond issuer.
	 */
	public void changeBondIssuerDetails(LicMgrBondIssuerInfo bondIssuerInfo) {

		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();
		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();

		logger.info("Change the details of the bond issuer");

		if (!"".equals(bondIssuerInfo.businessNm)) {

			logger.info("Change Bussiness Name. The new bussiness name is:"
					+ bondIssuerInfo.businessNm);
			detailsPg.setBusinessNm(bondIssuerInfo.businessNm);
		}
		if (!"".equals(bondIssuerInfo.cityOrTown)) {

			logger.info("Change City/Town " + bondIssuerInfo.cityOrTown);
			detailsPg.setCityOrTown(bondIssuerInfo.cityOrTown);
		}

		if (!"".equals(bondIssuerInfo.contactAddress)) {

			logger.info("Change Contact Address.");
			detailsPg.setContactAdr(bondIssuerInfo.contactAddress);
		}
		if (!"".equals(bondIssuerInfo.firstName)) {

			logger.info("Change Contact First Name.");
			detailsPg.setContactFirstNm(bondIssuerInfo.firstName);
		}

		if (!"".equals(bondIssuerInfo.lastName)) {

			logger.info("Change Contact Last Name.");
			detailsPg.setContactLastNm(bondIssuerInfo.lastName);
		}

		if (!"".equals(bondIssuerInfo.country)) {

			logger.info("Change Country.");
			detailsPg.setCountry(bondIssuerInfo.country);
		}

		if (!"".equals(bondIssuerInfo.email)) {

			logger.info("Change Contact Email Address.");
			detailsPg.setEmail(bondIssuerInfo.email);
		}
		if (!"".equals(bondIssuerInfo.phone)) {

			logger.info("Change Contact Phone Number.");
			detailsPg.setPhone(bondIssuerInfo.phone);
		}

		if (!"".equals(bondIssuerInfo.state)) {

			logger.info("Change State.");
			detailsPg.setState(bondIssuerInfo.state);
			ajax.waitLoading();
		}

		if (!"".equals(bondIssuerInfo.zipCd)) {

			logger.info("Change ZIP/Postal Code.");
			detailsPg.setZipCode(bondIssuerInfo.zipCd);
		}

		detailsPg.clickOK();
		ajax.waitLoading();
		bondIssuersPg.waitLoading();
	}

	/**
	 * Start from Vendor Detail Page and end at Vendor Bonds page
	 */
	public void gotoBondSubTabFromVendorDetailPg() {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorBondsSubPage vendorBondsPg = LicMgrVendorBondsSubPage
				.getInstance();

		vendorDetailPg.clickBankBondsTab();
		ajax.waitLoading();
		vendorBondsPg.waitLoading();

	}

	/**
	 * Start from Vendor Bonds Page and end at add vendor bonds widget
	 */
	public void gotoAddBondsWidget() {
		LicMgrVendorBondsSubPage vendorBondsPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrAddVendorBondsWidget addBondsWidget = LicMgrAddVendorBondsWidget
				.getInstance();

		vendorBondsPg.clickAddBond();
		ajax.waitLoading();
		addBondsWidget.waitLoading();
	}

	/**
	 * Add vendor bond at Add Bond Widget by clicking 'Add Bond' link in vendor
	 * Bond sub page
	 * 
	 * @param bondInfo
	 * @param isClickOK
	 * @return - the newest added Bond id OR the error message displayed at the
	 *         add widget
	 */
	public String addVendorBond(VendorBondInfo bondInfo, boolean isClickOK) {
		LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrAddVendorBondsWidget addBondsWidget = LicMgrAddVendorBondsWidget
				.getInstance();

		String toReturn = "";

		logger.info("Add vendor Bond in Bonds sub page.");
		vendorBondPg.clickAddBond();
		ajax.waitLoading();
		addBondsWidget.waitLoading();
		addBondsWidget.setBondInfo(bondInfo);
		if (isClickOK) {
			addBondsWidget.clickOK();
		} else {
			addBondsWidget.clickCancel();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(addBondsWidget, vendorBondPg);
		if (page == addBondsWidget) {
			toReturn = addBondsWidget.getErrorMsg();
		}
		if ((page == vendorBondPg) && isClickOK) {
			toReturn = vendorBondPg.getBondIDByBondInfo(bondInfo);
		}

		return toReturn;
	}

	/**
	 * Go to the HarvestQuestionPage from tab.
	 */

	public void gotoHarvestQuestionsPage() {
		logger
				.info("Go Harvest Questions page from product configration page.");
		this.gotoProductSearchListPageFromTopMenu("Harvest Questions");
	}

	/**
	 * Go to the HarvestQuestionPage from tab.
	 * 
	 * @param id
	 *            - the harvest question Id.
	 */

	public void gotoEditHarvestQuesestPage(String harverId) {
		LicMgrHavestQuestionsListPage quesListPg = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage quesDetailPg = LicMgrHarvestQuestionsDetailsPage
				.getInstance();

		logger
				.info("Go to Harvest Questions(#=" + harverId
						+ ") details page.");

		quesListPg.clickHarvestId(harverId);
		ajax.waitLoading();
		quesDetailPg.waitLoading();
	}

	/**
	 * Add the harvest questions.
	 * 
	 * @param harvestQuestion
	 *            - the harvest question array.
	 * @param effectiveDate
	 *            - the effective Data of a harvest report questionnaire detail.
	 * @param isOK
	 *            - Click the Ok or cancel button in adding harvest widget page.
	 * @param isCancel
	 *            - Click the Ok or cancel button in adding harvest widget page
	 *            in the edit harvest page.
	 * @return - the add harvest widget error message.
	 */
	/*
	 * public void addHarvestQuest(ArrayList<HarvestQuestion> harvestQuestion,
	 * String effectiveDate){ String harvestId ="";
	 * LicMgrHavestQuestionsListPage listPg =
	 * LicMgrHavestQuestionsListPage.getInsatance();
	 * LicMgrHarvestQuestionsDetailsPage detailPg =
	 * LicMgrHarvestQuestionsDetailsPage.getInstance();
	 * LicMgrAddHarvestQuestionWidget addWidget =
	 * LicMgrAddHarvestQuestionWidget.getInsatance();
	 * 
	 * logger.info("Add harvest question.");
	 * 
	 * for(int i= 0;i<harvestQuestion.size();i++){
	 * detailPg.clickAddHarvestQuestion(); addWidget.waitExists();
	 * addWidget.setHarvestQuestionInfo(harvestQuestion.get(i));
	 * addWidget.clickOK(); ajax.waitLoading(); } detailPg.waitExists();
	 * if(null!= effectiveDate){ detailPg.setEffectiveDate(effectiveDate); }
	 * detailPg.clickOk(); listPg.waitExists(); harvestId =
	 * listPg.getHarvestQuestionId(harvestQuestReport.species,
	 * harvestQuestReport.season, harvestQuestReport.status,
	 * harvestQuestReport.effectiveDate);//TODO return harvestId; }
	 */

	public String addHarvestQuest(HarvestReportQuestionnaires havQuestReport) {
		String harvestId = "";
		LicMgrHavestQuestionsListPage listPg = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage detailPg = LicMgrHarvestQuestionsDetailsPage
				.getInstance();
		LicMgrAddHarvestQuestionWidget addWidget = LicMgrAddHarvestQuestionWidget
				.getInsatance();

		logger.info("Add harvest question.");
		for (int i = 0; i < havQuestReport.harvestQuestions.size(); i++) {
			detailPg.clickAddHarvestQuestion();
			addWidget.waitLoading();
			addWidget.setHarvestQuestionInfo(havQuestReport.harvestQuestions
					.get(i));
			addWidget.clickOK();
			ajax.waitLoading();
		}
		detailPg.waitLoading();
		if (havQuestReport.effectiveDate.length() > 0) {
			detailPg.setEffectiveDate(havQuestReport.effectiveDate);
		}
		detailPg.clickOk();
		listPg.waitLoading();
		harvestId = listPg.getHarvestQuestionId(havQuestReport.species,
				havQuestReport.season, havQuestReport.status,
				havQuestReport.effectiveDate);
		return harvestId;
	}

	/**
	 * Edit the harvest * Edit the harvest question.
	 * 
	 * @param harvestQuestReport
	 *            - the value of edit harvest question.
	 * @param isCancel
	 *            - Click the OK or cancel button in adding harvest widget page
	 *            in the edit harvest page.
	 * @param isRemove
	 *            - Remove the acceptable answer and display order or not.
	 */
	public String editHarvestReportQuestionnaire(
			HarvestReportQuestionnaires harvestQuestReport) {
		String harvestId = "";
		LicMgrHavestQuestionsListPage licMgrHarvestListPage = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage licMgrHarvestQuestionsDetailsPage = LicMgrHarvestQuestionsDetailsPage
				.getInstance();

		logger.info("Eidt harvest report");

		// licMgrHarvestQuestionsDetailsPage.setEffectiveDate(harvestQuestReport.effectiveDate);
		if (harvestQuestReport.harvestQuestions.size() > 0) {
			for (int i = 0; i < harvestQuestReport.harvestQuestions.size(); i++) {
				licMgrHarvestQuestionsDetailsPage
						.editHarvestQuesion(harvestQuestReport.harvestQuestions
								.get(i));
			}
		}

		if (null != harvestQuestReport.effectiveDate
				&& harvestQuestReport.effectiveDate.length() > 0) {
			licMgrHarvestQuestionsDetailsPage
					.setEffectiveDate(harvestQuestReport.effectiveDate);
		}

		licMgrHarvestQuestionsDetailsPage.clickOk();
		ajax.waitLoading();
		licMgrHarvestListPage.waitLoading();
		harvestId = licMgrHarvestListPage.getHarvestQuestionId(
				harvestQuestReport.species, harvestQuestReport.season,
				harvestQuestReport.status, harvestQuestReport.effectiveDate);
		return harvestId;
	}

	/**
	 * Remove the harvest question.
	 * 
	 * @return String - the changed harvest id
	 */

	public String removeHarvestQuestions(
			HarvestReportQuestionnaires harvestQuestReport) {
		String harvestId = "";
		LicMgrHavestQuestionsListPage licMgrHarvestListPage = LicMgrHavestQuestionsListPage
				.getInsatance();
		LicMgrHarvestQuestionsDetailsPage licMgrHarvestQuestionsDetailsPage = LicMgrHarvestQuestionsDetailsPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		logger.info("Remove harvest questions");
		for (int i = 0; i < Integer.parseInt(harvestQuestReport.questionNum) - 1; i++) {
			licMgrHarvestQuestionsDetailsPage.removeHarvestQuestions(i);
			ajax.waitLoading();
			Object page = browser.waitExists(confirmWidget,
					licMgrHarvestQuestionsDetailsPage);
			if (confirmWidget == page) {
				confirmWidget.clickOK();
				ajax.waitLoading();
				licMgrHarvestQuestionsDetailsPage.waitLoading();
			}
		}
		licMgrHarvestQuestionsDetailsPage.clickOk();
		licMgrHarvestListPage.waitLoading();
		harvestId = licMgrHarvestListPage.getHarvestQuestionId(
				harvestQuestReport.species, harvestQuestReport.season,
				harvestQuestReport.status, harvestQuestReport.effectiveDate);
		return harvestId;
	}

	// keyword for go to harvest question list page.
	public void gotoHarvestQuestionListPage() {
		LicMgrHarvestQuestionsListPage hQuestionPage = LicMgrHarvestQuestionsListPage
				.getInstance();
		LicMgrProductPage productPage = LicMgrProductPage.getInstance();
		productPage.clickHarvestQuestionsTab();
		ajax.waitLoading();
		hQuestionPage.waitLoading();
	}

	public void gotoPrivilegesListPage() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrProductPage productPage = LicMgrProductPage.getInstance();
		productPage.clickPrivilegesTab();
		ajax.waitLoading();
		privilegeListPage.waitLoading();
	}

	/**
	 * This method used to change bond assignment for given agent with given
	 * bond
	 * 
	 * @param agentId
	 * @param bondNum
	 * @param bondIssuer
	 */
	public void changeAgentBondAssignment(String agentId, String bondNum,
			String bondIssuer) {
		LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrVendorChangeStoreBondAssignmentWidget changPg = LicMgrVendorChangeStoreBondAssignmentWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmPg = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Assign Bond " + bondNum + " to Agent " + agentId);

		vendorBondPg.clickChangeAssignments();
		ajax.waitLoading();
		changPg.waitLoading();
		changPg.assignBondToAgent(agentId, bondNum, bondIssuer);
		changPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmPg, vendorBondPg);
		if (page == confirmPg) {
			confirmPg.clickOK();
			ajax.waitLoading();
			vendorBondPg.waitLoading();
		}
	}

	/**
	 * This method used to change bond assignment for given agent with given
	 * bond
	 * 
	 * @param agentName
	 * @param bondNum
	 * @param bondIssuer
	 */
	public void changeAgentBondAssignmentByName(String agentName,
			String bondNum, String bondIssuer) {
		LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrVendorChangeStoreBondAssignmentWidget changPg = LicMgrVendorChangeStoreBondAssignmentWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmPg = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Assign Bond " + bondNum + " to Agent " + agentName);

		vendorBondPg.clickChangeAssignments();
		ajax.waitLoading();
		changPg.waitLoading();
		changPg.assignBondToAgentByName(agentName, bondNum, bondIssuer);
		changPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmPg, vendorBondPg);
		if (page == confirmPg) {
			confirmPg.clickOK();
			ajax.waitLoading();
			vendorBondPg.waitLoading();
		}
	}

	/**
	 * Goto agent/bonds assigments page from vendor bonds list page
	 */
	public void gotoViewAgentBondsAssigmentsPg() {
		LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrVendorViewStoreBondAssignmentWidget viewPg = LicMgrVendorViewStoreBondAssignmentWidget
				.getInstance();

		logger.info("Goto Agent/Bonds Assignments Page.");

		vendorBondPg.clickViewAssignments();
		ajax.waitLoading();
		viewPg.waitLoading();
	}

	/**
	 * Goto agent/bonds assignments page from vendor bonds list page by click
	 * number of agents link for given bond
	 */
	public void gotoViewAgentBondsAssigmentsPg(String bondId) {
		LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrVendorViewStoreBondAssignmentWidget viewPg = LicMgrVendorViewStoreBondAssignmentWidget
				.getInstance();

		logger.info("Goto View Agent/Bonds Assignments Page for given bond "
				+ bondId);

		vendorBondPg.clickNumOfAssignedAgents(bondId);
		viewPg.waitLoading();
	}

	/**
	 * Go to lien companies page from System Configuration page
	 */
	public void gotoLienCompaniesPageFromSysConfigPage() {
		LicMgrSystemConfigurationPage systemConfigPage = LicMgrSystemConfigurationPage
				.getInstance();
		LicMgrLienCompaniesConfigurationPage lienCompaniesPage = LicMgrLienCompaniesConfigurationPage
				.getInstance();

		logger
				.info("Go to lien companies page from System Configuration page.");
		systemConfigPage.clickLienCompaniesTab();
		ajax.waitLoading();
		lienCompaniesPage.waitLoading();
	}

	/**
	 * Add lien company info with new lien company name from lien companies page
	 * with click cancel
	 * 
	 * @param lienCompanyInfo
	 * @return
	 */
	public String addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickCancel(
			LienCompanyDetailsInfo lienCompanyInfo) {
		return this.addLienCompanyFromLienCompaniesPage(lienCompanyInfo, true,
				true);
	}

	/**
	 * Add lien company info with new lien company name from lien companies page
	 * with click OK
	 * 
	 * @param lienCompanyInfo
	 * @return
	 */
	public String addLienCompanyWithNewLienCompanyNameFromCompaniesPageWithClickOk(
			LienCompanyDetailsInfo lienCompanyInfo) {
		return this.addLienCompanyFromLienCompaniesPage(lienCompanyInfo, false,
				true);
	}

	/**
	 * Add lien company info with select lien company name from lien companies
	 * page with click OK
	 * 
	 * @param lienCompanyInfo
	 * @return
	 */
	public String addLienCompanyWithSelectLienCompanyNameFromCompaniesPageWithClickOK(
			LienCompanyDetailsInfo lienCompanyInfo) {
		return this.addLienCompanyFromLienCompaniesPage(lienCompanyInfo, false,
				false);
	}

	/**
	 * Add Lien Company info from lien companies page
	 * 
	 * @param lienCompanyInfo
	 */
	String addLienCompanyFromLienCompaniesPage(
			LienCompanyDetailsInfo lienCompanyInfo, boolean isClickCancel,
			boolean isNewLienCompanyName) {
		LicMgrLienCompaniesConfigurationPage lienCompaniesPage = LicMgrLienCompaniesConfigurationPage
				.getInstance();
		LicMgrAddLienCompanyWidget addLienCompanyPage = LicMgrAddLienCompanyWidget
				.getInstance();
		String toReturn = null;

		logger.info("Add lien company info.");
		lienCompaniesPage.clickAdd();
		ajax.waitLoading();
		addLienCompanyPage.waitLoading();
		addLienCompanyPage.setLienCompanyInfo(lienCompanyInfo,
				isNewLienCompanyName);
		if (isClickCancel) {
			addLienCompanyPage.clickCancel();
			ajax.waitLoading();
		} else {
			addLienCompanyPage.clickOK();
			ajax.waitLoading();
		}
		Object pages = browser
				.waitExists(lienCompaniesPage, addLienCompanyPage);
		if (lienCompaniesPage == pages) {
			toReturn = lienCompaniesPage.getLienCompanyAddrID(lienCompanyInfo);
		}

		if (addLienCompanyPage == pages) {
			toReturn = addLienCompanyPage.getMessage();
		}
		return toReturn;
	}

	/**
	 * Go to lien company detailpage from lien companies page, through by click
	 * lien company ID
	 * 
	 * @param lienCompanyID
	 */
	public void gotoLienCompanyDetailPageFromLienCompaniesPage(
			String lienCompanyID) {
		LicMgrLienCompaniesConfigurationPage lienCompaniesPage = LicMgrLienCompaniesConfigurationPage
				.getInstance();
		LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage
				.getInstance();

		logger
				.info("Go to lien company detail page from lien companies page by click lien company ID = "
						+ lienCompanyID);
		lienCompaniesPage.clickLienCompanyID(lienCompanyID);
		ajax.waitLoading();
		lienCompanyDetailPage.waitLoading();
	}

	/**
	 * Go to lien company history page from lien company detail page. Start from
	 * lien company detail page, end at lien company history page
	 */
	public void gotoLienCompanyHistoryPageFromLienCompanyDetailPage() {
		LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage
				.getInstance();
		LicMgrLienCompanyChangeHistoryPage lienCompanyChangeHistory = LicMgrLienCompanyChangeHistoryPage
				.getInstance();

		logger
				.info("Go to lien company history page from lien company detail page.");
		lienCompanyDetailPage.clickChangeHistory();
		ajax.waitLoading();
		lienCompanyChangeHistory.waitLoading();
	}

	/**
	 * Go to lien company detail page from lien company history page Start from
	 * lien company history page, end at lien company detail page
	 */
	public void gotoLienCompanyDetailPageFromLienCompanyHistoryPage() {
		LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage
				.getInstance();
		LicMgrLienCompanyChangeHistoryPage lienCompanyChangeHistory = LicMgrLienCompanyChangeHistoryPage
				.getInstance();

		logger
				.info("Go to lien company detail page from lien company history page.");
		lienCompanyChangeHistory.clickReturnToLienCompanyDetails();
		ajax.waitLoading();
		lienCompanyDetailPage.waitLoading();
	}

	/**
	 * Edit lien Company Detail Info with Click OK, start from lien companies
	 * page
	 * 
	 * @param lienCompanyInfo
	 */
	public void editLienCompanyDetailInfoWithClickOK(
			LienCompanyDetailsInfo lienCompanyInfo) {
		this.editLienCompanyDetailInfo(lienCompanyInfo, "OK");
	}

	/**
	 * Edit lien company detail info with click Cancel, start from lien
	 * companies page, end at lien company companies page
	 * 
	 * @param lienCompanyInfo
	 */
	public void editLienCompanyDetailInfoWithClickCancel(
			LienCompanyDetailsInfo lienCompanyInfo) {
		this.editLienCompanyDetailInfo(lienCompanyInfo, "Cancel");
	}

	/**
	 * Edit lien company detail info with click apply, start from lien companies
	 * page, end at lien company detail page
	 * 
	 * @param lienCompanyInfo
	 */
	public void editLienCompanyDetailInfoWithClickApply(
			LienCompanyDetailsInfo lienCompanyInfo) {
		this.editLienCompanyDetailInfo(lienCompanyInfo, "Apply");
	}

	/**
	 * Edit lien company detail info
	 * 
	 * @param lienCompanyInfo
	 * @param clickFlag
	 */
	void editLienCompanyDetailInfo(
			LienCompanyDetailsInfo lienCompanyInfo, String clickFlag) {
		LicMgrLienCompaniesConfigurationPage lienCompaniesPage = LicMgrLienCompaniesConfigurationPage
				.getInstance();
		LicMgrLienCompanyDetailPage lienCompanyDetailPage = LicMgrLienCompanyDetailPage
				.getInstance();
		logger.info("Edit lien company detail info. Lien company ID = "
				+ lienCompanyInfo.lienCompanyAddrID);

		lienCompanyDetailPage.setLienCompanyInfo(lienCompanyInfo);
		if (clickFlag.equals("Cancel")) {
			lienCompanyDetailPage.clickCancel();
			ajax.waitLoading();
			lienCompaniesPage.waitLoading();
		} else if (clickFlag.equals("Apply")) {
			lienCompanyDetailPage.clickApply();
			ajax.waitLoading();
			lienCompanyDetailPage.waitLoading();
		} else if (clickFlag.equals("OK")) {
			lienCompanyDetailPage.clickOK();
			ajax.waitLoading();
			browser.waitExists(lienCompanyDetailPage, lienCompaniesPage);
		} else {
			throw new ErrorOnPageException("Did not found the action "
					+ clickFlag);
		}
	}

	/**
	 * Filter lien company info from lien company configuration page, through by
	 * lien company anme. start from lien company configuration page, end at
	 * lien company configuration page
	 * 
	 * @param lienCompanyName
	 */
	public void filterLienCompanyInfoFromLienCompanyConfigurationPage(
			String lienCompanyName) {
		LicMgrLienCompaniesConfigurationPage lienCompaniesPage = LicMgrLienCompaniesConfigurationPage
				.getInstance();

		logger.info("Filter lien company info by lien company name = "
				+ lienCompanyName);
		if (lienCompanyName != null && lienCompanyName.trim().length() > 0) {
			lienCompaniesPage.selectFilters(lienCompanyName);
		} else {
			lienCompaniesPage.selectFilters();
		}

		lienCompaniesPage.clickGo();
		ajax.waitLoading();
		lienCompaniesPage.waitLoading();
	}

	/**
	 * Go to EFT Schedule sub page from system configuration main page
	 */
	public void gotoEFTSchedulePageFromSysConfigPage() {
		LicMgrSystemConfigurationPage systemConfigPage = LicMgrSystemConfigurationPage
				.getInstance();
		LicMgrEFTSchedulesConfigurationPage eftSchedulePage = LicMgrEFTSchedulesConfigurationPage
				.getInstance();

		logger.info("Go to EFT Schedule page from System Configuration page.");
		systemConfigPage.clickEFTSchedulesTab();
		ajax.waitLoading();
		eftSchedulePage.waitLoading();
	}

	/**
	 * Add EFT Schedule at EFT Schedule configuration sub page
	 * 
	 * @param schedule
	 */
	public String addEFTSchedule(EFTScheduleInfo schedule) {
		LicMgrEFTSchedulesConfigurationPage eftSchedulesPage = LicMgrEFTSchedulesConfigurationPage
				.getInstance();
		LicMgrAddEFTScheduleWidget addEftScheduleWidget = LicMgrAddEFTScheduleWidget
				.getInstance();

		logger.info("Add EFT Schedule.");
		eftSchedulesPage.clickAdd();
		ajax.waitLoading();
		addEftScheduleWidget.waitLoading();
		
		return this.setupEFTSchedule(schedule);
	}
	
	public String setupEFTSchedule(EFTScheduleInfo schedule) {
		LicMgrEFTSchedulesConfigurationPage eftSchedulesPage = LicMgrEFTSchedulesConfigurationPage
				.getInstance();
		LicMgrAddEFTScheduleWidget addEftScheduleWidget = LicMgrAddEFTScheduleWidget
				.getInstance();
		String toReturn = "";

		addEftScheduleWidget.setupEFTScheduleInfo(schedule);
		addEftScheduleWidget.clickOK();
		ajax.waitLoading();
		Object page = browser
				.waitExists(addEftScheduleWidget, eftSchedulesPage);
		if (page == addEftScheduleWidget) {
			toReturn = addEftScheduleWidget.getErrorMessage();
		}
		if (page == eftSchedulesPage) {
			toReturn = eftSchedulesPage.getEFTScheduleIDViaName(schedule.name);
		}

		return toReturn;
	}

	/**
	 * Delete EFT Schedule record from DB, because there is not a interface to
	 * delete it from UI
	 * 
	 * @param schema
	 * @param scheduleID
	 */
	public void deleteEFTScheduleFromDB(String schema, String scheduleID) {
		this.deleteEFTScheduleFromDB(schema, "id", scheduleID);
	}
	
	public void deleteEFTScheduleFromDBByName(String schema,String name){
		this.deleteEFTScheduleFromDB(schema, "name", name);
	}
	
	void deleteEFTScheduleFromDB(String schema,String searchType,String value){
		logger.info("Delete EFT schdule by "+searchType+" is "+value);
		
		db.resetSchema(schema);
		String sql = "delete from f_eft_schedule where "+searchType+"='" + value+"'";
		db.executeUpdate(sql);
	}

	/**
	 * update vendor financial config EFT schedule as the newest value
	 * 
	 * @param schema
	 * @param eftFrequency
	 */
	public void updateVendorFinancialConfigDefaultEFTSchdl(String schema,
			String eftFrequency) {
		logger
				.info("Update vendor financial config default EFT schedule(Frequency="
						+ eftFrequency + ") as the newest value.");
		Map<String, Integer> frequencies = new HashMap<String, Integer>();
		frequencies.put("Daily", 1);
		frequencies.put("Weekly", 2);
		frequencies.put("Monthly", 3);
		frequencies.put("Quarterly", 4);
		frequencies.put("Annually", 5);
		db.resetSchema(schema);

		String sql = "select ID from F_EFT_SCHEDULE where EFT_FREQUENCY="
				+ frequencies.get(eftFrequency);
		List<String> ids = db.executeQuery(sql, "ID");
		if (ids.size() < 1) {
			throw new ErrorOnDataException(
					"Can't find eft schedule record identified by frequency - "
							+ eftFrequency);
		}
		String id = ids.get(0);
		sql = "select * from F_VENDOR_FIN_CONF_DEFAULT where LABEL=" + "'"
				+ eftFrequency + " EFT'";
		int affectedNum = db.executeUpdate(sql);
		if (affectedNum > 0) {
			sql = "update F_VENDOR_FIN_CONF_DEFAULT set EFT_SCHDL = " + id
					+ " where LABEL=" + "'" + eftFrequency + " EFT'";
			affectedNum = db.executeUpdate(sql);
			if (affectedNum < 1) {
				throw new ErrorOnDataException(
						"Can't find vendor financial config default eft record which LABEL= "
								+ eftFrequency);
			} else {
				logger
						.info("Update vendor financial config default EFT schedule as newest value - '"
								+ eftFrequency + "' successfully.");
			}
		}
	}

	/**
	 * This method can be used to delete display category, display sub-category,
	 * report category from DB
	 * 
	 * @param schema
	 * @param id
	 */
	public void deleteDisplayCategoryFromDB(String schema, String id) {
		logger.info("Delete Display Category(ID#=" + id + ") from DB.");
		db.resetSchema(schema);
		String sql = "delete from p_display_category where id=" + id;
		int affectedNum = db.executeUpdate(sql);
		if (affectedNum < 1) {
			throw new ErrorOnDataException(
					"Can't find Display Category record - " + id);
		} else {
			logger
					.info("Delete Display Category(ID#=" + id
							+ ") successfully.");
		}
	}

	/**
	 * Goto vehicle search page from top menu
	 */
	public void gotoVehicleSearchPageFromTopMenu() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();

		logger.info("Go to vehicle search page from top menu.");
		topMenu.clickVehicles();
		ajax.waitLoading();
		vehicleSearchPg.waitLoading();
	}

	public void gotoVehicleDetailsPgByMiNum(String num) {
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();

		logger.info("Go to vehicle detail page from top menu.");
		this.gotoVehicleSearchPageFromTopMenu();
		vehicleSearchPg.setMINum(num);
		vehicleSearchPg.setState(StringUtil.EMPTY);
		vehicleSearchPg.clickSearch();
		ajax.waitLoading();
		Object page = browser.waitExists(vehicleSearchPg, vehicleDetailsPg);
		if (page == vehicleSearchPg) {
			vehicleSearchPg.clickVehiclesID(num);
			ajax.waitLoading();
			vehicleDetailsPg.waitLoading();
		}
	}

	public void gotoVehiclesDetailsPgBySerialNum(String num) {
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();

		logger.info("Go to vehicle detail page from top menu.");
		this.gotoVehicleSearchPageFromTopMenu();
		
		vehicleSearchPg.setState("");// blank
		ajax.waitLoading();
		
		vehicleSearchPg.setSerialNum(num);
		vehicleSearchPg.clickSearch();
		ajax.waitLoading();
		Object page = browser.waitExists(vehicleSearchPg, vehicleDetailsPg);
		if (page == vehicleSearchPg) {
			vehicleSearchPg.clickVehiclesIDBySerialNum(num);
			browser.waitExists(vehicleDetailsPg);
		}
	}

	/**
	 * 
	 */
	public void editVehicleInfo(Vehicle veh, String note) {
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleDetailPage vehDetailPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleStatusChangeWidget statusWidget = LicMgrVehicleStatusChangeWidget
				.getInstance();
		LicMgrVehicleChangeConfirmWidget confirm = LicMgrVehicleChangeConfirmWidget
				.getInstance();

		logger.info("Edit vehicle info in LicMgrVehicleDetailPage.");

		vehDetailPg.fillVehicleInfo(veh);
		vehDetailPg.clickApply();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirm, statusWidget);
		if (pages instanceof LicMgrVehicleChangeConfirmWidget) {
			confirm.clickOK();
			ajax.waitLoading();

			statusWidget.waitLoading();
			statusWidget.setStatusChangeNote(note);
			statusWidget.clickOK();
			ajax.waitLoading();

		} else if (pages instanceof LicMgrVehicleStatusChangeWidget) {
			statusWidget.setStatusChangeNote(note);
			statusWidget.clickOK();
			ajax.waitLoading();
		}

		vehDetailPg.waitLoading();
		vehDetailPg.clickOK();
		ajax.waitLoading();
		vehicleSearchPg.waitLoading();
	}

	/**
	 * Go to Financial Config page From Vendor Detail Page
	 * 
	 */
	public void gotoVendorFinConfigSubPage() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorFinConfigSubPage finConfigPg = LicMgrVendorFinConfigSubPage
				.getInstance();

		logger.info("Go to Financial Config page From Vendor Detail Page.");
		vendorDetailsPg.clickFinancialConfigTab();
		ajax.waitLoading();
		finConfigPg.waitLoading();
	}

	/**
	 * Go to Change History Page From Vendor Detail Page
	 * 
	 */
	public void gotoVendorChangeHistoryPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorViewChangeHistoryPage vendorChangeHistoryPg = LicMgrVendorViewChangeHistoryPage
				.getInstance();

		logger.info("Go to Change History Page From Vendor Detail Page.");
		vendorDetailsPg.clickChangeHistory();
		ajax.waitLoading();
		vendorChangeHistoryPg.waitLoading();
	}

	/**
	 * Go to Vendor Detail Page From Change History Page
	 * 
	 */
	public void gotoVendorDetailPgFromChageHistoryPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorViewChangeHistoryPage vendorChangeHistoryPg = LicMgrVendorViewChangeHistoryPage
				.getInstance();

		logger.info("Go to Vendor detail page from Change History Page.");
		vendorChangeHistoryPg.clickReturnToVendorDetail();
		ajax.waitLoading();
		vendorDetailsPg.waitLoading();
	}

	/**
	 * Go to Vendor Detail Page From Vendor Search Page by Vendor number
	 * 
	 * @param vendorNum
	 */
	public Object gotoVendorDetailPgFromVendorSearchPg(String vendorNum) {
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();

		// search by vendor number
		vendorSearchPg.setVendorNumber(vendorNum);
		vendorSearchPg.clickSearch();
		ajax.waitLoading();
		return browser.waitExists(vendorSearchPg, vendorDetailPg);
	}

	/**
	 * change vendor status, start from vendor detail page, end at vendor search
	 * page.
	 * 
	 * @param status
	 */
	public void changeVendorStauts(String status) {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Change vendor status to: " + status + ".");
		vendorDetailPg.selectVendorStatus(status.replace(" - ", "-"));
		vendorDetailPg.clickOK();
		Object objs = browser.waitExists(vendorSearchPg, confirmDialogWidget);
		if (confirmDialogWidget == objs) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			vendorDetailPg.waitLoading();
			vendorDetailPg.clickOK();
			ajax.waitLoading();
			vendorSearchPg.waitLoading();
		}
	}

	/**
	 * This method is used to update vendor basic info
	 * 
	 * @param vendor
	 */
	public String updateVendorBasicInfo(VendorInfo vendor) {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		String toReturn = "";

		logger.info("update vendor basic info.");
		vendorDetailPg.setVendorBasicInfo(vendor);
		vendorDetailPg.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(vendorSearchPg, vendorDetailPg);
		if (pages == vendorSearchPg) {
			toReturn = vendor.number;
		} else {
			toReturn = vendorDetailPg.getErrorMessage();
		}

		return toReturn;
	}

	/**
	 * This method is used to update vendor address and contact info
	 * 
	 * @param vendor
	 */
	public String updateVendorAddressAndContactInfo(VendorInfo vendor,
			boolean isReturnToSearchPg) {
		LicMgrVendorDetailAddAndContractsPage addressAndContactPg = LicMgrVendorDetailAddAndContractsPage
				.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();
		String toReturn = "";

		logger.info("Update vendor address and contact info.");
		addressAndContactPg.setupVendorAddressAndContactInfo(vendor);
		addressAndContactPg.clickSave();
		ajax.waitLoading();
		addressAndContactPg.waitLoading();
		if (addressAndContactPg.checkErrorMessageIsExist()) {
			toReturn = addressAndContactPg.getErrorMessage();
		}
		if (isReturnToSearchPg) {
			addressAndContactPg.clickOK();
			ajax.waitLoading();
			browser.waitExists(vendorSearchPg, addressAndContactPg);
		}
		return toReturn;
	}

	/**
	 * update vendor financial config info
	 * 
	 * @param vendorNum
	 * @param financialInfo
	 */
	public void updateVendorFinancialConfigInfo(String vendorNum,
			LicMgrVendorFinConfigInfo financialInfo) {
		LicMgrVendorFinConfigSubPage finConfigPage = LicMgrVendorFinConfigSubPage
				.getInstance();

		logger.info("Update vendor(Vendor#=" + vendorNum
				+ ") financial config info.");

		
		if(!finConfigPage.exists() || !vendorNum.equals(finConfigPage.getVendorNum())){
			this.gotoVendorDetailsPgFromTopMenu(vendorNum);
			this.gotoVendorFinConfigSubPage();
		}
		finConfigPage.editFinancialConfigInfo(financialInfo);
		finConfigPage.clickSave();
		ajax.waitLoading();
		finConfigPage.waitLoading();
	}

	/**
	 * This method is used to go to vendor application page, start from vendor
	 * detail page end at vendor application page
	 */
	public void gotoVendorApplicationPgFromVendorDetailPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorApplicationPage vendorApplicationPg = LicMgrVendorApplicationPage
				.getInstance();

		logger.info("Go to Vendor Application page from vendor detail page.");
		vendorDetailsPg.clickApplication();
		ajax.waitLoading();
		vendorApplicationPg.waitLoading();
	}

	/**
	 * This method is used to go to vendor detail page, start from vendor
	 * applicaiton page end at vendor detail page
	 */
	public void gotoVendorDetialPgFromVendorApplicationPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorApplicationPage vendorApplicationPg = LicMgrVendorApplicationPage
				.getInstance();

		logger.info("Go to Vendor detail page from vendor application page.");
		vendorApplicationPg.clickCancel();
		ajax.waitLoading();
		vendorDetailsPg.waitLoading();
	}

	/*
	 * This method is used to go to vendor address and contact page, start from
	 * vendor detail page end at vendor address and contact sub page.
	 */
	public void gotoVendorAddressAndContactSubPagFromVendorDetailPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorDetailAddAndContractsPage addressAndContactPg = LicMgrVendorDetailAddAndContractsPage
				.getInstance();

		logger
				.info("Go to Vendor address and contact sub page from vendor detail page.");
		vendorDetailsPg.clickAddressesContactsTab();
		ajax.waitLoading();
		addressAndContactPg.waitLoading();
	}

	public void gotoVendorBankAccountStoreAssignmentList() {
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		LicMgrVendorBankAccountStoreAssignmentsDetailsWidget vendorBankAccountDetailsPg = LicMgrVendorBankAccountStoreAssignmentsDetailsWidget
				.getInstance();

		vendorBankAccountPg.clickViewStoreAccountAssignments();
		vendorBankAccountDetailsPg.waitLoading();
	}

	public Certification getCertificationByNum(String certificationNum) {
		LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		int row = certificationPg.getCertificationRow(certificationNum);
		Certification c = certificationPg.getCertificationInfoByRowNum(row);
		return c;
	}

	public void gotoCustDetailFromChangeHisPg() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerChangeHistoryPage historyPage = LicMgrCustomerChangeHistoryPage
				.getInstance();
		historyPage.clickReturnToCustomerDetail();
		ajax.waitLoading();
		custDetailPg.waitLoading();
	}

	public void gotoCustChangeHistoryPg() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerChangeHistoryPage historyPage = LicMgrCustomerChangeHistoryPage
				.getInstance();
		custDetailPg.clickChangeHistory();
		ajax.waitLoading();
		historyPage.waitLoading();
	}

	public void gotoCustomerDetailPg() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		custSearchPg.clickCustomerFirstNumer();
		ajax.waitLoading();
		custDetailPg.waitLoading();
	}

	/**
	 * This method is used to go to vendor vendor agents page
	 */
	public void gotoVendorAgentsPg() {
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage
				.getInstance();

		logger.info("Go to vendor agents page from vendor detail page");
		vendorDetailsPg.clickStoresTab();
		ajax.waitLoading();
		vendorAgentsPg.waitLoading();
	}

	/**
	 * This method is used add a new vendor store.
	 * 
	 * @param storeInfo
	 *            - store info.
	 */
	public String addVendorAgents(StoreInfo storeInfo) {
		LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreAddPage storeAddPg = LicMgrStoreAddPage.getInstance();
		LicMgrVendorDetailsPage vendorDetailsPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrStoreSelectCountyWidget selectCountyWidget = LicMgrStoreSelectCountyWidget
				.getInstance();

		String storeId = "";
		logger.info("Add vendor agents.");
		vendorAgentsPg.clickAddAgents();
		ajax.waitLoading();
		storeAddPg.waitLoading();

		storeAddPg.setStoreInfo(storeInfo);
		storeAddPg.clickApply();
		ajax.waitLoading();
		Object page = browser.waitExists(selectCountyWidget, storeDetailPg,
				storeAddPg);
		if (page == selectCountyWidget) {
			selectCountyWidget.selectCounty(1);
			selectCountyWidget.clickOK();
			ajax.waitLoading();
			storeDetailPg.waitLoading();
		}
		if (page == storeDetailPg
				&& StringUtil.isEmpty(storeDetailPg.getErrorMessage())) {
			storeId = storeDetailPg.getStoreID();
			storeDetailPg.clickOK();
			ajax.waitLoading();
			vendorDetailsPg.waitLoading();
		}
		return storeId;
	}

	/**
	 * This method is used to go to the store detail page after clicking the
	 * store id.
	 * 
	 * @param id
	 *            - the id of store.
	 */
	public void gotoVendorAgentsDetailPg(String id) {
		LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage
				.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();

		logger.info("Go to vendor store detail page from vendor agents page");
		vendorAgentsPg.clickStoreId(id);
		ajax.waitLoading();
		storeDetailPg.waitLoading();
	}

	/**
	 * Edit bond info, work flow starts from bond list page ends with the same
	 * one
	 * 
	 * @param newBondInfo
	 * @return
	 */
	public String editBondInfo(VendorBondInfo newBondInfo) {
		LicMgrVendorBondsSubPage bondPg = LicMgrVendorBondsSubPage
				.getInstance();
		LicMgrEditVendorBondsWidget bondsWidget = LicMgrEditVendorBondsWidget
				.getInstance();

		if (newBondInfo == null) {
			throw new ErrorOnDataException("Bond info should not null");
		}

		logger.info("Edit bond(ID#=" + newBondInfo.id + ") info");

		bondPg.clickBondId(newBondInfo.id);
		ajax.waitLoading();
		bondsWidget.waitLoading();

		bondsWidget.setBondInfo(newBondInfo);

		bondsWidget.clickOK();
		ajax.waitLoading();
		bondPg.waitLoading();

		bondPg.selectBondIsserForSearch(newBondInfo.issuer);
		bondPg.selectIncludeActive();
		// bondPage.selectTypeLetterOfCredit();
		bondPg.clickGo();
		ajax.waitLoading();
		bondPg.waitLoading();

		String newId = bondPg.getBondIDByBondInfo(newBondInfo);

		return newId;
	}

	/**
	 * This method is used to edit the vendor store.
	 * 
	 * @param newStore
	 *            - the updated store info.
	 */
	public void editVendorStore(StoreInfo newStore) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreAddressesAndContactsPage storeAddAndContactsPg = LicMgrStoreAddressesAndContactsPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorSearchListPage searchPg = LicMgrVendorSearchListPage
				.getInstance();

		storeDetailPg.SetStoreInfo(newStore);
		storeDetailPg.clickApply();
		ajax.waitLoading();
		Object objs = browser.waitExists(confirmDialogWidget, storeDetailPg);
		if (confirmDialogWidget == objs) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			storeDetailPg.waitLoading();
		}
		storeAddAndContactsPg.setAddressAndContactsInfo(newStore);
		storeAddAndContactsPg.clickSave();
		ajax.waitLoading();

		storeDetailPg.clickOK();
		browser.waitExists(searchPg, vendorDetailPg);
	}

	/**
	 * This method is used to update the store status.
	 * 
	 * @param newStatus
	 *            - the new store status.
	 */
	public void updateStoreStatus(String newStatus) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
				.getInstance();

		storeDetailPg.selectStatus(newStatus);
		storeDetailPg.clickApply();
		ajax.waitLoading();
		// To do
		Object objs = browser.waitExists(confirmDialogWidget, storeDetailPg);
		if (confirmDialogWidget == objs) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			storeDetailPg.waitLoading();
		}
		storeDetailPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(vendorAgentsPg, vendorSearchPg);
	}

	/**
	 * This method is used to go to the system configuration sub page .
	 * 
	 * @param tabName
	 *            - the tab name.
	 */
	public void gotoSysConfPgFromTopMenu(String tabName) {

		LicMgrSystemConfigurationPage sysConfigPg = LicMgrSystemConfigurationPage
				.getInstance();

		logger.info("Go to System Configuration " + tabName
				+ " Page From Top Menu");

		this.gotoSysConfPgFromTopMenu();
		if (tabName.equalsIgnoreCase("Vendor Status/Reason Mgt")) {
			sysConfigPg.clickVendorStatusReasonMgtTab();
			ajax.waitLoading();
			sysConfigPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Agents Status/Reason Mgt")) {
			LicMgrStoreStatusReasonMgtPage sotreStuReasonPg = LicMgrStoreStatusReasonMgtPage
					.getInstance();
			sysConfigPg.clickAgentStatusReasonMgtTab();
			ajax.waitLoading();
			sotreStuReasonPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("EFT Schedules")) {
			sysConfigPg.clickEFTSchedulesTab();
			ajax.waitLoading();
			sysConfigPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Bond Issuer")) {
			sysConfigPg.clickBondIssuersTab();
			ajax.waitLoading();
			sysConfigPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Lien Compnies")) {
			sysConfigPg.clickLienCompaniesTab();
			ajax.waitLoading();
			sysConfigPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unknown System Tab Name....");
		}
	}

	/**
	 * This method is used to add store status reason .
	 * 
	 * @param status
	 *            - the store status.
	 * @param reason
	 *            - the store status reason.
	 */
	public String addStoreStatusReason(String status, String reason) {
		LicMgrStoreStatusReasonMgtPage sotreStuReasonPg = LicMgrStoreStatusReasonMgtPage
				.getInstance();
		LicMgrStoreAddStatusReasonWidge storeAddStuReasonWgt = LicMgrStoreAddStatusReasonWidge
				.getInstance();

		logger.info("Add store status reason info");
		sotreStuReasonPg.clickAddButton();
		ajax.waitLoading();
		storeAddStuReasonWgt.waitLoading();
		storeAddStuReasonWgt.addStatusReason(status, reason);
		storeAddStuReasonWgt.clickOK();
		ajax.waitLoading();
		sotreStuReasonPg.waitLoading();
		String storeReasonId = sotreStuReasonPg.getStoreStatusReasonId(status,
				reason);
		return storeReasonId;
	}

	/**
	 * This method is used to go to vendor store history page .
	 */
	public void gotoVendorStoryChangeHistoryPage() {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreChangeHistoryPage historyPg = LicMgrStoreChangeHistoryPage
				.getInstance();
		storeDetailPg.clickChangeHistory();
		historyPg.waitLoading();
	}

	/**
	 * This method is used to go back to vendor store detail page from store
	 * change history page.
	 */
	public void gotoStoreDetailPage() {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreChangeHistoryPage historyPg = LicMgrStoreChangeHistoryPage
				.getInstance();
		historyPg.clickReturnToAgentsDetails();
		storeDetailPg.waitLoading();
	}

	/**
	 * Go to agents status reason mgt.
	 */
	public void gotoAgentStatusReasonMgtPage() {
		this.gotoSysConfPgFromTopMenu("Agents Status/Reason Mgt");
	}

	public void duplicateRegisterVehicleOrderToCartFromHomePg(Vehicle vehicle) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVehiclesSearchPage searchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleDetailPage detailPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleRegistrationWidget registrationPg = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger
				.info("Register vehicle order from vehicle quick go area to order cart.");

		homePg.clickVehicles();
		searchPg.waitLoading();
		if (vehicle instanceof BoatInfo) {
			searchPg
					.searchVehicleOrderBySerialNum(((BoatInfo) vehicle).hullIdSerialNum);
			searchPg
					.clickVehiclesIDBySerialNum(((BoatInfo) vehicle).hullIdSerialNum);
		} else if (vehicle instanceof MotorInfo) {
			searchPg.searchVehicleOrderBySerialNum(((MotorInfo) vehicle)
					.getSerialNum());
			searchPg.clickVehiclesIDBySerialNum(((MotorInfo) vehicle)
					.getSerialNum());
		}
		ajax.waitLoading();
		detailPg.waitLoading();

		detailPg.clickRegistration();
		ajax.waitLoading();
		registrationPg.waitLoading();
		registrationPg.selectRegistrationProduct("Duplicate",
				vehicle.registration.product);
		registrationPg.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	public void addSupplyProudct(SupplyInfo supply) {
		LicMgrSuppliesListPage listPg = LicMgrSuppliesListPage.getInstance();
		LicMgrCreateNewSupplyPage createNewPg = LicMgrCreateNewSupplyPage
				.getInstance();

		listPg.clickAddSupplyProduct();

		logger.info("Begin to add supply...");

		ajax.waitLoading();
		createNewPg.waitLoading();

		createNewPg.setProductCode(supply.code);
		createNewPg.setProductName(supply.name);
		createNewPg.setProductDescription(supply.description);
		createNewPg.selectProductGroup(supply.productGroup);
		createNewPg.selectAvailToEquipType(supply.availableToApp);
		createNewPg.setNumberOfPanels(supply.ofPanels);
		createNewPg.selectFulfillParty(supply.fulfillmentParty);
		createNewPg.setSupplyCost(supply.supplyCost);
		createNewPg.setShippingCost(supply.shippingCost);
		createNewPg.setMaxDailyOrder(supply.maxDailyOrder);
		createNewPg.setReorderThreshold(supply.recorderThreshold);
		createNewPg.setReorderEmail(supply.recorderMail);
		createNewPg.setQuantityOnHold(supply.qtyOnHand);

		createNewPg.clickApply();
		Browser.sleep(1);
		createNewPg.waitLoading();

		String errMsg = createNewPg.getErrorMessage();
		if (StringUtil.notEmpty(errMsg)) {
			throw new RuntimeException("Couldn't add supply for: " + errMsg);
		} else {
			supply.id = createNewPg.getProductID();
			supply.status = createNewPg.getProductStatus();
		}

		createNewPg.clickOK();

		ajax.waitLoading();
		listPg.waitLoading();
	}

	// go to the document template detail page.
	public void gotoDocuTemplatesDetailPage(String DocuTemplateName) {
		LicMgrDocumentTemplatesConfigurationPage docTemplatesListPg = LicMgrDocumentTemplatesConfigurationPage
				.getInstance();
		LicMgrDocumentTemplatesdetailPage docTemplatesDetailPg = LicMgrDocumentTemplatesdetailPage
				.getInstance();
		docTemplatesListPg.clickDocumentTemplateName(DocuTemplateName);
		ajax.waitLoading();
		docTemplatesDetailPg.waitLoading();
	}

	/**
	 * Process order cart. The work flow starts from order cart page and ends at
	 * license manager home page.
	 * 
	 * @param pay
	 *            - the payment information datacollection
	 * @return - the order numbers in the order cart.
	 */
	public String processOrderCart(Payment pay) {
		// get print document control from TestProperty by key
		// "control.printdoc"
		// the value of "control.printdoc" can be set in the test case steps.
		return processOrderCart(pay, Boolean.valueOf(TestProperty.getProperty(
				"control.printdoc", "true")));
	}

	/**
	 * void a privilege order, start from LM main page, end to order cart page.
	 * 
	 * 
	 * @param orderNum
	 * @param voidAction
	 *            : Undo/Reverse
	 * @param voidReason
	 *            : reason of void
	 * @param voidNote
	 * 
	 */
	public void voidPrivilegeOrder(String orderNum, String voidAction,
			String voidReason, String voidNote) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		gotoPrivilegeOrderDetailPage(orderNum);

		privOrderDetailsPage.voidOrReverseOrder();
		voidUndoVoidPg.waitLoading();

		voidUndoVoidPg.clickVoidOrUndoVoidSelectedTransaction();
		ajax.waitLoading();

		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(voidAction, voidReason, voidNote);

		orderCartPg.waitLoading();
	}	
	
	/**
	 * void a privilege order, start from LM main page, end to order cart page.
	 * 
	 * 
	 * @param orderNum
	 * @param voidAction
	 *            : Undo/Reverse
	 * @param voidReason
	 *            : reason of void
	 * @param voidNote
	 * 
	 */
	public void voidLandownerPriInstance(String orderNum, String voidPriName, String voidAction) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		gotoPrivilegeOrderDetailPage(orderNum);

		privOrderDetailsPage.voidOrReverseOrder();
		voidUndoVoidPg.waitLoading();

		// select privilege instance which will be voided.
		voidUndoVoidPg.selectPriByName(voidPriName);
		ajax.waitLoading();
		voidUndoVoidPg.waitLoading();

		voidUndoVoidPg.clickVoidOrUndoVoidSelectedTransaction();
		ajax.waitLoading();

		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(voidAction, "14 - Other", "Automation testing");
		browser.waitExists(voidUndoVoidPg, orderCartPg);
	}

	/**
	 * void a vehicle register order, start from LM main page, end to order cart
	 * page.
	 * 
	 * 
	 * @param orderNum
	 * @param voidAction
	 *            : Undo/Reverse
	 * @param voidReason
	 *            : reason of void
	 * @param voidNote
	 * 
	 */
	public void voidRegisterVehicleOrder(String orderNum, String voidReason,
			String voidNote) {

		LicMgrVehicleOrderDetailsPage vehicleOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget reverseConfirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsConfirmDialogWidget confirmDialog = OrmsConfirmDialogWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		gotoVehicleOrderDetailPage(orderNum);

		vehicleOrdDetailsPg.clickReverseOrder();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialog, reverseConfirmWidget);
		if (page == confirmDialog) {
			confirmDialog.clickOK();
			ajax.waitLoading();
			reverseConfirmWidget.waitLoading();
		}
		reverseConfirmWidget.setupConfirmInfo("NOT USE", voidReason, voidNote);// we
		// set
		// "NOT USE",
		// because
		// we do
		// not
		// need
		// set
		// this
		// field.
		orderCartPg.waitLoading();
	}

	/**
	 * The work flow starts from order cart page and ends at order summary page.
	 * 
	 * @param pay
	 * @param isPrinted
	 */
	public String processOrderCartToOrderSummaryPage(Payment pay,
			boolean isPrinted) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();

		logger.info("Process order cart to Order Summary Page.");

		this.processOrderToOrderSummary(pay);

		if (isPrinted && lmOrdSumPg.isPrintDocumentEnabled()) {
			logger.info("Print document for this order.");
			lmOrdSumPg.clickPrintDocuments();
			ajax.waitLoading();
			alert.setDismissible(false);
			alert.setBeforePageLoading(false);
			ajax.waitLoading();
			Object page = browser.waitExists(alert, printPg);
			if (page == alert) {
				alert.clickOK();
				ajax.waitLoading();
				printPg.waitLoading();
			}
			ajax.waitLoading();
			printPg.processPrint();
			ajax.waitLoading();
			lmOrdSumPg.waitLoading();
		}

		String ordID = lmOrdSumPg.getAllOrdNums();

		return ordID;
	}

	/**
	 * Process order cart. The work flow starts from order cart page and ends at
	 * license manager home page.
	 * 
	 * @param pay
	 * @param printRequired
	 * @return
	 */
	public String processOrderCart(Payment pay, boolean printRequired) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		String ordID = processOrderCartToOrderSummaryPage(pay, printRequired);

		logger.info("Finish Order");

		lmOrdSumPg.clickFinishButton();

		// confirmDialog.setDismissible(false);
		// confirmDialog.setBeforePageLoading(false);
		// Object page = browser.waitExists(confirmDialog, lmHomePg);
		browser.waitExists(confirmDialog, lmHomePg);
		// if (confirmDialog == page) {
		// confirmDialog.clickOK();
		// lmHomePg.waitExists();
		// }
		return ordID;
	}

	public void gotoCustomerOrderPage() {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerOrdersPage orderPg = LicMgrCustomerOrdersPage
				.getInstance();
		logger
				.info("Go to customer order sub table from customer detail page.");
		detailsPg.clickOrdersTab();
		ajax.waitLoading();
		orderPg.waitLoading();
	}
	
	/**
	 * Cancel order cart
	 */
	public void cancelCart() {
		LicenseMgrHomePage lmHomePage = LicenseMgrHomePage.getInstance();
		LicMgrTopMenuPage lmTopMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrEndCartPage lmEndCartPage = LicMgrEndCartPage.getInstance();

		logger.info("Cancel cart..");
		if (!lmHomePage.exists()) {
			if (lmTopMenuPage.isCartExists()) {
				lmTopMenuPage.clickCancelCart();
				lmEndCartPage.waitLoading();
				lmEndCartPage.clickOKToCancelCart();
				lmTopMenuPage.waitLoading();
			} else {
				logger.warn("Could not cancel cart. Order cart doesn't exist.");
			}
		}
	}

	public boolean tryToCancelCart() {
		LicenseMgrHomePage lmHomePage = LicenseMgrHomePage.getInstance();
		LicMgrTopMenuPage lmTopMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrEndCartPage lmEndCartPage = LicMgrEndCartPage.getInstance();

		logger.info("Trying to cancel cart..");
		try {
			if (!lmHomePage.exists()) {
				if (lmTopMenuPage.isCartExists()) {
					lmTopMenuPage.clickCancelCart();
					lmEndCartPage.waitLoading();
					lmEndCartPage.clickOKToCancelCart();
					lmTopMenuPage.waitLoading();
				} else {
					logger
							.warn("Could not cancel cart. Order cart doesn't exist.");
				}
			}

			return true;

		} catch (Exception e) {
			logger.warn("Failed to cancel cart due to " + e.getMessage());
			return false;
		}
	}

	/**
	 * go to privilege order history page.
	 */
	public void gotoPrivilegeOrderHistoryPage() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage
				.getInstance();
		privOrderDetailsPage.clickHistory();
		ajax.waitLoading();
		privOrderHistoryPg.waitLoading();
	}

	/**
	 * go back to privilege order detail page from privilege order history page.
	 */
	public void backPrivilegeOrderDetailPgFromHistoryPg() {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage
				.getInstance();
		privOrderHistoryPg.clickOK();
		ajax.waitLoading();
		privOrderDetailsPage.waitLoading();
	}

	/**
	 * Check one or more privilege exist(s) in system or not, identified by
	 * privilege code
	 * 
	 * @param schema
	 * @param codes
	 * @return
	 */
	public boolean checkPrivilegesExist(String schema, String... codes) {
		logger.info("Checking whether privilege(s) exist or not.");
		boolean exists = true;
		for (String code : codes) {
			exists = true;
			exists &= this.checkPrivilegeExisted(code, schema);
			if (!exists) {
				logger
						.error("Privilege(Code#="
								+ code
								+ ") doesn't exist in System, please run support or manually to add it.");
			} else
				logger.info("Privilege(Code#=" + code
						+ ") really exists in System.");
		}
		if (!exists) {
			throw new ErrorOnPageException(
					"Privilege doesn't exist, please refer the Error msg for detail info.");
		}

		return exists;
	}

	/**
	 * go to privilege order search page.
	 */
	public void gotoPrivilegeOrderSearchPage() {
		LicenseMgrHomePage lmHomePage = LicenseMgrHomePage.getInstance();

		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();

		logger.info("go to privlilege item search page");
		lmHomePage.clickOrders();
		privOrderSearchPage.waitLoading();
	}

	/**
	 * go to privilege order search page.
	 * 
	 * @param tabName
	 *            -- the tab name: 'Customers','Suspensions','Privileges' and no
	 *            others
	 */
	public void gotoCustomerMgrTabSubPage(String tabName) {
		LicenseMgrHomePage lmHomePage = LicenseMgrHomePage.getInstance();
		LicMgrCustomerMgrTabPage mrgTabPg = LicMgrCustomerMgrTabPage
				.getInstance();
		LicMgrPrivilegeItemSearchPage itemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		LicMgrCustomersSearchPage customSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		logger.info("go to " + tabName + " search page");
		lmHomePage.clickCustomers();
		ajax.waitLoading();
		mrgTabPg.waitLoading();
		if (tabName.equals("Customers")) {
			mrgTabPg.clickCustomersTab();
			ajax.waitLoading();
			customSearchPg.waitLoading();
		} else if (tabName.equals("Suspensions")) {
			mrgTabPg.clickSuspensionsTab();
			ajax.waitLoading();
			mrgTabPg.waitLoading();// TODO need to specific the suspension search
			// page.
		} else if (tabName.equals("Privileges")) {
			mrgTabPg.clickPrivilegesTab();
			ajax.waitLoading();
			itemSearchPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unknow Sub Table");
		}
	}

	/**
	 * go to privilege detail page.
	 * 
	 * @param priNumber
	 *            -- privilege number.
	 */
	public void gotoPrivilegeDetailPageFromPriSearchPg(String priNumber) {
		LicMgrPrivilegeItemSearchPage itemSearchPg = LicMgrPrivilegeItemSearchPage
				.getInstance();
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		logger.info("Go to Privilege(Priv#= " + priNumber + ") details page from Search List page.");
		List<String> searchTypes = itemSearchPg.getSearchTypes();
		String value1 = "Privilege #";
		String value2 = "Licence #";
		
		if(searchTypes.contains(value1)){
			itemSearchPg.searchPrivilege(value1, priNumber);
		}else if(searchTypes.contains(value2)){
			itemSearchPg.searchPrivilege(value2, priNumber);
		}else throw new ErrorOnPageException("Can't find matched search type.");
			
		itemSearchPg.clickPrivilegeNumber(priNumber);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * Report harvest in customer harvest sub page
	 * 
	 * @param harvest
	 * @return
	 */
	public String reportHarvest(Harvest harvest) {
		LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage
				.getInstance();
		LicMgrCustomerHarvestQuestionWidget questionWidget = LicMgrCustomerHarvestQuestionWidget
				.getInstance();

		logger.info("Report Harvest...");
		harvestPage.clickReportHarvest(harvest.privilegeNum);
		ajax.waitLoading();
		questionWidget.waitLoading();
		questionWidget.setupInfo(harvest);
		questionWidget.clickDone();
		ajax.waitLoading();
		harvestPage.waitLoading();
		String confirmationNum = harvestPage
				.getHarvestListInfo(harvest.privilegeNum).confirmationNum;

		return confirmationNum;
	}

	/**
	 * Edit reported harvest report in customer detail-harvest sub page
	 * 
	 * @param harvest
	 * @return
	 */
	public String editHarvestReport(Harvest harvest) {
		LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage
				.getInstance();
		LicMgrCustomerHarvestQuestionWidget questionWidget = LicMgrCustomerHarvestQuestionWidget
				.getInstance();

		logger
				.info("Edit harvest report(Harvest#=" + harvest.harvestNum
						+ ").");
		harvestPage.clickHarvestNum(harvest.harvestNum);
		ajax.waitLoading();
		questionWidget.waitLoading();
		questionWidget.setupInfo(harvest);
		questionWidget.clickDone();
		ajax.waitLoading();
		harvestPage.waitLoading();
		String confirmationNum = harvestPage
				.getHarvestListInfo(harvest.privilegeNum).confirmationNum;

		return confirmationNum;
	}

	/**
	 * The method used to retrieve payment info from database query by customer
	 * F_name and L_name and current date
	 * 
	 * @param schema
	 * @param f_name
	 *            first name
	 * @param l_name
	 *            last name
	 * @return a payment data collection added by pzhu for case:
	 *         AllocateReceivedPaymentToOrder
	 */
	public Payment getPaymentInfoByName(String schema, String f_name,
			String l_name) {
		db.resetSchema(schema);
		Payment pay = new Payment();
		String[] colNames = { "id", "pmt_amnt", "change", "status",
				"sales_chnl", "login", "location", "pmt_type_id" };
		String date = DateFunctions.formatDate(DateFunctions.getCurrentDate(),
				"dd-MMM-yy").toUpperCase();
		logger.info("Query Payment Info From DB.");
		String query = "select fp.id,fp.pmt_amnt,fc.PMT_AMNT as change, fp.status, fp.sales_chnl, du.login,d_loc.name as location,fptl.pmt_type_id from f_pmt fp,f_change fc,d_user_auth du,d_loc,f_pmt_type_loc fptl, c_cust cust where cust.cust_id=fp.cust_id "
				+ "and cust.f_name='"
				+ f_name
				+ "' and cust.l_name='"
				+ l_name
				+ "' and fp.pmt_date like '"
				+ date
				+ "' and fp.change_id=fc.id(+) and fp.user_id=du.id "
				+ "and fp.loc_id=d_loc.id and fp.pmt_type_loc_id=fptl.id order by fp.id desc";

		logger.debug("Execute query: " + query);
		List<String[]> list = db.executeQuery(query, colNames);

		if (list != null && list.size() > 0) {
			if (list.size() >= 2) { // Get the payment amount in the second
				pay.amount_1 = list.get(1)[1];
			}
			pay.paymentID = list.get(0)[0];
			pay.amount = list.get(0)[1];
			pay.changeAmount = list.get(0)[2] == null ? "0" : list.get(0)[2];
			pay.status = ConstantsInterpreter.getPaymentStatus(list.get(0)[3]);
			pay.salesChanl = ConstantsInterpreter
					.getSaleChannel(list.get(0)[4]);
			pay.collectUser = list.get(0)[5];
			pay.collectLocation = list.get(0)[6];
			pay.payType = ConstantsInterpreter.getPaymentType(list.get(0)[7]);
		} else {
			throw new ItemNotFoundException("No Payment Info Found.");
		}
		return pay;
	}

	public void newVehicleSaleToCustSearch(String vehicleType) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();

		logger.info("Make a new Vehicle Sale for " + vehicleType);

		homePg.selectNewVehicleType(vehicleType);
		homePg.clickGoInVehicles();
		ajax.waitLoading();
		custSearchPg.waitLoading();
	}

	/**
	 * Select customer for registration, the customer has an active alert, get
	 * alert text and return it.
	 * 
	 * @param customer
	 * @param vehicleType
	 * @return
	 */
	public String selectCustForRegis(Customer customer, String vehicleType) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrAlertPopupWidget alertWiget = LicMgrAlertPopupWidget
				.getInstance();

		logger
				.info("Select customer for registration, the customer has an active alert, get alert text and return it.");
		this.newVehicleSaleToCustSearch(vehicleType);
		custSearchPg.searchCust(customer);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();
		alertWiget.waitLoading();
		String text = alertWiget.getAlertInfo();
		alertWiget.clickCancel();
		ajax.waitLoading();
		custSearchPg.waitLoading();
		return text;
	}

	/**
	 * 1. New Vehicle from Vehicle Actions home page to vehicle details page 2.
	 * Set up vehicle details, and go to order cart page This flow started from
	 * home page, and ends at order cart page
	 * 
	 * @param customer
	 * @param vehicle
	 *            -- handle with type:Boat, Dealer
	 */
	public void registerVehicleToOrderCart(Customer customer, Vehicle vehicle) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrRegisterVehicleDetailsPage vehicleDetailPg = LicMgrRegisterVehicleDetailsPage
				.getInstance();
		LicMgrVehicleRegistrationWidget registrationWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrTransactionGroupsForChildVehiclesWidget childWidget = LicMgrTransactionGroupsForChildVehiclesWidget
				.getInstance();

		logger.info("New " + vehicle.type
				+ " from Vehicles Actions Home Page to Order Cart page.");

		this.newVehicleSaleToCustSearch(vehicle.type);

		custSearchPg.searchCust(customer);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();

		Object page = browser.waitExists(registrationWidget, vehicleDetailPg,
				ormsOrderCartPg);
		if (page == vehicleDetailPg) {
			logger.info("Register vehicle to Order Cart.");
			// for boat
			vehicleDetailPg.setupBoatDetails((BoatInfo) vehicle);
			vehicleDetailPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(childWidget, alert, registrationWidget);
			if (page == childWidget) {
				childWidget.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(alert, registrationWidget);
			}
			if (page == alert) {
				alert.clickOK();
				throw new ErrorOnPageException(
						"Purchase vehichle failed: No vehicle products are available for registration.");
			}
		}

		registrationWidget
				.selectRegistrationProduct(vehicle.registration.product);
		registrationWidget.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * Add new vehicle in order cart page. From : order cart page Back to :
	 * order cart page
	 * 
	 * @param customer
	 * @param vehicle
	 *            -- handle with type:Boat, Dealer
	 */
	public void addVehicleInOrderCartPage(Customer customer, Vehicle vehicle) {

		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrRegisterVehicleDetailsPage vehicleDetailPg = LicMgrRegisterVehicleDetailsPage
				.getInstance();
		LicMgrVehicleRegistrationWidget registration = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Add vehicle in Order Cart page.");
		Object pg = null;

		ormsOrderCartPg.clickGoAddNewVehicleForRegistration();
		ajax.waitLoading();
		custSearchPg.waitLoading();

		custSearchPg.searchCust(customer);
		custSearchPg.waitLoading();
		custSearchPg.selectFirstCustomer();

		ajax.waitLoading();

		Object page = browser.waitExists(vehicleDetailPg, ormsOrderCartPg);

		if (page == vehicleDetailPg) {
			logger.info("Register vehicle to Order Cart.");
			// for boat
			vehicleDetailPg.setupBoatDetails((BoatInfo) vehicle);
			vehicleDetailPg.clickOK();
			ajax.waitLoading();
			pg = browser.waitExists(alert, registration);
		}

		if ((pg != null) && (pg == alert)) {
			alert.clickOK();
			throw new ErrorOnDataException(
					"Purchase vehichle failed: No vehicle products are available for titling.");
		}

		String product = vehicle.registration.product;
		registration.selectRegistrationProduct(product);
		registration.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();

	}

	public void registerVehicleInCustDetailPage(Vehicle vehicle) {

		LicMgrCustomerVehiclesPage custVehiclePg = LicMgrCustomerVehiclesPage
				.getInstance();
		LicMgrRegisterVehicleDetailsPage vehicleDetailPg = LicMgrRegisterVehicleDetailsPage
				.getInstance();
		LicMgrVehicleRegistrationWidget registration = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrTransactionGroupsForChildVehiclesWidget childWidget = LicMgrTransactionGroupsForChildVehiclesWidget
				.getInstance();

		logger.info("Register vehicle from customer detail page.");
		custVehiclePg.selectNewVehicleType(vehicle.type);
		custVehiclePg.clickGoToNewVehicle();
		ajax.waitLoading();

		Object page = browser.waitExists(vehicleDetailPg, registration);

		if (page == vehicleDetailPg) {
			logger.info("Register vehicle to Order Cart.");
			// for boat
			vehicleDetailPg.setupBoatDetails((BoatInfo) vehicle);
			vehicleDetailPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(alert, childWidget, registration);
		}

		if ((page != null) && (page == alert)) {
			alert.clickOK();
			throw new ErrorOnDataException(
					"Purchase vehichle failed: No vehicle products are available for titling.");
		}

		if (page == childWidget) {
			// TODO select an specific transaction, but for now just keep the
			// default one
			childWidget.clickOK();
			ajax.waitLoading();
			registration.waitLoading();
		}

		String product = vehicle.registration.product;
		registration.selectRegistrationProduct(product);
		registration.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * Select customer for inspect vehicle, the customer has an active alert,
	 * get alert text and return it.
	 * 
	 * @param customer
	 * @param vehicleType
	 * @return
	 */
	public String selectCustForInspectVel(Customer customer, String vehicleType) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrCustomerDetailsPage detailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrAlertPopupWidget alertWiget = LicMgrAlertPopupWidget
				.getInstance();

		/* click request inspection */
		homePg.selectNewVehicleType(vehicleType);
		homePg.clickRequestInspection();
		ajax.waitLoading();
		custSearchPg.waitLoading();

		/* search customer */
		custSearchPg.searchCust(customer);
		custSearchPg.clickCustomerFirstNumer();
		ajax.waitLoading();
		alertWiget.waitLoading();
		String text = alertWiget.getAlertInfo();
		alertWiget.clickOK();
		ajax.waitLoading();
		detailPg.waitLoading();
		return text;
	}

	public void inspectVehicleToOrderCartFromHomePg(Customer customer,
			Vehicle vehicle) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrInspectionVehicleDetailsPage inspectionDetailPg = LicMgrInspectionVehicleDetailsPage
				.getInstance();
		LicMgrVehicleRegistrationWidget inspection = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrInspectionAssignOfficerWidget assignOfficerWgt = LicMgrInspectionAssignOfficerWidget
				.getInstance();

		/* click request inspection */
		homePg.selectNewVehicleType(vehicle.type);
		homePg.clickRequestInspection();
		ajax.waitLoading();
		custSearchPg.waitLoading();

		/* search customer */
		custSearchPg.searchCust(customer);
		if (custSearchPg.exists()) {
			custSearchPg.selectFirstCustomer();
		}
		ajax.waitLoading();
		inspectionDetailPg.waitLoading();

		/* inspection vehicle */
		inspectionDetailPg.setupInspectionDetailInfo(vehicle.inspection);
		inspectionDetailPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(alert, assignOfficerWgt, inspection);
		if (page == assignOfficerWgt) {
			assignOfficerWgt.clickOk();
			ajax.waitLoading();
			inspection.waitLoading();
		}
		if ((page != null) && (page == alert)) {
			alert.clickOK();
			throw new ErrorOnDataException(
					"Purchase vehichle failed: No vehicle products are available for inspection.");
		}

		String product = vehicle.inspection.getProduct();
		inspection.selectRegistrationProduct(product);

		inspection.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * Title a motor to order cart page This flow started from customer search
	 * page, and ends at order cart page
	 * 
	 * @param customer
	 * @param motor
	 */
	public void titleMotorFromCustSearchToOrderCart(Customer customer,
			MotorInfo motor) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrTitleVehicleDetailsPage vehicleDetailPg = LicMgrTitleVehicleDetailsPage
				.getInstance();
		LicMgrVehicleRegistrationWidget title = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		custSearchPg.searchCust(customer);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();
		vehicleDetailPg.waitLoading();
		vehicleDetailPg.setupMotorDetails(motor);
		vehicleDetailPg.clickOK();
		ajax.waitLoading();
		title.waitLoading();

		if (!StringUtil.isEmpty(motor.title.product)) {
			title.selectRegistrationProduct(motor.title.product);
		}
		title.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * title motor vehicle from Home page to Order Cart page
	 * 
	 * @param cust
	 * @param motor
	 */
	public void titleMotorToOrderCart(Customer cust, MotorInfo motor) {
		logger
				.info("Add New Motor from Vehicles Actions Home Page to Order Cart page.");

		this.newVehicleSaleToCustSearch("Motor");
		this.titleMotorFromCustSearchToOrderCart(cust, motor);
	}

	public void duplicateTitileVehicleToOrderCartFromRegistrationDetailPg(
			Vehicle vehicle, String duplicateRea) {
		// if (MiscFunctions.isQA24()) {
		// this.correctOrDuplicateTitileVehicleToOrderCartFromRegistrationDetailPg(vehicle,
		// "Replacement", duplicateRea);
		// }
		// else {
		this
				.correctOrDuplicateTitileVehicleToOrderCartFromRegistrationDetailPg(
						vehicle, "Duplicate", duplicateRea);
		// }
	}

	public void correctTitileVehicleToOrderCartFromRegistrationDetailPg(
			Vehicle vehicle, String correctRea) {
		this
				.correctOrDuplicateTitileVehicleToOrderCartFromRegistrationDetailPg(
						vehicle, "Corrected", correctRea);
	}

	public String setTitleToTransferable() {
		LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();

		logger.info("Set to Transferable for title# - "
				+ titleDetailsPage.getTitleNumber());
		titleDetailsPage.clickSetToTransferable();
		ajax.waitLoading();
		titleDetailsPage.waitLoading();

		return titleDetailsPage.getStatus();
	}

	public String surrenderTitle() {
		LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();

		logger.info("Surrender Title# - " + titleDetailsPage.getTitleNumber());
		titleDetailsPage.clickSurrenderTitle();
		ajax.waitLoading();
		titleDetailsPage.waitLoading();

		return titleDetailsPage.getStatus();
	}

	public String reactivateTitle() {
		LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();

		logger.info("Reactivate Title# - " + titleDetailsPage.getTitleNumber());
		titleDetailsPage.clickReactiveTitle();
		ajax.waitLoading();
		titleDetailsPage.waitLoading();

		return titleDetailsPage.getStatus();
	}

	/**
	 * set to tranferable for title in the vehicle detail page
	 * 
	 * @param prdname
	 */
	public String setTitleToTransferableByPrd(String prdname) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleTitleListPage titlePg = LicMgrVehicleTitleListPage
				.getInstance();

		logger.info("set titile: " + prdname + " to transferable...");
		/* go to title tab */
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		titlePg.waitLoading();

		/* select active status title */
		titlePg.showActiveTitles();

		/* set active title to trasferable */
		String titleId = titlePg.getTitleItemIdByPrdName(prdname);
		titlePg.transferableTitle(titleId);
		return titleId;
	}

	/**
	 * surrender title in the vehicle detail page
	 * 
	 * @param prdname
	 */
	public String surrenderTitleFromTitleList(String prdname) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleTitleListPage titlePg = LicMgrVehicleTitleListPage
				.getInstance();

		logger.info("Surrender titile: " + prdname + " ...");
		/* go to title tab */
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		titlePg.waitLoading();
		titlePg.showActiveTitles();
		/* surrender active title */
		String titleId = titlePg.getTitleItemIdByPrdName(prdname);
		titlePg.surrenderTitle(titleId);
		return titleId;
	}

	public String surrenderTitleFromListByTitleNum(String titleNum) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleTitleListPage titlePg = LicMgrVehicleTitleListPage
				.getInstance();

		logger.info("Surrender titile#('" + titleNum + "').");
		/* go to title tab */
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		titlePg.waitLoading();
		titlePg.showAll();
		/* surrender active title */
		String titleId = titlePg.getTitleItemIdByTitleNum(titleNum);
		titlePg.surrenderTitle(titleId);
		return titleId;
	}

	/**
	 * reactive title in the vehicle detail page
	 * 
	 * @param prdname
	 */
	public void reactiveTitleByPrd(String prdname) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleTitleListPage titlePg = LicMgrVehicleTitleListPage
				.getInstance();

		logger.info("reactive titile: " + prdname + " ...");
		/* go to title tab */
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		titlePg.waitLoading();

		/* select transferable status and surrender status title */
		titlePg.unselectActiveStautsCheckBox();
		titlePg.selectTransferableStatusCheckBox();
		titlePg.selectSurrenderedStatusCheckBox();
		titlePg.clickGobutton();
		ajax.waitLoading();

		/* reactive title */
		titlePg.selectTitleItmeRadioByPrdName(prdname);
		titlePg.clickReactiveTitle();
		ajax.waitLoading();
		titlePg.waitLoading();

	}

	/**
	 * corrected or duplicated title vehicle to 'Title Product(s)' selection
	 * widget this method starts from Vehicle details page, and ends with 'Title
	 * Product(s)' widget
	 * 
	 * @param vehicle
	 */
	public void correctOrDuplicateTitleVehicleToTitleProductSelectionWidget() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrCorrectDuplicateVehicleTitleProductWidget correctOrDupTitlePg = LicMgrCorrectDuplicateVehicleTitleProductWidget
				.getInstance();

		logger
				.info("Corrected/Duplicated title to title product selection widget.");
		vehicleDetailsPg.clickTitleButton();
		ajax.waitLoading();
		correctOrDupTitlePg.waitLoading();
	}

	/**
	 * Correct or duplicate title
	 * 
	 * @param vehicle
	 * @param type
	 * @param correctReason
	 */
	public void correctOrDuplicateTitileVehicleToOrderCartFromRegistrationDetailPg(
			Vehicle vehicle, String type, String correctReason) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrCorrectDuplicateVehicleTitleProductWidget correctOrDupTitlePg = LicMgrCorrectDuplicateVehicleTitleProductWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrSelectTransactionReasonWidget correctReasonPg = LicMgrSelectTransactionReasonWidget
				.getInstance();

		logger.info(type + " titile registration to order cart.");
		vehicleDetailsPg.clickTitleButton();
		ajax.waitLoading();
		correctOrDupTitlePg.waitLoading();

		String product = vehicle.title.product.replaceAll(" - ", "-");
		correctOrDupTitlePg.selectRegistrationProduct(type, product);
		correctOrDupTitlePg.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(correctReasonPg, ormsOrderCartPg);
		if (correctReasonPg == pages) {
			if (null != correctReason) {
				correctReasonPg.selectReason(correctReason);
			}
			correctReasonPg.clickOK();
			ajax.waitLoading();
			ormsOrderCartPg.waitLoading();
		}
	}

	/**
	 * This method is used to add manufacturer when register an vehicle. Start
	 * from LM main page, end to vehicle details page
	 * 
	 * @param customer
	 * @param manufacturer
	 * 
	 */
	public String addVehicleTypeManufacturer(Customer cust,
			VehicleTypeManufacturer manufacturer) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrRegisterVehicleDetailsPage vehicleDetailPg = LicMgrRegisterVehicleDetailsPage
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrAddVehicleTypeManufacturerWidget manufacturerWidget = LicMgrAddVehicleTypeManufacturerWidget
				.getInstance();

		logger.info("Adding new manufacturer from LM main page.");

		this.newVehicleSaleToCustSearch(manufacturer.getVehicleType());

		custSearchPg.searchCust(cust);
		custSearchPg.waitLoading();
		custSearchPg.selectFirstCustomer();

		ajax.waitLoading();

		Object page = browser.waitExists(vehicleDetailPg, ormsOrderCartPg);

		if (page instanceof LicMgrRegisterVehicleDetailsPage) {
			logger.info("Enter into LicMgrRegisterVehicleDetailsPage.");
			vehicleDetailPg.clickAddNewManufacturer();
			ajax.waitLoading();

			manufacturerWidget.waitLoading();
			manufacturerWidget.setMIC(manufacturer.getMIC());
			manufacturerWidget.setManufacturerName(manufacturer
					.getManufacturerName());
			manufacturerWidget.setManufacturerPrintName(manufacturer
					.getPrintName());

			manufacturerWidget.clickOK();
			ajax.waitLoading();

			page = browser.waitExists(manufacturerWidget, vehicleDetailPg);

			if (page instanceof LicMgrAddVehicleTypeManufacturerWidget) {
				String errorMsg = manufacturerWidget.getErrorMsg();
				manufacturerWidget.clickCancel();
				ajax.waitLoading();
				vehicleDetailPg.waitLoading();
				return errorMsg;
			} else {
				return StringUtil.EMPTY;
			}

		} else {
			throw new ErrorOnPageException(
					"Can not enter into LicMgrRegisterVehicleDetailsPage, Failed to add new manufacturer!");
		}
	}

	/**
	 * title vehicle to 'Title Product(s)' selection widget this method starts
	 * from Vehicle details page, and ends with 'Title Product(s)' widget
	 * 
	 * @param vehicle
	 */
	public void titleVehicleToTitleProductSelectionWidget(Vehicle vehicle) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrAddVehicleLienWidget addVehicleLienWidget = LicMgrAddVehicleLienWidget
				.getInstance();
		LicMgrVehicleRegistrationWidget titleWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrVehicleLienCompanyDetailsWidget lienCompanyWgt = LicMgrVehicleLienCompanyDetailsWidget
				.getInstance();

		logger.info("Titile vehicle to 'Title Product(s)' widget.");
		vehicleDetailsPg.clickTitleButton();
		ajax.waitLoading();
		addVehicleLienWidget.waitLoading();
		String vehicleValue = "";
		if ((vehicle instanceof BoatInfo)) {
			vehicleValue = vehicle.title.boatValue;
		} else if (vehicle instanceof MotorInfo) {
			vehicleValue = vehicle.title.getMotorValue();
		} else if (vehicle instanceof DealerInfo) {
			vehicleValue = vehicle.title.getDealerValue();
		}
		addVehicleLienWidget.setVehicleValue(vehicleValue);

		if (null != vehicle.title.lienInfo) {
			addVehicleLienWidget.setLineInfo(vehicle.title);
			if (null != vehicle.title.lienInfo.getLienCompanyDetailsInfo()) {
				addVehicleLienWidget.clickAddLineDetails();
				ajax.waitLoading();
				lienCompanyWgt.waitLoading();
				lienCompanyWgt.setLienCompanyDetails(vehicle.title.lienInfo
						.getLienCompanyDetailsInfo());
				lienCompanyWgt.clickOK();
				ajax.waitLoading();
				addVehicleLienWidget.waitLoading();
			}
		}
		addVehicleLienWidget.clickOK();
		ajax.waitLoading();
		titleWidget.waitLoading();
	}

	public void selectTitleVehicleProductToOrderCart(Vehicle vehicle) {
		LicMgrVehicleRegistrationWidget titleWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrSelectTransactionReasonWidget correctReasonPg = LicMgrSelectTransactionReasonWidget
				.getInstance();

		logger.info("Select vehicle product - " + vehicle.title.product
				+ " to Order Cart page.");
		String product = vehicle.title.product;
		if (!StringUtil.isEmpty(product)) {
			if (StringUtil.isEmpty(vehicle.title.purchaseType)) {
				throw new ErrorOnPageException(
						"Please assign value for Purchase Type.");
			}
			logger.info("Titile vehicle(product=" + product
					+ ") to order cart.");
			titleWidget.selectRegistrationProduct(vehicle.title.purchaseType,
					product);
		}
		titleWidget.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(correctReasonPg, ormsOrderCartPg);
		if (correctReasonPg == pages) {
			correctReasonPg.selectReason(vehicle.operationReason);
			correctReasonPg.clickOK();
			ajax.waitLoading();
			ormsOrderCartPg.waitLoading();
		}
	}

	/**
	 * This method is used to title vehicle to order cart Start at registration
	 * vehicle detail page, end at order cart page
	 * 
	 * @param vehicle
	 */
	public void titleVehicleToOrderCartFromVehicleDetailPage(Vehicle vehicle) {
		logger.info("Titile vehicle to order cart.");
		titleVehicleToTitleProductSelectionWidget(vehicle);
		selectTitleVehicleProductToOrderCart(vehicle);
	}

	public void duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(
			String type, String productName) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleRegistrationWidget registrationPg = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger
				.info("Duplicate registration vehicle to order cart page from registration detail page.");
		vehicleDetailsPg.clickRegistration();
		ajax.waitLoading();
		registrationPg.waitLoading();
		registrationPg.selectRegistrationProduct(type, productName);
		registrationPg.clickOK();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * Select customer for transfer vehicle registration, the customer has an
	 * active alert, get alert text and return it.
	 * 
	 * @param customer
	 * @return
	 */
	public String selectCustForTransferVel(Customer customer) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrAlertPopupWidget alertWiget = LicMgrAlertPopupWidget
				.getInstance();

		vehicleDetailsPg.clickTransferButton();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, custSearchPg);
		if (confirmDialogWidget == page) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			custSearchPg.waitLoading();
		}

		custSearchPg.searchCust(customer);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();
		alertWiget.waitLoading();
		String text = alertWiget.getAlertInfo();
		alertWiget.clickCancel();
		ajax.waitLoading();
		custSearchPg.waitLoading();
		return text;
	}

	/**
	 * transfer vehicle to transfer details page this work flow starts with
	 * Vehicle Details page, ends with transfer Vehicle Details or Transfer
	 * Motor widget
	 * 
	 * @param customer
	 */
	public void transferVehicleToDetailsPage(Customer customer) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrTransferMotorWidget transferMotorWgt = LicMgrTransferMotorWidget
				.getInstance();
		LicMgrTransferDetailPage transferDetailPg = LicMgrTransferDetailPage
				.getInstance();

		logger
				.info("----Transfer vehicle to TransferDetails page from Vehicle Detail page.");
		vehicleDetailsPg.clickTransferButton();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, custSearchPg);
		if (confirmDialogWidget == page) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			custSearchPg.waitLoading();
		}

		custSearchPg.searchCust(customer);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();
		browser.waitExists(transferMotorWgt, transferDetailPg);
	}

	/**
	 * setup transfer vehicle details info at transfer vehiclde details page,
	 * and ends with Registration/Title product selection widget
	 * 
	 * @param vehicle
	 */
	public void setupTransferVehicleDetailInfoToProductSelectionWidget(
			Vehicle vehicle) {
		LicMgrVehicleRegistrationWidget registrationWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		LicMgrTransferMotorWidget transferMotorWgt = LicMgrTransferMotorWidget
				.getInstance();
		LicMgrTransferDetailPage transferDetailPg = LicMgrTransferDetailPage
				.getInstance();

		logger
				.info("----Setup transfer vehiclde details info in TransferDetails page.");
		if (transferMotorWgt.exists()) {
			for (int i = 0; i < ((BoatInfo) vehicle).motors.size(); i++) {
				transferMotorWgt
						.selectMotorTransferYesRadioBox(((BoatInfo) vehicle).motors
								.get(i).id);
				transferMotorWgt.clickOK();
				ajax.waitLoading();
				transferDetailPg.waitLoading();
			}
		}

		transferDetailPg.setTransferDetailsInfo(vehicle);
		transferDetailPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(registrationWidget, transferDetailPg);
		if (transferDetailPg == page) {
			if (null != ((BoatInfo) vehicle).transferMotors
					&& ((BoatInfo) vehicle).transferMotors.size() > 0) {
				for (int i = 0; i < ((BoatInfo) vehicle).transferMotors.size(); i++) {
					if (i != 0) {
						transferDetailPg.clickAdditionalMotor();
						ajax.waitLoading();
					}
					transferDetailPg
							.setTransferMotorInfo(((BoatInfo) vehicle).transferMotors
									.get(i));// TODO mutiple sutiation
				}
			}
			transferDetailPg.clickOK();
			ajax.waitLoading();
			registrationWidget.waitLoading();
		}
	}

	public void setupTransferMotorDetailInfoToHomePage(MotorInfo motor) {
		LicMgrTransferDetailPage transferDetailsPage = LicMgrTransferDetailPage
				.getInstance();
		LicMgrAlertDialogWidget alertDialog = LicMgrAlertDialogWidget
				.getInstance();

		// TODO Setup motor info, and New Co-Owner details info
		transferDetailsPage.clickOK();
		ajax.waitLoading();
		alertDialog.waitLoading();
		alertDialog.clickOK();
		ajax.waitLoading();
		LicenseMgrHomePage.getInstance().waitLoading();
	}

	/**
	 * select transfer products(including registration, title products) at
	 * Registration/Title product selection widget to order cart
	 * 
	 * @param registrationProduct
	 * @param titleProduct
	 */
	public void selectTransferProductsToOrderCart(String registrationProduct,
			String titleProduct) {
		LicMgrVehicleRegistrationWidget registrationWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();

		logger
				.info("----Select transfer Registration/Title products in selection widget to Order Cart.");
		if (!StringUtil.isEmpty(registrationProduct)) {
			registrationWidget.selectRegistrationProduct(registrationProduct);
		}
		if (!StringUtil.isEmpty(titleProduct)) {
			registrationWidget.selectRegistrationProduct(titleProduct);
		}

		registrationWidget.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	/**
	 * 
	 * @param customer
	 * @param vehicle
	 */
	public void transferVehicleToOrderCartFromDetailsPage(Customer customer,
			Vehicle vehicle) {
		logger
				.info("Transfer vehicle to Order Cart from vehciel details page.");

		transferVehicleToDetailsPage(customer);
		if (vehicle instanceof BoatInfo) {
			setupTransferVehicleDetailInfoToProductSelectionWidget(vehicle);
		} else if (vehicle instanceof DealerInfo) {
			LicMgrTransferDetailPage.getInstance().clickOK();
			ajax.waitLoading();
			LicMgrVehicleRegistrationWidget.getInstance().waitLoading();
		}
		selectTransferProductsToOrderCart(vehicle.registration.product,
				vehicle.title.product);
	}

	public void transferMotorDealerToVehicleDetailsPageFromDetailsPage(
			Customer customer, MotorInfo motorOrDealer) {
		logger
				.info("Transfer motor to Vehicle Details page from Vehicle Details page.");
		transferVehicleToDetailsPage(customer);
		setupTransferMotorDetailInfoToHomePage(motorOrDealer);
	}

	public void reverseTransferMotor() {
		LicMgrVehicleDetailPage vehicleDetailsPage = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		LicMgrAlertDialogWidget alertWidget = LicMgrAlertDialogWidget
				.getInstance();

		logger.info("Reverse transfer motor at vehicle details page.");
		vehicleDetailsPage.clickReverseTransfer();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		alertWidget.waitLoading();
		alertWidget.clickOK();
		ajax.waitLoading();
		vehicleDetailsPage.waitLoading();
	}

	/**
	 * reverse transfer vehicle registration from vehicle details page to order
	 * cart page
	 * 
	 * @param reason
	 * @param note
	 */
	public void reverseTransferToOrderCartFromDetailsPage(String reason,
			String note) {
		LicMgrVehicleDetailPage detailsPage = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget reverseWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger
				.info("Reverse transfer for vehicle registration to Order Cart from vehicle detials page.");
		detailsPage.clickReverseTransfer();
		ajax.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		reverseWidget.waitLoading();
		reverseWidget.selectVoidOrReverseReason(reason);
		reverseWidget.setNotes(note);
		reverseWidget.clickOK();
		ajax.waitLoading();
		cartPage.waitLoading();
	}

	/**
	 * Go to return documents page by selecting 'Return Documents' opinion from
	 * top menu admin drop down list
	 */
	public void gotoReturnDocumentPageFromHome() {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();

		logger.info("Goto privielge return documents page from home page.");
		if (!topMenuPage.getAdminValue().equals("Return Documents")) {
			topMenuPage.selectAdminOptions("Return Documents");
		} else {
			topMenuPage.clickAdmin();
		}
		returnDocPage.waitLoading();
	}

	/**
	 * Search return document info from return document list page
	 * 
	 * @param returnDocOrdInfo
	 */
	public void searchReturnDocumentOrderInfoFromReturnDocumentListPage(
			ReturnDocumentOrderInfo returnDocOrdInfo) {
		LicMgrPrivilegeReturnDocumentPage privilegeReturnDocumentPg = LicMgrPrivilegeReturnDocumentPage
				.getInstance();

		logger.info("Search return document order info.");
		privilegeReturnDocumentPg
				.searchReturnDocumentOrderInfo(returnDocOrdInfo);
		privilegeReturnDocumentPg.clickGo();
		ajax.waitLoading();
		privilegeReturnDocumentPg.waitLoading();
	}

	/**
	 * Go to Interstate contact list page from top menu admin drop down list
	 */
	public void gotoInterstateContactPageFromHome() {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrInterstateContactListPage interstateContactPg = LicMgrInterstateContactListPage
				.getInstance();

		logger.info("Goto LicMgrInterstateContactListPage from home page.");
		if (!topMenuPage.getAdminValue().equals("Interstate Contact")) {
			topMenuPage.selectAdminOptions("Interstate Contact");
		} else {
			topMenuPage.clickAdmin();
		}
		interstateContactPg.waitLoading();

	}

	/**
	 * Go to Warehouse Inventory list page from top menu admin drop down list
	 */
	public void gotoWarehouseInventoryPageFromHome() {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrWarehouseInventoryListPage inventoryPg = LicMgrWarehouseInventoryListPage
				.getInstance();

		logger.info("Goto Warehouse Inventory from home page.");
		if (!topMenuPage.getAdminValue().equals("Warehouse Inventory")) {
			topMenuPage.selectAdminOptions("Warehouse Inventory");
		} else {
			topMenuPage.clickAdmin();
		}
		inventoryPg.waitLoading();

	}

	/**
	 * @param contact
	 * @return errorMsg : if there is no msg, will return empty string.
	 * 
	 *         add interstate contact from interstate contact list page, return
	 *         error msg corresponding to.
	 */
	public String addInterstateContact(InterstateContactInfo contact) {
		LicMgrInterstateContactListPage interstateContactPg = LicMgrInterstateContactListPage
				.getInstance();
		LicMgrInterstateContactDetailWidget contactDetailPg = LicMgrInterstateContactDetailWidget
				.getInstance();

		interstateContactPg.clickAddInterstateContact();
		ajax.waitLoading();
		contactDetailPg.waitLoading();

		contactDetailPg.selectState(contact.getState());
		contactDetailPg.setContactEmails(contact.getEmails());

		contactDetailPg.clickOK();
		ajax.waitLoading();

		String errorMsg = "";
		Object obj = browser.waitExists(contactDetailPg, interstateContactPg);
		if (obj instanceof LicMgrInterstateContactDetailWidget) {
			errorMsg = contactDetailPg.getErrorMsg();
			logger.info("[addInterstateContact] got error: " + errorMsg);
			contactDetailPg.clickCancel();
			ajax.waitLoading();
			interstateContactPg.waitLoading();
			return errorMsg;
		} else {
			// interstateContactPg.waitExists();
			return StringUtil.EMPTY;
		}

	}

	/**
	 * @param id
	 *            : which you want to be updated.
	 * @param newEmails
	 *            : emails which you want contact to be updated to.
	 * @return errorMsg : if there is no error msg, will return empty string.
	 * 
	 *         edit interstate contact from interstate contact list page(by ID),
	 *         return error msg corresponding to.
	 */
	public String editInterstateContact(String id, List<String> newEmails) {
		LicMgrInterstateContactListPage interstateContactPg = LicMgrInterstateContactListPage
				.getInstance();
		LicMgrInterstateContactDetailWidget contactDetailPg = LicMgrInterstateContactDetailWidget
				.getInstance();

		logger.info("Try to select interstate contact ID: " + id);
		interstateContactPg.clickContactByID(id);

		ajax.waitLoading();
		contactDetailPg.waitLoading();

		contactDetailPg.setContactEmails(newEmails);
		contactDetailPg.clickOK();
		ajax.waitLoading();

		String errorMsg = "";
		Object obj = browser.waitExists(contactDetailPg, interstateContactPg);
		if (obj instanceof LicMgrInterstateContactDetailWidget) {
			errorMsg = contactDetailPg.getErrorMsg();
			logger.info("[addInterstateContact] got error: " + errorMsg);
			contactDetailPg.clickCancel();
			ajax.waitLoading();
			interstateContactPg.waitLoading();
			return errorMsg;
		} else {
			// interstateContactPg.waitExists();
			return StringUtil.EMPTY;
		}

	}

	/**
	 * Return specific privilege document. This method starts with home page and
	 * ends with privilege return documents page
	 * 
	 * @param orderNum
	 */
	public void returnPrivilegeDocument(String orderNum) {
		LicMgrPrivilegeReturnDocumentPage returnDocPage = LicMgrPrivilegeReturnDocumentPage
				.getInstance();
		LicMgrPrivilegeReturnDocumentWidget returnWidget = LicMgrPrivilegeReturnDocumentWidget
				.getInstance();

		logger.info("Return document of privilege(Order#=" + orderNum + ").");
		this.gotoReturnDocumentPageFromHome();
		returnDocPage.searchReturnDocumentByOrdNum(orderNum);
		returnDocPage.returnDocument(orderNum);
		Object page = browser.waitExists(returnWidget, returnDocPage);
		if (page == returnWidget) {
			returnWidget.clickOK();
			ajax.waitLoading();
			returnDocPage.waitLoading();
		}
	}

	public boolean closeFinSession(String finSessID, String pinNum,
			String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		LicMgrTopMenuPage lmTopMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();
		OrmsEnterPinNumPopupPage pinPopupPg = OrmsEnterPinNumPopupPage
				.getInstance();

		logger.info("Closing Fin Session.");
		lmTopMenuPg.waitLoading();
		lmTopMenuPg.selectFinancialsOptions("Fin Sessions & Deposits");
		ormsFinSessPg.waitLoading();

		this.closeFinSession(finSessID, cashTotalOnHand,
				nonCashTotalOnHand, note);
		Object page = browser.waitExists(pinPopupPg, ormsFinSessPg);
		if (page == pinPopupPg) {
			pinPopupPg.enterPIN(pinNum);
			pinPopupPg.clickOK();
		}
		ormsFinSessPg.waitLoading();
		logger.info("Close FinSession ID is " + finSessID + ".");
		return true;

	}

	/**
	 * Check the document templates whether is exist or not if not exist add a
	 * new one.
	 * 
	 * @param docTemplateInfo
	 *            -document info.
	 */
	public void checkAndAddDocumentTem(DocumentTemplateInfo docTemplateInfo) {
		LicMgrDocumentTemplatesConfigurationPage docPage = LicMgrDocumentTemplatesConfigurationPage
				.getInstance();

		this.gotoDocumentTemplateConfigPgFromTopMenu();
		logger
				.info("go to Document templates assignment page from document template list page");

		boolean isExist = docPage
				.checnDocTemplatesExist(docTemplateInfo.docTemplateName);
		if (!isExist) {
			this.addDocumentTemplate(docTemplateInfo, true);
		}
	}
	
	public void gotoCustomerPrivilegesPage() {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerPrivilegePage privilegePg = LicMgrCustomerPrivilegePage.getInstance();
		logger.info("Go to customer licenses sub table from customer detail page.");
		detailsPg.clickPrivilegesTab();
		ajax.waitLoading();
		privilegePg.waitLoading();
	}

	/**
	 * go to privilege detail page from customer privilege page.
	 * 
	 * @param priNumber
	 *            -privilege number.
	 */
	public void gotoPrivilegeDetailPgFromCustDetialsPg(String priNumber) {
		LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
				.getInstance();
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage
				.getInstance();

		gotoCustomerPrivilegesPage();
		custPrivilegPg.clickPrivilege(priNumber);
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * Go to daily sales activity sub page from agent detail page
	 * 
	 * @param tabName
	 *            -- Summary|Transaction|PaymentType|User|Register
	 */
	public void gotoDailySalesActivitySubPgFromAgentDetailPg(String tabName) {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreDaliySalesActivityCommonPage commonPg = LicMgrStoreDaliySalesActivityCommonPage
				.getInstance();
		LicMgrStoreActivitySummarySubPage activitySummaryPg = LicMgrStoreActivitySummarySubPage
				.getInstance();
		LicMgrStoreActivityTransactionSubPage activityTransPg = LicMgrStoreActivityTransactionSubPage
				.getInstance();
		LicMgrStoreActivityPaymentTypeSubPage paymentTypePg = LicMgrStoreActivityPaymentTypeSubPage
				.getInstance();
		LicMgrStoreActivityUserSubPage userPg = LicMgrStoreActivityUserSubPage
				.getInstance();
		LicMgrStoreActivityRegisterSubPage registerPg = LicMgrStoreActivityRegisterSubPage
				.getInstance();
		LicMgrStoreActivityCustomerSubPage custPg = LicMgrStoreActivityCustomerSubPage
				.getInstance();

		if (!tabName
				.matches("Summary|Transaction|PaymentType|User|Customer|Register")) {
			throw new ErrorOnDataException("Table name was wrong!");
		}

		logger
				.info("Go to Daily Sales Activity Summary Page from Agent Detail page.");
		storeDetailPg.clickDailySalesActivity();
		ajax.waitLoading();
		activitySummaryPg.waitLoading();

		if (tabName.equalsIgnoreCase("Transaction")) {
			logger.info("Go to Daily Sales Activity by " + "tabName"
					+ " Page from Summary Page.");
			commonPg.clickShowActivityTransaction();
			ajax.waitLoading();
			activityTransPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("PaymentType")) {
			logger.info("Go to Daily Sales Activity by " + "tabName"
					+ " Page from Summary Page.");
			commonPg.clickShowActivityByPaymentType();
			ajax.waitLoading();
			paymentTypePg.waitLoading();
		} else if (tabName.equalsIgnoreCase("User")) {
			logger.info("Go to Daily Sales Activity by " + "tabName"
					+ " Page from Summary Page.");
			commonPg.clickShowActivityByUser();
			ajax.waitLoading();
			userPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Register")) {
			logger.info("Go to Daily Sales Activity by " + "tabName"
					+ " Page from Summary Page.");
			commonPg.clickShowActivityByRegister();
			ajax.waitLoading();
			registerPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Customer")) {
			logger.info("Go to Daily Sales Activity by " + "tabName"
					+ " Page from Summary Page.");
			commonPg.clickShowActivityByCustomer();
			ajax.waitLoading();
			custPg.waitLoading();
		}
	}

	public void gotoIdentifyCustomerPage() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrIdentifyCustomerPage identifyPage = LicMgrIdentifyCustomerPage
				.getInstance();

		logger.info("Go to Identify Customer page from home page.");
		homePg.clickPurchasePrivilege();
		identifyPage.waitLoading();
	}

	public void gotoAgentDetailPgFromDailySalesActivitySubPg() {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreDaliySalesActivityCommonPage activityPg = LicMgrStoreDaliySalesActivityCommonPage
				.getInstance();

		logger
				.info("Go to Agent Detail page from Daily Sales Activity sub Page");
		activityPg.clickReturnToAgentDetails();
		ajax.waitLoading();
		storeDetailPg.waitLoading();
	}

	/**
	 * go to customer order detail page from customer details page.
	 * 
	 * @param orderNum
	 *            -- number of order.
	 */
	public void gotoCustOrderDetailsPgFromCustDetailsPg(String orderNum) {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerOrdersPage custOrderPg = LicMgrCustomerOrdersPage
				.getInstance();
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVehicleOrderDetailsPage vehiOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();

		logger
				.info("Go to order detail page from customde detail page by order number "
						+ orderNum);

		custDetailPg.clickOrdersTab();
		ajax.waitLoading();
		custOrderPg.waitLoading();
		custOrderPg.clickOrderNumber(orderNum);
		ajax.waitLoading();
		browser.waitExists(privOrderDetailsPage, vehiOrderDetailsPage);
	}

	/**
	 * go to privileges tab page.
	 */
	public void gotoPrivilegeTabPage() {
		this.gotoCustomerMgrTabSubPage("Privileges");
	}

	public void gotoAgentAssignmentsSubPgFromProductDetailPg(Object page) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage
				.getInstance();
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrSupplyProductDetailsPage supplyDetailsPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrLotteryProductDetailsPage lotteryDetailsPg = LicMgrLotteryProductDetailsPage.getInstance();
		
		logger.info("Go to store assignments page from " + page);

		if (page == privilegeDetailPg) {
			LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage
					.getInstance();
			privilegeDetailPg.clickStoreAssignmentsTab();
			ajax.waitLoading();
			privilegeStoreAssignmentsPg.waitLoading();
		} else if (page == vehicleDetailPg) {
			LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentsPg = LicMgrVehicleProductStoreAssignmentsPage
					.getInstance();
			vehicleDetailPg.clickStoreAssignmentsTab();
			ajax.waitLoading();
			vehicleStoreAssignmentsPg.waitLoading();
		} else if (page == consumableDetailsPg) {
			LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage
					.getInstance();
			consumableDetailsPg.clickAgentAssignmentTab();
			ajax.waitLoading();
			consumableStoreAssignmentPg.waitLoading();
		} else if (page == supplyDetailsPg) {
			LicMgrSupplyAgentAssignmentsPage supplyStoreAssignmentPg = LicMgrSupplyAgentAssignmentsPage
					.getInstance();
			supplyDetailsPg.clickAgentAssignmentTab();
			ajax.waitLoading();
			supplyStoreAssignmentPg.waitLoading();
		} else if (page == lotteryDetailsPg) {
			LicMgrLotteryStoreAssignmentsPage lotteryStoreAssignmentPg = LicMgrLotteryStoreAssignmentsPage.getInstance();
			lotteryDetailsPg.clickAgentAssignmentsTab();
			ajax.waitLoading();
			lotteryStoreAssignmentPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unexpected page");
		}
	}

	/**
	 * Assign supply to stores thru location class in Supply Store Assignments
	 * Page sub of supply Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are used to check to do assigning action
	 */
	public void assignSupplyToStoresThruLocationClass(String... locationClasses) {
		LicMgrSupplyProductDetailsPage supplyDetailsPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrSupplyAgentAssignmentsPage supplyStoreAssignmentPg = LicMgrSupplyAgentAssignmentsPage
				.getInstance();

		logger
				.info("Assign supply to stores thru Location Class in privilege store assignments page.");
		supplyDetailsPg.clickAgentAssignmentTab();
		ajax.waitLoading();
		supplyStoreAssignmentPg.waitLoading();

		for (String locationClass : locationClasses) {
			supplyStoreAssignmentPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		supplyStoreAssignmentPg.clickAssignToStoresInSelectedLocationClasses();
		ajax.waitLoading();
		supplyStoreAssignmentPg.waitLoading();
	}

	/**
	 * Un-Assign supply from stores thru location class in supply Store
	 * Assignments Page sub of supply Detail page
	 * 
	 * @param locationClasses
	 *            - Location Class is/are to check to do un-assigning action
	 */
	public void unassignSupplyFromStoresThruLocationClass(
			String... locationClasses) {
		LicMgrSupplyProductDetailsPage supplyDetailsPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrSupplyAgentAssignmentsPage supplyStoreAssignmentPg = LicMgrSupplyAgentAssignmentsPage
				.getInstance();

		logger
				.info("Unassign supply from stores thru Location Class in supply store assignments page.");
		if (!supplyStoreAssignmentPg.exists()) {
			supplyDetailsPg.clickAgentAssignmentTab();
			supplyStoreAssignmentPg.waitLoading();
		}

		for (String locationClass : locationClasses) {
			supplyStoreAssignmentPg
					.selectLocationClassCheckboxByName(locationClass);
		}
		supplyStoreAssignmentPg
				.clickUnassignFromStoresInSelectedLocationClasses();
		ajax.waitLoading();
		supplyStoreAssignmentPg.waitLoading();
	}

	/**
	 * This method executes the work flow of entering Fee details page in order
	 * cart. The work flow starts from order cart and ends in fee details page
	 * 
	 * @param resID
	 *            - the reservation number
	 */
	public void gotoFeeDetailsPageInCart(String resID) {
		this.gotoFeeDetailsPageInCart(resID, true);
	}

	/**
	 * This method executes the work flow of entering Fee details page in order
	 * cart. The work flow starts from order cart and ends in fee details page
	 * 
	 * @param resID
	 * @param notCampOrder
	 *            --mark the order is camping reservation order or not
	 */
	public void gotoFeeDetailsPageInCart(String resID, boolean notCampOrder) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage
				.getInstance();

		logger.info("Go to fee detail page from order cart page.");
		ordCartPg.goFeeDetail(resID, notCampOrder);
		feepg.waitLoading();
	}

	/**
	 * deactivate the given privileges for the given customer This keyword start
	 * from LM home page and end at LM home page
	 * 
	 * @param cust
	 * @param privileges
	 */
	public void invalidatePrivilegesPerCustomer(Customer cust,
			List<String> privileges) {
		if (privileges.size() > 0) {
			logger.info("Deactivate privileges: " + privileges.toString());
			gotoCustomerDetailFromCustomersQuickSearch(cust);

			for (String p : privileges) {
				gotoPrivilegeDetailPgFromCustDetialsPg(p);
				invalidatePrivilegeItem("", "");
				gotoCustomerDetailsFromPrivilegeDetails();
			}
			gotoLicenseManageHomePage();
		}
	}

	/**
	 * Deactivate all active privilege purchased by one customer.
	 * 
	 * @param customer
	 * @param privilegeInfo
	 */
	public void invalidatePrivilegePerCustomer(Customer customer,
			PrivilegeInfo... privilegeInfos) {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerPrivilegePage customerPriPage = LicMgrCustomerPrivilegePage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPage = LicMgrPrivilegeItemDetailPage
				.getInstance();
		logger
				.info("Deactivate all active privilege purchased by one customer.");

		// go to customer privilege page
		this.gotoCustomerDetailFromTopMenu(customer);
		detailsPg.clickPrivilegesTab();
		ajax.waitLoading();

		// get privilege ID list
		for (PrivilegeInfo privilegeInfo : privilegeInfos) {
			customerPriPage.searchPrivilege(privilegeInfo.licenseYearForSearch,
					privilegeInfo.searchStatus);
			List<String> priIDList = customerPriPage
					.getPrivilegeIDListByName(privilegeInfo.purchasingName);
			if (priIDList.size() > 0) {
				for (int i = 0; i < priIDList.size(); i++) {
					this.gotoHomePage();
					this.gotoPrivSearchPgFromCustomerPg();

					// go to privilege detail page
					this.gotoPrivilegeDetailPageFromPriSearchPg(priIDList
							.get(i));

					// invalidate privilege
					privilegeItemDetailPage.invalidatePrivilege(
							privilegeInfo.operateReason,
							privilegeInfo.operateNote);
				}
				this.gotoCustomerDetailFromTopMenu(customer);
				detailsPg.clickPrivilegesTab();
				ajax.waitLoading();
			}
		}
	}

	public void invalidateAllPrivilegesPerCustomer(Customer customer,  String searchLicYear, String[] searchStatus, String operateReason, String operateNote) {
		invalidateAllPrivilegesPerCustomer(customer, new String[]{searchLicYear}, searchStatus, operateReason, operateNote);
	}
	
	public void invalidateAllPrivilegesPerCustomer(Customer customer,  String[] searchLicYears, String[] searchStatus, String operateReason, String operateNote) {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerPrivilegePage customerPriPage = LicMgrCustomerPrivilegePage
				.getInstance();
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPage = LicMgrPrivilegeItemDetailPage
				.getInstance();
		logger
		.info("Deactivate all active privilege purchased by one customer.");
		List<String> priIDList = new ArrayList<String>();
		// go to customer privilege page
		this.gotoCustomerDetailFromTopMenu(customer);
		gotoCustomerPrivilegesPage();
		
		if(searchLicYears!=null){
			for(String searchLicYear: searchLicYears){
				customerPriPage.searchPrivilege(searchLicYear, searchStatus);
				
				//get privilege ID list
				priIDList.clear();
				priIDList = customerPriPage.getAllPrivilegeIDList();
				if (priIDList.size() > 0) {
					for (int i = 0; i < priIDList.size(); i++) {
						this.gotoHomePage();
						this.gotoPrivSearchPgFromCustomerPg();

						// go to privilege detail page
						this.gotoPrivilegeDetailPageFromPriSearchPg(priIDList.get(i));

						// invalidate privilege
						privilegeItemDetailPage.invalidatePrivilege(operateReason, operateNote);
					}
					this.gotoCustomerDetailFromTopMenu(customer);
					detailsPg.clickPrivilegesTab();
					ajax.waitLoading();
				}
			}
		}
	}
	
	public void invalidateAllPrivilegesPerCustomer(Customer customer, String operateReason, String operateNote) {
		invalidateAllPrivilegesPerCustomer(customer, StringUtil.EMPTY, null, operateReason, operateNote);
	}
	
	public void reverseAllPrivilegesPerCustomer(Customer customer, String operateReason, String operateNote) {
		reverseAllPrivilegesPerCustomer(customer, new String[] {StringUtil.EMPTY}, operateReason, operateNote);
	}
	
	public void reverseAllPrivilegesPerCustomer(Customer customer, String[] searchLicYears, String operateReason, String operateNote) {
		LicMgrCustomerOrdersPage custOrdersPage = LicMgrCustomerOrdersPage.getInstance();
		
		logger.info("Reverse all active order purchased by one customer.");
		// go to customer-Orders tab
		this.gotoCustomerDetailFromTopMenu(customer);
		gotoCustomerOrderPage();
		
		List<String> orderNumsList = new ArrayList<String>();
		if(searchLicYears!=null) {
			for(String searchLicYear: searchLicYears){
				custOrdersPage.searchOrderInLicenseYear(searchLicYear);
				
				orderNumsList.clear();
				orderNumsList = custOrdersPage.getAllActiveOrderNums();
				
				if (orderNumsList.size() > 0) {
					Payment pay = new Payment("Visa");
					for (int i = 0; i < orderNumsList.size(); i++) {
						if(!LicenseMgrHomePage.getInstance().exists()) {
							this.gotoHomePage();
						}
						this.reversePrivilegeOrderToCleanUp(orderNumsList.get(i), operateReason, operateNote, pay);
					}
					
					this.gotoCustomerDetailFromTopMenu(customer);
					this.gotoCustomerOrderPage();
				}
			}
		}
	}
	
	/**
	 * Edit business rule.
	 * 
	 * @param privilegeCode
	 * @param ruleInfo
	 */
	public String[] editBusinessRule(String privilegeCode,
			PrivilegeBusinessRule ruleInfo) {
		logger.info("Edit business rule.");
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget
				.getInstance();

		// go to privilege details page
		this.gotoProductSearchListPageFromTopMenu("Privilege");
		this.gotoPrivilegeDetailsPageFromProductListPage(privilegeCode);

		// go to rule detail page
		this.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);

		// edit rule
		editPg.enterRuleInfo(ruleInfo);
		editPg.clickOK();
		ajax.waitLoading();

		String[] ruleId = null;
		if (rulePage.exists()) {
			if (ruleInfo.isProductMuti) {
				ruleId = new String[1];
				ruleId[0] = rulePage.getRuleInfoFromBusinessRulePg(ruleInfo, 0).id;
			} else {
				ruleId = new String[ruleInfo.parameters.length];
				for (int i = 0; i < ruleInfo.parameters.length; i++) {
					ruleId[i] = rulePage.getRuleInfoFromBusinessRulePg(
							ruleInfo, i).id;
				}
			}
		}

		return ruleId;
	}

	/**
	 * Calculate DOB of customer.
	 * 
	 * @param paramDate
	 * @param paramage
	 * @param isObey
	 * @return
	 */
	public String calculateDOB(String paramDate, int paramAge, int addDays) {
		logger.info("Calculate DOB of customer.");
		Date d2 = DateFunctions.parseDateString(paramDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d2);
		cal.add(Calendar.YEAR, paramAge * (-1));
		cal.add(Calendar.DAY_OF_YEAR, addDays);
		return DateFunctions.formatDate(cal.getTime(), "M/d/yyyy");
	}

	/**
	 * Go back to business rule page by cancel from edit rule widget
	 */
	public void gotoPrivilegeBusinessRulePageFromEditRuleWidget() {
		LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget
				.getInstance();
		LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		logger.info("Go to business rule page by cancel from edit rule widget");

		editPg.clickCancel();
		ajax.waitLoading();
		rulePg.waitLoading();
	}

	/**
	 * Go to co-owner sub page from vehicle detail page
	 */
	public void gotoCoOwnerSubPgFromVehicleDetailsPg() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();

		logger.info("Go to Co-Owner sub page from vehicle details page");

		vehicleDetailsPg.clickCoOwnersTab();
		ajax.waitLoading();
		coOwnerPg.waitLoading();

	}

	/**
	 * Go to edit Co-Owner widget
	 * 
	 * @param num
	 */
	public void gotoEditCoOwnerWidgetByOwnerNum(String num) {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrVehicleEditCoOwnerWidget editPg = LicMgrVehicleEditCoOwnerWidget
				.getInstance();

		logger.info("Go to co-owner detail page with num:" + num);
		coOwnerPg.clickCoOwnerNum(num);
		ajax.waitLoading();
		editPg.waitLoading();
	}

	/**
	 * Get consumable product ID by product code from db.
	 * 
	 * @param consumable
	 *            code.
	 */
	public String getConsumableIDByCode(String proCode, String schema) {
		logger.info("Get prd_id by product code: " + proCode
				+ " from table p_prd.");

		String sql = "SELECT prd_id FROM P_PRD WHERE ( DECODE(PRD_SUBCAT_ID, NULL, 0, PRD_SUBCAT_ID) * 10000 + DECODE(APPL_PRD_CAT_ID, NULL, 0, APPL_PRD_CAT_ID) * 100 + PRODUCT_CAT_ID) IN (4, 20004, 10004, 30004)"
				+ " AND PRODUCT_CAT_ID=4 AND (PRD_SUBCAT_ID IN ('1' , '3' , '2'))"
				+ " AND LOB_ID ='2'"
				+ " AND prd_cd='"
				+ proCode + "'";
		db.resetSchema(schema);
		return db.executeQuery(sql, "prd_id").get(0);
	}

	/**
	 * Get vehicle product ID by product code from db.
	 * 
	 * @param consumable
	 *            code.
	 */
	public String getVehicleIDByCode(String proCode, String schema) {
		logger.info("Get prd_id by product code: " + proCode
				+ " from table p_prd.");

		String sql = "SELECT prd_id FROM P_PRD WHERE PRODUCT_CAT_ID=11 "
				+ " AND ACTIVE_IND =1" + " AND LOB_ID ='2'" + " AND prd_cd='"
				+ proCode + "'";
		db.resetSchema(schema);
		return db.executeQuery(sql, "prd_id").get(0);
	}

	/**
	 * Go back to CoOwner page by cancel from edit CoOwner widget
	 */
	public void gotoCoOwnerSubPageFromEditCoOwnerWidget() {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrVehicleEditCoOwnerWidget editPg = LicMgrVehicleEditCoOwnerWidget
				.getInstance();

		logger.info("Go to coowner page by cancel from edit coowner widget");

		editPg.clickCancel();
		ajax.waitLoading();
		coOwnerPg.waitLoading();
	}

	/**
	 * Click 'View Previous Co-Owners' button in Co-Owners sub page
	 */
	public void gotoViewPreviousCoOwnerFromCoOwnerSubPg() {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrVehicleViewPreviousCoOwnersWidget preCoOwnerPg = LicMgrVehicleViewPreviousCoOwnersWidget
				.getInstance();

		logger.info("Go to view previous co-owner list from coowner sub page");

		coOwnerPg.clickViewPreviousCoOwners();
		ajax.waitLoading();
		preCoOwnerPg.waitLoading();
	}

	/**
	 * Add co-owner in co-cowner sub page
	 * 
	 * @param owner
	 */
	public String addCoOwnerFromCoOwnerSubPg(OwnerInfo owner) {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrVehicleAddCoOwnerWidget addCoOwnerPg = LicMgrVehicleAddCoOwnerWidget
				.getInstance();

		logger.info("Add co-owner list from coowner sub page");

		coOwnerPg.clickAddCoOwner();
		ajax.waitLoading();
		addCoOwnerPg.waitLoading();

		addCoOwnerPg.setupCoOwnerInfo(owner);
		addCoOwnerPg.clickOK();
		ajax.waitLoading();
		coOwnerPg.waitLoading();
		String id = coOwnerPg.getCoOwnerIdToBoat(owner);
		return id;
	}

	/**
	 * Deactive all active questions for supply product This work flow starts at
	 * supply question page
	 */
	public void deactiveAllActiveSupplyQuestions() {
		LicMgrSupplyQuestionPage questionPg = LicMgrSupplyQuestionPage
				.getInstance();

		List<String> ids = questionPg.getAllActiveQuestionsID();

		if (null != ids) {
			logger
					.info("Deactive all active questions for current supply product.");
			for (int i = 0; i < ids.size(); i++) {
				this.deactivateProductQuestion(questionPg, ids.get(i));
			}
		}
	}

	/**
	 * remove co-owner by ID in co-cowner sub page
	 * 
	 * @param id
	 * @param clickOK
	 * @return confirm message
	 */
	public String removeCoOwnerFromVehicleByID(String id, boolean clickOK) {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrConfirmDialogWidget confirm = LicMgrConfirmDialogWidget
				.getInstance();

		String msg = null;
		logger.info("Remove co-owner id:'" + id + "' from current vehicle");
		coOwnerPg.selectCheckBoxByID(id);
		coOwnerPg.clickRemoveCoOwner();
		ajax.waitLoading();
		confirm.waitLoading();
		msg = confirm.getMessage();
		if (clickOK) {
			confirm.clickOK();
		} else {
			confirm.clickCancel();
		}
		ajax.waitLoading();
		coOwnerPg.waitLoading();
		return msg;
	}

	/**
	 * Search harvest in customer harvest list page
	 * 
	 * @param Harvest
	 *            harvest
	 */
	public void searchHarvestInCustHarvestListPg(Harvest harvest) {
		LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage
				.getInstance();
		harvestPage.setSearchingHarvest(harvest);
		harvestPage.clickGo();
		ajax.waitLoading();
	}

	/**
	 * Reprint privilege in Privilege order details page.
	 * 
	 * @param reason
	 * @param note
	 */
	public String rePrintPrivilegeInPrivilegeOrderDetailsPg(String reason,
			String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrReprintPrivilegeWidget rePrintWidget = LicMgrReprintPrivilegeWidget
				.getInstance();
		String msg = "";

		logger.info("Reprint privilege in Privilege order details page.");
		privOrderDetailsPage.clickReprint();
		ajax.waitLoading();
		rePrintWidget.waitLoading();
		rePrintWidget.processReprint(reason, note);
		ajax.waitLoading();
		Object page = browser.waitExists(rePrintWidget, privOrderDetailsPage);
		if (page == rePrintWidget) {
			msg = rePrintWidget.getWarnMessage();
		}
		return msg;
	}

	/**
	 * Reprint privilege in Privilege order details page.
	 * 
	 * @param cancelReason
	 * @param note
	 */
	public String rePrintPrivilegeInPrivilegeOrderDetailsPg() {
		return rePrintPrivilegeInPrivilegeOrderDetailsPg("4 - Other",
				"Auto Test");
	}

	public void mergeCustomFromCustomerDetailPg() {
//		mergeCustomFromCustomerDetailPg(null, 0); // Lesley[20130827]: refactor the method of merge customer, including merge identifiers
		mergeCustomerFromCustomerDetailPg(null, 0, new CustomerIdentifier[0]);
	}

	/**
	 * Merge other candidates to customer which is in the detail page Merge
	 * candidates: same first name, same last name, same date of birth.
	 * 
	 * @param canditateMDWFP
	 */
	public void mergeCustomFromCustomerDetailPg(String canditateMDWFP,
			int selection) {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrMergeCandidatesList mergeList = LicMgrMergeCandidatesList
				.getInstance();
		LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails
				.getInstance();
		LicMgrCustomerMergeOptionWidget mergeOption = LicMgrCustomerMergeOptionWidget
				.getInstance();

		detailsPg.clickMerge();
		ajax.waitLoading();
		mergeOption.waitLoading();
		if (StringUtil.isEmpty(canditateMDWFP)) {
			mergeOption.selectViewMergeCandidates();
			mergeOption.clickOK();
			mergeList.waitLoading();
			mergeList.selectCustomerFirstNumer();
			mergeList.clickOK();	
		} else {
			mergeOption.selectMergeWithMDWFP();
			mergeOption.setMDWFP(canditateMDWFP);
			mergeOption.clickOK();
		}
		
		mergeDetail.waitLoading();
		mergeDetail.selectLeftCustomertoKeep();
		ajax.waitLoading();

		// mergeDetail.clickOK();
		ajax.waitLoading();

		Object page = browser.waitExists(mergeDetail, detailsPg);
		int count = 2;
		while (mergeDetail == page && count > 0) {
			// mergeDetail.clickOK();
			mergeDetail.clickNext();
			ajax.waitLoading();

			page = browser.waitExists(mergeDetail, detailsPg);
			count--;
		}
		mergeDetail.clickMerge();
		ajax.waitLoading();
		page = browser.waitExists(mergeDetail, detailsPg);

		if (page != detailsPg) {
			throw new PageNotFoundException(detailsPg.getName()
					+ " is not found.");
		}
	}

	public void mergeCustomerFromCustomerDetailPg(CustomerIdentifier... mergedIdents) {
		this.mergeCustomerFromCustomerDetailPg(null, 0, mergedIdents);
	}
	
	/** Merge customers from customer details page, including merge identifiers */
	public void mergeCustomerFromCustomerDetailPg(String canditateMDWFP,
			int selection, CustomerIdentifier... mergedIdents) {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrMergeCandidatesList mergeList = LicMgrMergeCandidatesList
				.getInstance();
		LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails
				.getInstance();
		LicMgrCustomerMergeOptionWidget mergeOption = LicMgrCustomerMergeOptionWidget
				.getInstance();
		
		logger.info("Merge customers from customer details page...");
		detailsPg.clickMerge();
		ajax.waitLoading();
		Object page = browser.waitExists(mergeOption, mergeList);//Lesley[20130827]:Update the workflow due to there is no Merge Option widget shown for SK
		if (page == mergeOption) {
			if (StringUtil.isEmpty(canditateMDWFP)) {
				mergeOption.selectViewMergeCandidates();
				mergeOption.clickOK();
				ajax.waitLoading();
				mergeList.waitLoading();
				page = mergeList;
			} else {
				mergeOption.selectMergeWithMDWFP();
				mergeOption.setMDWFP(canditateMDWFP);
				mergeOption.clickOK();
			}
		}
		if (page == mergeList) {
			mergeList.selectCustomerFirstNumer();
			mergeList.clickOK();
			ajax.waitLoading();
		}
		mergeDetail.waitLoading();
		mergeDetail.selectLeftCustomertoKeep();
		ajax.waitLoading();

		page = browser.waitExists(mergeDetail, detailsPg);
		if (page == mergeDetail) {
			while(!mergeDetail.isMergeButtonExists()) {
				mergeDetail.clickNext();
				ajax.waitLoading();
				mergeDetail.waitLoading();
				if (mergedIdents != null && mergedIdents.length > 0 && mergeDetail.isIdentifiersTableExist()) {
					// merge identifier
					mergeDetail.selectIdentifiersToMerge(mergedIdents);
				}
			}
			mergeDetail.clickMerge();
			ajax.waitLoading();
			page = browser.waitExists(mergeDetail, detailsPg);
		}
		if (page != detailsPg) {
			throw new PageNotFoundException(detailsPg.getName()
					+ " is not found.");
		}
	}

	
	/**
	 * go to print doucment detail widget.
	 * 
	 * @param documentId
	 */
	public void gotoPrintDocumentDetailsPage(String documentId) {
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
				.getInstance();
		LicMgrEditPrintDocumentWidget editPrintDocWidget = LicMgrEditPrintDocumentWidget
				.getInstance();
		printDocSubTab.clickDocumentId(documentId);
		ajax.waitLoading();
		editPrintDocWidget.waitLoading();
	}

	/**
	 * go to vehicles print document sub page.
	 * 
	 * @param docProdCode
	 */
	public void gotoVehiclesPrintDocumentSubpage(String docProdCode) {
		this.gotoVehicleProductSubTabPage(docProdCode, "Print Documents");
	}

	/**
	 * Add more consumable from Cart page to Cart page
	 * 
	 * @param consumables
	 * @author Lesley Wang
	 * @date May 11, 2012
	 */
	public void addConsumableFromCartToCart(ConsumableInfo... consumables) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();

		logger.info("Add more Consumable To Order Cart.");

		cartPg.clickPurchaseConsumables();
		ajax.waitLoading();
		addItemPg.waitLoading();
		for (ConsumableInfo consumable : consumables) {
			this.addConsumableItem(consumable);
		}
		this.goToCart();
	}

	/**
	 * Deactive all active questions for privilege product This work flow starts
	 * at privilege question page
	 */
	public void deactiveAllActivePrivilegeQuestions() {
		LicMgrPrivilegeQuestionPage questionPg = LicMgrPrivilegeQuestionPage
				.getInstance();

		List<String> ids = questionPg.getAllActiveQuestionsID();

		if (null != ids) {
			logger
					.info("Deactive all active questions for current privilege product.");
			for (int i = 0; i < ids.size(); i++) {
				this.deactivateProductQuestion(questionPg, ids.get(i));
			}
		}
	}

	/*
	 * quick renewal privilege to cart.
	 */
	public void quickRenewalPrivilegeToCart(String custumerNum,
			PrivilegeInfo... privilegeInfos) {
		this.quickRenewalPrivilegeToCart(custumerNum, null, privilegeInfos);
	}

	/**
	 * handle quick renewal privileg possible page.
	 * 
	 * @param custumerNum
	 * @param cust
	 * @param privilegeInfos
	 */
	void handleRenewalPivilegePage(String custumerNum, Customer cust,
			PrivilegeInfo... privilegeInfos) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
				.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget
				.getInstance();
		LicMgrPrivilegeEducationWidget educationWidget = LicMgrPrivilegeEducationWidget
				.getInstance();
		LicMgrPrivilegeRenewalConfirmWidget renewConfirmPg = LicMgrPrivilegeRenewalConfirmWidget
				.getInstance();

		logger.info("Handle renewal privielge page");

		Object page = browser.waitExists(ordCartPg, privQuestWidget,
				privInvWidget, educationWidget, renewConfirmPg);
		if (privInvWidget == page) {
			for (int i = 0; i < privilegeInfos.length; i++) {
				privilegeInfos[i].inventoryNum = privInvWidget
						.selectInventoryNumber(privilegeInfos[i].inventoryNum);
				if (StringUtil.isEmpty(privilegeInfos[i].inventoryNum)) {
					throw new ItemNotFoundException(
							"Failed to get inventory number.");
				}
				privInvWidget.clickOK();
				ajax.waitLoading();
				this.handleRenewalPivilegePage(custumerNum, cust,
						privilegeInfos);
			}
		}
		if (educationWidget == page) {
			educationWidget.setEducationInfo(cust.education);
			ajax.waitLoading();
			this.handleRenewalPivilegePage(custumerNum, cust, privilegeInfos);
		}
		if (privQuestWidget == page) {
			for (int i = 0; i < privilegeInfos.length; i++) {
				if (null != privilegeInfos[i].privilegeQuestions
						&& privilegeInfos[i].privilegeQuestions.length > 0) {
					for (int j = 0; j < privilegeInfos[i].privilegeQuestions.length; j++) {
						privQuestWidget
								.answerPrivilegeQuestion(
										privilegeInfos[i].privilegeQuestions[j].questDisplayText,
										privilegeInfos[i].privilegeQuestions[j].questionType,
										privilegeInfos[i].privilegeQuestions[j].questAnswer);
						privQuestWidget.clickDone();
						ajax.waitLoading();
						this.handleRenewalPivilegePage(custumerNum, cust,
								privilegeInfos);
						;
					}
				}
			}

		}
		if (renewConfirmPg == page) {
			for (int i = 0; i < privilegeInfos.length; i++) {
				renewConfirmPg.unselectAllPrivilegeToRenewal();
				renewConfirmPg
						.selectPrivlegeToRenewal(privilegeInfos[i].purchasingName);
			}
			renewConfirmPg.clickRenewButton();
			ajax.waitLoading();
			this.handleRenewalPivilegePage(custumerNum, cust, privilegeInfos);
		}

	}

	/**
	 * quick renewal privilege from home page to order cart page.
	 * 
	 * @param custumerNum
	 *            - customr number.
	 */
	public void quickRenewalPrivilegeToCart(String custumerNum, Customer cust,
			PrivilegeInfo... privilegeInfos) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();

		this.gotoHomePage();
		homePg.setCustomerMDWFPforQuickRenewal(custumerNum);
		homePg.clickQuickRenewal();
		ajax.waitLoading();
		confirmPg.waitLoading();
		confirmPg.clickOK();
		ajax.waitLoading();
		this.handleRenewalPivilegePage(custumerNum, cust, privilegeInfos);
	}

	/**
	 * go to customer privilege page from customer detail page.
	 */
	public void gotoPrivilegeTabPageFromCustDetailPg() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
				.getInstance();

		custDetailPg.clickPrivilegesTab();
		ajax.waitLoading();
		custPrivilegPg.waitLoading();
	}

	public void verifyEnforceSearchBeforeAddCust(String customerClass,
			String alertMsg, boolean forced) {
		LicMgrCustomersSearchPage customerSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialog = LicMgrConfirmDialogWidget
				.getInstance();
		
		if (forced) {
			logger.info("Verify enforce search before add a new customer.");
			customerSearchPg.clickAddCustomer();
			ajax.waitLoading();
			Object page = browser.waitExists(confirmDialog, newCustPg);
			if (page == newCustPg) {
				throw new ActionFailedException(
						"Alert didn't pop up to enforce search before add a new customer.");
			}
			String content = confirmDialog.getMessage();
			if (!content.equalsIgnoreCase(alertMsg)) {
				throw new ErrorOnPageException(
						"The pop up message does not make sense that enforce search before add a new customer.",
						alertMsg, content);
			}
			logger
					.info("Alert has poped up to enforce search before add a new customer");
			confirmDialog.clickOK();
			customerSearchPg.waitLoading();

			customerSearchPg.setLicenseNum("invalid123"); // set an invalid
															// number
			customerSearchPg.clickSearch();
			ajax.waitLoading();
			customerSearchPg.waitLoading();
			verifyEnforceSearchBeforeAddCust(customerClass, null, false);
		} else {
			logger
					.info("Verify add a new customer without enforce search customer.");
			this.gotoNewCustomerPageWithoutSearch(customerClass);
		}
	}

	/**
	 * Go to search registration page from top menu.
	 */
	public void gotoSearchRegisPage() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage
				.getInstance();
		logger.info("Go to search registration page from top menu...");

		// go to search registration page
		topMenu.clickVehicles();
		vehicleSearchPg.waitLoading();
		vehicleSearchPg.switchToRegistrationsTab();
		registrationsSearchPg.waitLoading();
	}

	/**
	 * Go to registration details page from registration search page.
	 * 
	 * @param regisID
	 */
	public void gotoRegisDetailPage(String regisID) {
		LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage
				.getInstance();
		LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage
				.getInstance();
		logger
				.info("Go to registration details page from registration search page.");
		registrationsSearchPg.clickRegisID(regisID);
		ajax.waitLoading();
		regisDetailPg.waitLoading();
	}

	/**
	 * Check if the customer has vehicle order. If customer doesn't have any
	 * vehicle order, do registration for this customer.
	 */
	public String checkRegis(Customer cust, Vehicle vehicle, Payment pay,
			String tabName) {
		LicMgrCustomerDetailsPage cusDetailPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerVehiclesPage vehiclesPg = LicMgrCustomerVehiclesPage
				.getInstance();
		LicMgrVehicleDetailPage vehicleDetailPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleOrderSubPage vehicleOrdPg = LicMgrVehicleOrderSubPage
				.getInstance();
		LicMgrVehicleRegistrationsPage vehicleRegisPg = LicMgrVehicleRegistrationsPage
				.getInstance();
		String returnValue = "";
		logger.info("Check whether this customer has vehicle order.");

		// go to vehicles page from customer detail page
		this.gotoCustomerDetailFromTopMenu(cust);
		cusDetailPg.clickVehiclesTab();
		ajax.waitLoading();
		vehiclesPg.waitLoading();
		// get customer vehicles
		List<BoatInfo> vehicleList = vehiclesPg.getCustomerVehicles();
		if (vehicleList.size() > 0) {
			for (int i = 0; i < vehicleList.size(); i++) {
				// go to vehicle detail page
				vehiclesPg.clickIDMINum(vehicleList.get(i).id);
				ajax.waitLoading();
				vehicleDetailPg.waitLoading();
				// go to vehicle order sub page
				vehicleDetailPg.clickTab(tabName);
				ajax.waitLoading();
				Object page = browser.waitExists(vehicleOrdPg, vehicleRegisPg);
				if (page == vehicleOrdPg) {
					// get vehicle order info
					List<OrderInfo> orderInfoList = vehicleOrdPg.getOrderInfo();
					OrderInfo orderInfo = new OrderInfo();
					for (int j = 0; j < orderInfoList.size(); j++) {
						orderInfo = orderInfoList.get(j);
						if (orderInfo.customer.contains(cust.lName)
								&& orderInfo.productPurchaseType
										.contains(vehicle.registration.rTIPrdCode)) {
							returnValue = orderInfo.orderNum;
							break;
						}
					}
				} else if (page == vehicleRegisPg) {
					// get registration ID
					List<RegistrationInfo> regisList = vehicleRegisPg
							.getVehicleRegistrations();
					RegistrationInfo regisInfo = new RegistrationInfo();
					for (int j = 0; j < regisList.size(); j++) {
						regisInfo = regisList.get(j);
						if (regisInfo.customer.contains(cust.lName)
								&& regisInfo.product
										.contains(vehicle.registration.rTIPrdCode)) {
							returnValue = regisInfo.id;
							break;
						}
					}
				}
				if (null != returnValue && !"".equals(returnValue)) {
					break;
				}
			}
		}

		// if customer doesn't have vehicle order, do registration for this
		// customer.
		if (null == returnValue || "".equals(returnValue)) {
			this.gotoHomePage();
			this.registerVehicleToOrderCart(cust, vehicle);
			this.processOrderToOrderSummary(pay);
			returnValue = this.processOrderCart(pay);
		}
		return returnValue;
	}

	/**
	 * Go to consumable order detail page from license home page to consumable
	 * order detail page
	 * 
	 * @param consumOrderNum
	 * @author Lesley Wang
	 * @date Jun 4, 2012
	 */
	public void gotoConsumableOrderDetailsPage(String consumOrderNum) {
		LicenseMgrHomePage licMrgHomePg = LicenseMgrHomePage.getInstance();
		LicMgrConsumableOrderSearchPage consumOrderSearchPg = LicMgrConsumableOrderSearchPage
				.getInstance();
		LicMgrConsumableOrderDetailsPage consumOrderDetailsPage = LicMgrConsumableOrderDetailsPage
				.getInstance();

		logger.info("----Goto consumable order(#=" + consumOrderNum
				+ ") detail page from license home page----");

		licMrgHomePg.setConsumableOrderNum(consumOrderNum);
		licMrgHomePg.clickSearchInOrders();
		ajax.waitLoading();
		consumOrderSearchPg.waitLoading();
		consumOrderSearchPg.clickOrderNum(consumOrderNum);
		ajax.waitLoading();
		consumOrderDetailsPage.waitLoading();
	}
	
	public void gotoConsumableOrderHistoryPage() {
		LicMgrConsumableOrderDetailsPage consumOrderDetailsPage = LicMgrConsumableOrderDetailsPage
		.getInstance();
		LicMgrConsumableOrderHistoryPage historypg=LicMgrConsumableOrderHistoryPage.getInstance();
		
		consumOrderDetailsPage.clickHistory();
		ajax.waitLoading();
		historypg.waitLoading();
	}

	/**
	 * Void consumable order from order details page to order cart page
	 * 
	 * @param reason
	 * @param note
	 * @author Lesley Wang
	 * @date Jun 4, 2012
	 */
	public void voidConsumableOrderToCart(String reason, String note) {
		LicMgrConsumableOrderDetailsPage consumOrderDetailPage = LicMgrConsumableOrderDetailsPage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Void Consumable Order To Cart.");

		consumOrderDetailPage.clickVoidOrder();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(reason, note);

		orderCartPg.waitLoading();
	}

	/**
	 * This method is used to make consumable order from customer top menu to
	 * order cart and its work flow as below: 1.go to customer detail page from
	 * top menu in home page 2.click purchase privilege to add item page 3.click
	 * Consumable tab and add the consumables to cart 4.go to cart
	 * 
	 * @param cust
	 * @param consumables
	 * @author Lesley Wang
	 * @date Jun 4, 2012
	 */
	public void makeConsumableOrderToCartFromCustomerTopMenu(Customer cust,
			ConsumableInfo... consumables) {
		LicMgrOrigPrivSaleAddItemPage privAddItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		logger
				.info("Make Consumable Order To Cart from customers in top menu.");

		this.gotoAddItemPgFromCustomerTopMenu(cust);
		privAddItemPg.clickConsumables();
		ajax.waitLoading();
		consumAddItemPg.waitLoading();
		this.makeConsumableOrderToCartFromAddItemPage(cust, consumables);
	}

	/**
	 * go to vehicle title tab page in register detail page.
	 */
	public void gotoVehicleTitleTabPage() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleTitleListPage vehicleTitlePg = LicMgrVehicleTitleListPage
				.getInstance();

		logger.info("Go to vehicle title sub page");
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		vehicleTitlePg.waitLoading();
	}

	/**
	 * Go to vehicle titles search/list page from top menu
	 */
	public void gotoVehicleTitleSearchPage() {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPage = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleTitleSearchPage titleSearchPage = LicMgrVehicleTitleSearchPage
				.getInstance();

		logger.info("Go to Vehicle Title search/list page from top menu.");
		topMenuPage.clickVehicles();
		vehicleSearchPage.waitLoading();
		vehicleSearchPage.switchToTitlesTab();
		titleSearchPage.waitLoading();
	}

	public void gotoVehicleTitleDetailPageFromSearchPage(String titleNum) {
		LicMgrVehicleTitleSearchPage titleSearchPage = LicMgrVehicleTitleSearchPage
				.getInstance();
		LicMgrVehicleTitleDetialsInfoPage titleDetailPage = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();

		logger.info("Go to vehicle title detail page from title search page.");
		titleSearchPage.searchTitleByTitleNum(titleNum);
		titleSearchPage.clickSearch();
		ajax.waitLoading();
		titleSearchPage.clickTitleID();
		ajax.waitLoading();
		titleDetailPage.waitLoading();
	}

	public void gotoVehicleTitleDetailPage(String titleNum) {
		logger.info("Search and Goto Title#(" + titleNum + ") detail.");

		gotoVehicleTitleSearchPage();
		gotoVehicleTitleDetailPageFromSearchPage(titleNum);
	}

	/**
	 * go to vehicle Title Details page.
	 * 
	 * @param titleId
	 */
	public void gotoVehicleTitleDetailPgFromTitleList(String titleId) {
		LicMgrVehicleTitleListPage vehicleTitlelPg = LicMgrVehicleTitleListPage
				.getInstance();
		LicMgrVehicleTitleDetialsInfoPage vehicleTitleDetailsInfoPg = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();

		logger.info("Go to title " + titleId + " details info page");
		vehicleTitlelPg.clickTitleId(titleId);
		ajax.waitLoading();
		vehicleTitleDetailsInfoPg.waitLoading();
	}

	/**
	 * go to lien list page.
	 */
	public void gotoVehicleLienListPage() {
		LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage
				.getInstance();
		LicMgrVehicleTitleDetialsInfoPage titleDetaileInfoPg = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();
		logger.info("go to vehicle lien list page");
		titleDetaileInfoPg.clickLiensTab();
		ajax.waitLoading();
		lienListPg.waitLoading();
	}

	/**
	 * add vehicle lien.
	 * 
	 * @param lien
	 */
	public String addVehicleLien(LienInfo lien) {
		String text = "";
		LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage
				.getInstance();
		LicMgrAddVehicleLienDetailsWidget addLienPg = LicMgrAddVehicleLienDetailsWidget
				.getInstance();
		LicMgrVehicleLienCompanyDetailsWidget companyDetailsWgt = LicMgrVehicleLienCompanyDetailsWidget
				.getInstance();
		logger.info("add vehicle lien");

		lienListPg.clickAddLien();
		ajax.waitLoading();
		addLienPg.waitLoading();
		addLienPg.setLienInfo(lien);
		if (null != lien.getLienCompanyDetailsInfo()) {
			addLienPg.clickAddLienCompanyName();
			ajax.waitLoading();
			companyDetailsWgt.waitLoading();
			companyDetailsWgt.setLienCompanyDetails(lien
					.getLienCompanyDetailsInfo());
			companyDetailsWgt.clickOK();
			ajax.waitLoading();
			addLienPg.waitLoading();
		}
		addLienPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(addLienPg, lienListPg);
		if (addLienPg == page) {
			text = addLienPg.getErrorMessage();
		}
		text = lienListPg.getActiveLienId();// There just allow one active lien
											// exist if want to add success the
											// status must active.
		return text;
	}

	/**
	 * edit vehicle lien.
	 * 
	 * @param lien
	 */
	public void editVehicleLien(LienInfo lien) {
		LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage
				.getInstance();
		LicMgrEditVehicleLienDetailsWidget editLienPg = LicMgrEditVehicleLienDetailsWidget
				.getInstance();
		LicMgrVehicleLienCompanyDetailsWidget companyDetailsWgt = LicMgrVehicleLienCompanyDetailsWidget
				.getInstance();
		logger.info("Edit vehicle lien");

		lienListPg.clickLienId(lien.getLienId());
		ajax.waitLoading();
		editLienPg.waitLoading();
		editLienPg.editLienInfo(lien.getDateOfLien(), lien.getLienAmount());
		if (null != lien.getLienCompanyDetailsInfo()) {
			editLienPg.clickAddLienCompanyName();
			ajax.waitLoading();
			companyDetailsWgt.waitLoading();
			companyDetailsWgt.setLienCompanyDetails(lien
					.getLienCompanyDetailsInfo());
			companyDetailsWgt.clickOK();
			ajax.waitLoading();
			editLienPg.waitLoading();
		}
		editLienPg.clickOK();
		ajax.waitLoading();
		lienListPg.waitLoading();
	}

	/**
	 * release VehicleLien
	 */
	public void releaseVehicleLien(String releaseDate) {
		LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage
				.getInstance();
		LicMgrVehicleReleaseLienWidget releaeWgt = LicMgrVehicleReleaseLienWidget
				.getInstance();
		logger.info("Release lien");

		if (!lienListPg.exists()) {
			lienListPg.clickLiensTab();
			ajax.waitLoading();
			browser.waitExists();
		}
		lienListPg.releaseLien();
		releaeWgt.waitLoading();
		releaeWgt.setDateOfRelease(releaseDate);
		releaeWgt.clickOK();
		ajax.waitLoading();
		lienListPg.waitLoading();
	}

	/*
	 * go to vehicle title history from details page.
	 */
	public void gotoVehicleTitleHistoryFromDetailPg() {
		LicMgrVehicleTitleDetialsInfoPage titleDetaileInfoPg = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();
		LicMgrVehicleTitleHistoryPage historyPg = LicMgrVehicleTitleHistoryPage
				.getInstance();
		logger.info("go to vehicle tilte history page");
		titleDetaileInfoPg.clickHistoryTab();
		ajax.waitLoading();
		historyPg.waitLoading();
	}

	/**
	 * Go to vehicle detail sub table from vehicle detail page
	 * 
	 * @param tabName
	 *            -- CoOwner/Registrations
	 */
	public void gotoVehicleDetailSubTable(String tabName) {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage
				.getInstance();

		if (tabName.equalsIgnoreCase("CoOwner")) {
			logger.info("Go to Co-Owner sub page.");
			vehicleDetailsPg.clickCoOwnersTab();
			ajax.waitLoading();
			coOwnerPg.waitLoading();
		} else if (tabName.equalsIgnoreCase("Registrations")) {
			logger.info("Go to Registrations sub page.");
			vehicleDetailsPg.clickRegistrationsTab();
			ajax.waitLoading();
			registrationPg.waitLoading();
		} else {
			throw new ErrorOnDataException("Unhandled table name.");
		}

	}

	/**
	 * Go to Vehicle Registrations tab sub page on Vehicles Details page
	 * 
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public void gotoVehicleRegistrationsTabPg() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleRegistrationsPage registrationsPg = LicMgrVehicleRegistrationsPage
				.getInstance();

		logger.info("Go to vehicle Registrations sub page");
		vehicleDetailsPg.clickRegistrationsTab();
		ajax.waitLoading();
		registrationsPg.waitLoading();
	}

	/**
	 * Go to Vehicle Registration Detail page by clicking the regis id under
	 * Registrations Tab page
	 * 
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public void gotoVehicleRegisDetailPgFromRegisTabPg(String regisID) {
		LicMgrVehicleRegistrationsPage registrationsPg = LicMgrVehicleRegistrationsPage
				.getInstance();
		LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage
				.getInstance();

		logger.info("Go to Vehicle Registration Detail page by "
				+ "clicking the regis id " + regisID
				+ " under Registrations Tab page.");
		registrationsPg.clickRegID(regisID);
		ajax.waitLoading();
		regisDetailPg.waitLoading();
	}

	/**
	 * change customer in order cart page. After search costomer, the work flow
	 * return order cart page
	 * 
	 * @param cust
	 * @Return void
	 * @Throws
	 */
	public void changeCustomer(Customer cust) {
		LicMgrCustomersSearchPage custSearch = LicMgrCustomersSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger.info("Change customer in order cart page...");
		cartPage.clickChangeCustomer();
		custSearch.waitLoading();
		this.searchCustomer(cust);
		if (custSearch.exists()) {
			custSearch.selectFirstCustomer();
		}
		ajax.waitLoading();
		cartPage.waitLoading();
		logger.info("Change customer successfully !");
	}

	/**
	 * Go to the related search page from order cart page by choosing the option
	 * in the Search dropdown list on order cart page.
	 * 
	 * @param option
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void gotoProductSearchPgFromOrderCartPg(String option) {
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrTopMenuPage lmTopMenuPage = LicMgrTopMenuPage.getInstance();

		logger
				.info("Go to " + option
						+ " search page from order order page...");
		lmTopMenuPage.selectSearchOptions(option);
		ajax.waitLoading();
		vehicleSearchPg.waitLoading();
	}

	/**
	 * Go to Vehicles search page from order cart page.
	 * 
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void gotoVehiclesSearchPgFromOrderCartPg() {
		gotoProductSearchPgFromOrderCartPg("Vehicles");
	}

	/**
	 * Quick renew the vehicle from home page to order cart page.
	 * 
	 * @param vehMINum
	 * @param product
	 * @return
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void quickRenewVehicleToOrderCartPg(String vehMINum, String product) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVehicleCustomerConfirmPage confirmPg = LicMgrVehicleCustomerConfirmPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget
				.getInstance();

		logger.info("Quick renewal vehicle registration " + product
				+ " from home page to order cart page...");
		homePg.setMIOrIDNumberForQuickRenewal(vehMINum);
		homePg.clickVehicleQuickRenewal();
		ajax.waitLoading();
		confirmPg.clickOK();
		ajax.waitLoading();

		Object page = browser.waitExists(ordCartPg, regWidget);
		if (page == regWidget) {
			regWidget.selectRegistrationProduct("Renewal", product);
			regWidget.clickOK();
			ajax.waitLoading();
			ordCartPg.waitLoading();
		}
	}

	/**
	 * go to renew vehicle customer confirm page.
	 * 
	 * @param vehMINum
	 */
	public void gotoQuickRenewVehicleCustomerConfirmPg(String vehMINum) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrVehicleCustomerConfirmPage confirmPg = LicMgrVehicleCustomerConfirmPage
				.getInstance();

		logger.info("go to renew vehicle customer confirm page");
		homePg.setMIOrIDNumberForQuickRenewal(vehMINum);
		homePg.clickVehicleQuickRenewal();
		ajax.waitLoading();
		confirmPg.waitLoading();
	}

	/**
	 * Go to Vehilce Details page from Registration Details page by clicking
	 * Vehicle MI Num link
	 * 
	 * @author Lesley Wang
	 * @date Jun 14, 2012
	 */
	public void clickVehicleMINumToVehicleDetailsPg() {
		LicMgrVehicleDetailPage vehDetailPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage
				.getInstance();

		logger
				.info("Go to Vehilce Details page from Registration Details page by clicking Vehicle MI Num link...");

		regisDetailPg.clickVehicleMINum();
		ajax.waitLoading();
		vehDetailPg.waitLoading();
	}

	/**
	 * Go to Customer Details page from Registration Details page by clicking
	 * Customer MDWFP Num link
	 * 
	 * @author Lesley Wang
	 * @date Jun 14, 2012
	 */
	public void clickVehicleCustMDWFPNumToCustDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage
				.getInstance();

		logger
				.info("Go to Customer Details page from Registration Details page by clicking Customer MDWFP Num link...");

		regisDetailPg.clickVehicleCustNum();
		ajax.waitLoading();
		custDetailsPg.waitLoading();
	}

	/**
	 * Go to registration details page from home page
	 * 
	 * @param regisInfo
	 * @author Lesley Wang
	 * @date Jun 15, 2012
	 */
	public void gotoRegisDetailsPgFromHomePg(RegistrationInfo regisInfo) {
		LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage
				.getInstance();

		logger.info("Go to registration details page from home page...");

		this.gotoSearchRegisPage();
		registrationsSearchPg.searchRegistration(regisInfo);
		regisInfo.id = registrationsSearchPg.getFirstRegistrationID();
		this.gotoRegisDetailPage(regisInfo.id);
	}

	/**
	 * Click Vehicle Registration button on Vehicle Details page to open the
	 * registration products widget
	 * 
	 * @author Lesley Wang
	 * @date Jun 15, 2012
	 */
	public void gotoVehRegisProdWidgetFromVehDetaildPg() {
		LicMgrVehicleDetailPage detailPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget
				.getInstance();

		logger
				.info("Go to registration products widget from Vehicle Details page...");

		detailPg.clickRegistration();
		ajax.waitLoading();
		regWidget.waitLoading();
	}

	/**
	 * Select Vehicle Reg Product on registration products widget by the type
	 * and name, and add it to order cart page
	 * 
	 * @param purchaseType
	 * @param product
	 * @author Lesley Wang
	 * @date Jun 15, 2012
	 */
	public void selectVehRegProdToOrderCartPg(String purchaseType,
			String product) {
		LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();

		logger
				.info("Select registration product with type="
						+ purchaseType
						+ " name="
						+ product
						+ " on vehicle registation product widget and add it to order cart page...");

		regWidget.selectRegistrationProduct(purchaseType, product);
		regWidget.clickOK();
		ajax.waitLoading();
		lmOrderCartPg.waitLoading();
	}

	/**
	 * go to vehicle privious owner page.
	 */
	public void gotoVehiclePreviousOwnersPg() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehiclePreviousOwnersPage previousOwnerPg = LicMgrVehiclePreviousOwnersPage
				.getInstance();

		logger.info("go to vehicle previous owners page");
		vehicleDetailsPg.clickPreOwnersTab();
		ajax.waitLoading();
		previousOwnerPg.waitLoading();
	}

	/**
	 * Go to registration/title/inspection details page from vehicle order
	 * details page by clicking the ID link on order item list
	 * 
	 * @param regID
	 * @author Lesley Wang
	 * @date Jun 19, 2012
	 */
	public void gotoRegTitInspDetailsPgFromVehOrderDetailsPg(String regID) {
		LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrRegistrationDetailsPage vehRegDetailsPg = LicMgrRegistrationDetailsPage
				.getInstance();
		LicMgrVehicleTitleDetialsInfoPage vehTitDetailsPg = LicMgrVehicleTitleDetialsInfoPage
				.getInstance();
		LicMgrInspectionDetailsPage vehInspDetailsPg = LicMgrInspectionDetailsPage
				.getInstance();

		logger
				.info("Go to registration details page from vehicle order details page by ID="
						+ regID);

		vehOrdDetailsPg.clickOrderItemRegTitleInspID(regID);
		browser.waitExists(vehRegDetailsPg, vehTitDetailsPg, vehInspDetailsPg);
	}

	/**
	 * click add vehicle motor.
	 * 
	 * @param motor
	 */
	public String addVehicleMotor(MotorInfo motor) {
		LicMgrVehicleMotorsPage motorPg = LicMgrVehicleMotorsPage.getInstance();
		LicMgrAddMotorWidget addMotorWgt = LicMgrAddMotorWidget.getInstance();

		logger.info("Add vehicle Motor");
		motorPg.clickAddMotorToBoat();
		ajax.waitLoading();
		addMotorWgt.waitLoading();
		addMotorWgt.setMotorDetialsInfo(motor);
		addMotorWgt.clickOK();
		ajax.waitLoading();
		motorPg.waitLoading();
		String id = motorPg.getMotorBoatId(motor);
		return id;
	}

	/**
	 * a local method for handling refund from refund widget to order details
	 * page
	 * 
	 * @param operation
	 * @author Lesley Wang
	 * @date Jun 20, 2012
	 */
	String handelRefundFromRefundWidgetToOrdDetailsPg(String operation) {
		LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
		LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage priVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		LicMgrConsumableOrderDetailsPage consumOrdDetailsPg = LicMgrConsumableOrderDetailsPage
				.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance(true);
		String message = null;

		logger.info(operation
				+ " refund from refund widget to order details page...");

		if (operation.equalsIgnoreCase("Issue")) {
			refundWidget.selectIssueRefundToCustomerRadio();
			ajax.waitLoading();
		} else if (operation.equalsIgnoreCase("Post")) {
			refundWidget.selectPostRefundAsCreditRadio();
			ajax.waitLoading();
		}

		refundWidget.clickOK();
		ajax.waitLoading();

		browser.waitExists(alert, vehOrdDetailsPg, priVoidPg,
				consumOrdDetailsPg);
		if (alert.exists()) {
			message = alert.getMessage();
			alert.clickOK();
			ajax.waitLoading();
			browser.waitExists(vehOrdDetailsPg, priVoidPg, consumOrdDetailsPg);
		}

		return message;
	}

	/**
	 * Issue this Refund to the Customer now on Refund widget to order details
	 * page
	 * 
	 * @author Lesley Wang
	 * @date Jun 20, 2012
	 */
	public String issueRefundToCustFromRefundWidgetToOrdDetailsPg() {
		return this.handelRefundFromRefundWidgetToOrdDetailsPg("Issue");
	}

	/**
	 * Post the Refund as a Credit on Refund widget to order details page
	 * 
	 * @author Lesley Wang
	 * @date Jun 20, 2012
	 */
	public String postRefundAsCreditFromRefundWidgetToOrdDetailsPg() {
		return this.handelRefundFromRefundWidgetToOrdDetailsPg("Post");
	}

	/**
	 * go to vehicle order tab page.
	 */
	public void goToVehicleOrderTabPage() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();
		LicMgrVehicleOrderSubPage orderSubPg = LicMgrVehicleOrderSubPage
				.getInstance();
		logger.info("go to vehicle order sub page");

		vehicleDetailsPg.clickOrderTab();
		ajax.waitLoading();
		orderSubPg.waitLoading();
	}

	/*
	 * remove vehicle motor by id.
	 */
	public void removeVehicleMotor(String id) {
		LicMgrVehicleMotorsPage motorPg = LicMgrVehicleMotorsPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance();

		logger.info("Remove vehicle motor");
		motorPg.selectMotorCheckBox(id);
		motorPg.clickRemoveMotorFromBoat();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
		confirmDialogWidget.clickOK();
		ajax.waitLoading();
		motorPg.waitLoading();
	}

	/**
	 * go to renewal customer page.
	 * 
	 * @param custumerNum
	 */
	public void gotoQuickRenewPrivilegeCustomerProfilePage(String custumerNum) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();
		logger.info("Go to quick renewal privilege customer profile page");

		homePg.setCustomerMDWFPforQuickRenewal(custumerNum);
		homePg.clickQuickRenewal();
		ajax.waitLoading();
		confirmPg.waitLoading();
	}

	/**
	 * edit renew customer profile Info.
	 * 
	 * @param cust
	 */
	public void editRenewPrivilegeCustomerProfileInfo(Customer cust) {
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();

		logger.info("Edit renew customer profile info");
		confirmPg.editCustProfile(cust);
		ajax.waitLoading();
		this
				.handleRenewalPivilegePage(cust.custNum, cust,
						(PrivilegeInfo) null);
	}

	/**
	 * Edit renew vehicle customer profile info.
	 * 
	 * @param cust
	 * @param product
	 */
	public void editRenewVehicleCustomerProfileInfo(Customer cust,
			String product) {
		LicMgrVehicleCustomerConfirmPage confirmPg = LicMgrVehicleCustomerConfirmPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget
				.getInstance();
		logger.info("Edit renew vehicle cusomer profile info");

		confirmPg.editCustProfile(cust);
		ajax.waitLoading();
		Object page = browser.waitExists(ordCartPg, regWidget);
		if (page == regWidget) {
			regWidget.selectRegistrationProduct("Renewal", product);
			regWidget.clickOK();
			ajax.waitLoading();
			ordCartPg.waitLoading();
		}
	}

	/**
	 * Go to search vehicle page, search vehicle and get warning message.
	 * 
	 * @param vehicle
	 * @return
	 */
	public String searchVehicle(Vehicle vehicle) {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();

		logger
				.info("Go to search vehicle page, search vehicle and get warning message.");
		topMenu.clickVehicles();
		ajax.waitLoading();
		vehicleSearchPg.waitLoading();
		vehicleSearchPg.setupSearchCriteria(vehicle);
		vehicleSearchPg.clickSearch();
		ajax.waitLoading();
		String returnValue = vehicleSearchPg.getWarnMessage();
		return returnValue;
	}

	/**
	 * Go to change history page from vehicle details page.
	 */
	public void gotoVehicleChangeHistoryPage() {
		LicMgrVehicleChangeHistoryPage historyPg = LicMgrVehicleChangeHistoryPage
				.getInstance();
		LicMgrVehicleDetailPage detailPg = LicMgrVehicleDetailPage
				.getInstance();

		logger.info("Go to change history page from vehicle details page.");
		detailPg.clickChangeHistory();
		ajax.waitLoading();
		historyPg.waitLoading();
	}

	/**
	 * Go back to vehicle details page from view change history page.
	 */
	public void gotoVehicleDetailFromChangeHistoryPage() {
		LicMgrVehicleChangeHistoryPage historyPg = LicMgrVehicleChangeHistoryPage
				.getInstance();
		LicMgrVehicleDetailPage detailPg = LicMgrVehicleDetailPage
				.getInstance();

		logger
				.info("Go back to vehicle details page from view change history page.");
		historyPg.clickRtnToVehicleDetail();
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	/**
	 * Go to vehicle details page by vehicle id from top menu
	 * 
	 * @param vehicleID
	 */
	public void gotoVehicleDetailPageByID(String vehicleID) {
		LicMgrVehiclesSearchPage vehicleSearchPage = LicMgrVehiclesSearchPage
				.getInstance();

		logger.info("Go to vehicle details page from top menu by vehicle id - "
				+ vehicleID);

		this.gotoVehicleSearchPageFromTopMenu();
		vehicleSearchPage.searchVehicleByVehicelId(vehicleID);
		this.gotoVehicleDetailPageFromSearchPage(vehicleID);
	}

	/**
	 * Go to vehicle details page by clicking vehicle ID in search vehicle page.
	 * 
	 * @param vehicleID
	 */
	public void gotoVehicleDetailPageFromSearchPage(String vehicleID) {
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();

		logger
				.info("Go to vehicle details page by clicking vehicle ID in search vehicle page.");
		vehicleSearchPg.clickVehiclesID(vehicleID);
		ajax.waitLoading();
		vehicleDetailsPg.waitLoading();
	}

	/**
	 * get active customer profile id from DB.
	 * 
	 * @param vehildeId
	 * @param schema
	 * @return
	 */
	/*
	 * public String getCustProfileIdByVehicleId(String vehicleId,String
	 * schema){ String sql =
	 * "select CUST_PROF_ID from E_VEHICLE where id="+vehicleId;
	 * db.resetSchema(schema); return db.executeQuery(sql, "CUST_PROF_ID",0); }
	 */
	/**
	 * get customer number by Vehicle ID.
	 * 
	 * @param vehicleId
	 * @param schema
	 * @return
	 */
	public String getCustNumberByVehicleId(String vehicleId, String schema) {
		String sql = "select CUST_NUMBER from C_CUST_HFPROFILE where id = (select CUST_PROF_ID from E_VEHICLE where id="
				+ vehicleId + ")";
		db.resetSchema(schema);
		return db.executeQuery(sql, "CUST_NUMBER", 0);
	}

	/**
	 * Edit co-owner information in edit coOwner info page
	 * 
	 * @param OwnerInfo
	 * @return
	 */
	public void editCoOwnerInfo(OwnerInfo ownerUpdate) {
		LicMgrVehicleEditCoOwnerWidget viewCoOwnerPg = LicMgrVehicleEditCoOwnerWidget
				.getInstance();
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		coOwnerPg.clickCoOwnerNum(ownerUpdate.id);
		viewCoOwnerPg.waitLoading();
		viewCoOwnerPg.editCoOwnerInfo(ownerUpdate);
		ajax.waitLoading();
		coOwnerPg.waitLoading();
	}

	/**
	 * Go to search vehicle page from top menu.
	 */
	public void gotoSearchVehiclePage() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage
				.getInstance();

		logger.info("Go to search vehicle page from top menu.");
		topMenu.clickVehicles();
		vehicleSearchPg.waitLoading();
	}

	/**
	 * Remove all the co-owners for a vehicle.
	 */
	public void removeActiveCoOwner() {
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage
				.getInstance();
		List<String> coOwnerIds = coOwnerPg.getAllCoOwnerIdForVehicle();
		Iterator<String> it = coOwnerIds.iterator();
		while (it.hasNext()) {
			String coOwnerId = it.next();
			this.removeCoOwnerFromVehicleByID(coOwnerId, true);
			logger.info("Remove co-owner id:'" + coOwnerId
					+ "' from current vehicle");
		}
	}

	/**
	 * This method used to go to bulletin search page, one the main menu,do one
	 * of the following: 1)if the drop-down list displays Bulletins,click Admin
	 * 2)if the drop-down list does not display Bulletins, select Bulletins.
	 */
	public void gotoBulletinSearchPgFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsBulletinSearchPage searchPg = OrmsBulletinSearchPage.getInstance();
		String adminValue = topMenuPg.getAdminValue();

		logger.info("Go to Product Configuration Page From Top Menu");

		if (!adminValue.equals("Bulletins")) {
			topMenuPg.selectAdminOptions("Bulletins");
		} else {
			topMenuPg.clickAdmin();
		}

		searchPg.waitLoading();
	}

	/**
	 *This method start from the bulletin search page and end at new bulletin
	 * detail page, the workflow is search the location and category and click
	 * the select button for the searched result and go to the add new bulletin
	 * page
	 * 
	 * @param bulletin
	 */
	public void gotoAddNewBulletinPageFromSearchPg(BulletinInfo bulletin) {
		OrmsBulletinSearchPage buletinPg = OrmsBulletinSearchPage.getInstance();
		OrmsBulletinLocationPage locPg = OrmsBulletinLocationPage.getInstance();
		OrmsBulletinDetailPage detailPg = OrmsBulletinDetailPage.getInstance();
		logger.info("Go to add new bulletin detail page...");
		buletinPg.clickAddNewBulletin();
		locPg.waitLoading();
		locPg.searchLocation(bulletin.location, bulletin.locCategory);
		locPg.waitLoading();
		locPg.clickSelect();
		detailPg.waitLoading();
	}

	/**
	 *This method start from the bulletin search page and end at bulletin
	 * detail page, the workflow is search the bulletin according to the
	 * information of bulletin and click the first search result to go to the
	 * bulletin detail page
	 * 
	 * @param bulletin
	 */
	public void gotoBulletinDetailPage(String headline) {
		OrmsBulletinSearchPage bulletinSchPg = OrmsBulletinSearchPage
				.getInstance();
		OrmsBulletinDetailPage bulletinDetailPg = OrmsBulletinDetailPage
				.getInstance();
		logger
				.info("Go to bulletin detail page from bulletin search page according to bulletin info......");
		bulletinSchPg.searchByHeadLine(headline);
		ajax.waitLoading();
		bulletinSchPg.waitLoading();
		bulletinSchPg.clickFirstbulletinId();
		bulletinDetailPg.waitLoading();
	}

	/*
	 * This method used to create a new bulletin, the workflow is first select a
	 * location and then set up bulletin detail and then click ok in bulletin
	 * detail page, end at bulletin search page.
	 * 
	 * @param bulletin
	 */
	public void createNewBulletin(BulletinInfo bulletin) {
		OrmsBulletinDetailPage detailPg = OrmsBulletinDetailPage.getInstance();
		OrmsBulletinSearchPage buletinPg = OrmsBulletinSearchPage.getInstance();
		logger.info("Start to Add New Bulletin ...");
		this.gotoAddNewBulletinPageFromSearchPg(bulletin);
		detailPg.setupBulletinInfo(bulletin);
		detailPg.clickApply();
		detailPg.waitLoading();
		bulletin.id = detailPg.getBulletinId();
		detailPg.clickOK();
		buletinPg.waitLoading();
	}

	/**
	 *This method start from the customer detail default page and go to show
	 * customer noteAlert page by click noteAlert subtab
	 */
	public void gotoNotesAndAlertsSubTabFromCustomerDetailsPg() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomerNoteAndAlertPage noteAndAlert = LicMgrCustomerNoteAndAlertPage
				.getInstance();
		logger.info("Go to noteAndAlert page from customerDetails page.");
		custDetailsPg.clickNotesAndAlertsTab();
		ajax.waitLoading();
		noteAndAlert.waitLoading();
	}

//	/**
//	 *This method is used to add noteAlert from customer noteAlert list page
//	 * and end at customer noteAlert list page The work flow is go to add
//	 * noteAlert page and setup information for noteAlert and then click ok
//	 * button to go back to noteAlert list page
//	 * 
//	 * @param type
//	 * @param content
//	 * @param startdate
//	 * @param enddate
//	 */
//	public String AddNoteOrAlertForCustomer(String type, String content,
//			String startdate, String enddate) {
//		LicMgrCustomerNoteAndAlertPage noteAndAlert = LicMgrCustomerNoteAndAlertPage
//				.getInstance();
//		LicMgrCustomerAddNoteAlertPage addNoteAlert = LicMgrCustomerAddNoteAlertPage
//				.getInstance();
//		logger.info("Start to add note alert for customer...");
//		noteAndAlert.clickAddNoteAlert();
//		addNoteAlert.waitExists();
//		addNoteAlert.setNoteAlertInfo(noteAlertInfo);
//		addNoteAlert.clickOK();
//		String message = "";
//		if (addNoteAlert.checkErrorExist()) {
//			message = addNoteAlert.getErrorMess();
//		} else {
//			ajax.waitLoading();
//			noteAndAlert.waitExists();
//		}
//		return message;
//	}

	/**
	 * Add note or alert for customer.
	 * 
	 * @param noteAlertInfo
	 * @return
	 */
	public String addNoteOrAlertForCustomer(NoteAndAlertInfo noteAlertInfo) {
		LicMgrCustomerNoteAndAlertPage noteAndAlert = LicMgrCustomerNoteAndAlertPage
				.getInstance();
		LicMgrCustomerAddNoteAlertPage addNoteAlert = LicMgrCustomerAddNoteAlertPage
				.getInstance();

		logger.info("Add " + noteAlertInfo.type + " for customer...");
		noteAndAlert.clickAddNoteAlert();
		ajax.waitLoading();
		addNoteAlert.waitLoading();
		addNoteAlert.setNoteAlertInfo(noteAlertInfo);
		addNoteAlert.clickOK();
		ajax.waitLoading();

		String returnInfo = "";
		if (addNoteAlert.checkErrorExist()) {
			returnInfo = addNoteAlert.getErrorMess();
		} else {
			noteAndAlert.waitLoading();
			returnInfo = noteAndAlert.getNoteAlertID();
		}
		return returnInfo;
	}

	/**
	 * Add note or alert for customer.
	 * 
	 * @param noteAlertInfo
	 * @return
	 */
	public String addNoteOrAlertForOrder(NoteAndAlertInfo noteAlertInfo) {
		LicMgrPrivilegeOrderDetailsPage orderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrNoteAndAlertListPage noteAndAlert = LicMgrNoteAndAlertListPage
				.getInstance();
		LicMgrAddNoteAlertWidget addNoteAlert = LicMgrAddNoteAlertWidget
				.getInstance();

		logger.info("Add " + noteAlertInfo.type + " for customer...");

		// go to note and alert page.
		orderDetailPage.clickNoteAndAlert();
		noteAndAlert.waitLoading();

		noteAndAlert.clickAddNoteAndAlert();
		ajax.waitLoading();
		addNoteAlert.waitLoading();
		addNoteAlert.setNoteAlertInfo(noteAlertInfo);
		addNoteAlert.clickOK();
		ajax.waitLoading();

		String returnInfo = StringUtil.EMPTY;
		if (addNoteAlert.checkErrorExist()) {
			returnInfo = addNoteAlert.getErrorMess();
		} else {
			noteAndAlert.waitLoading();
			returnInfo = noteAndAlert.getNoteAlertID();
		}
		return returnInfo;
	}

	/**
	 * Go to edit vehicle lien page.
	 * 
	 * @param lienID
	 */
	public void gotoLienDetailPage(String lienID) {
		LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage
				.getInstance();
		LicMgrEditVehicleLienDetailsWidget editLienPg = LicMgrEditVehicleLienDetailsWidget
				.getInstance();
		logger.info("Go to vehicle lien detaile info page.");

		lienListPg.clickLienId(lienID);
		ajax.waitLoading();
		editLienPg.waitLoading();
		editLienPg.removeFocus();
	}

	public void gotoAgentDetailPgFromVendorSearchPg(String agentId) {
		LicMgrVendorSearchListPage searchPg = LicMgrVendorSearchListPage
				.getInstance();
		LicMgrStoreDetailsPage detailPg = LicMgrStoreDetailsPage.getInstance();

		logger.info("Goto Agent '" + agentId + "' detail Page.");

		searchPg.setStoreID(agentId);
		searchPg.clickSearch();
		ajax.waitLoading();
		detailPg.waitLoading();
	}

	public void printActivityByCustomerReport(String enterDate, String path,boolean isIncludeSummary) {
		LicMgrStoreActivityCustomerSubPage custPg = LicMgrStoreActivityCustomerSubPage
				.getInstance();
		OrmsOnlineReportProcessingPage rptPg = OrmsOnlineReportProcessingPage
				.getInstance();
		LicMgrIncludePrdRevenueSummaryConfirmWidget widget = LicMgrIncludePrdRevenueSummaryConfirmWidget.getInstance();

		logger.info("Print Activity By Customer Report on " + enterDate);

		custPg.setEnterDate(enterDate);
		custPg.clickFirstGo();
		ajax.waitLoading();
		custPg.waitLoading();
		custPg.clickPrintByCustomerRpt();
		widget.waitLoading();
		widget.selectYesOrNoRadio(isIncludeSummary);
		widget.clickOK();
		ajax.waitLoading();
		rptPg.waitLoading();
		this.downloadFile(path);
		// rptPg.close();
	}

	/**
	 * Verify One Report by compare the report with given template file
	 * 
	 * @param templatesPath
	 * @param comparedPath
	 * @param errorResultPath
	 */
	public boolean verifyPdfReport(String templatesPath, String comparedPath) {
		logger.info("Verify PDF format Report.");

		boolean isCorrect = true;
		try {
			if (!PDFParser.verifyRptRunDate(comparedPath)) {
				isCorrect = false;
			}
			File file = new File(comparedPath);
			List<String> list = PDFParser.comparePDFFile(templatesPath + "\\"
					+ file.getName(), comparedPath);
			for (Object l : list) {
				if (l instanceof String) {
					isCorrect = false;
				} else if (l instanceof List<?>) {
					if (((List<?>) l).size() > 0) {
						isCorrect = false;
					}
				} else {
					throw new RuntimeException("Unknown Object Type.");
				}
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * Verify Excel format report content
	 * 
	 * @param templatesPath
	 * @param comparePath
	 */
	public boolean verifyExcelReport(String templatesPath, String comparePath) {
		logger.info("Verify Excel Format Report Content.");

		boolean isCorrect = true;
		List<String> resultMsg = this.compareExcelReport(templatesPath,
				comparePath);
		if (resultMsg.size() > 0) {
			logger.error("Excel Format Report Content Not Correct.");
			isCorrect = false;
		}

		return isCorrect;
	}

	public List<String> compareExcelReport(String templatesPath,
			String comparePath) {
		logger.info("Compare Excel Report from [" + templatesPath + "] and ["
				+ comparePath + "].");

		File file = new File(comparePath);
		if (!file.exists()) {
			throw new ItemNotFoundException("File not exist for " + comparePath);
		}
		String fromPath = templatesPath + "\\" + file.getName();
		List<String> resultMsg = ExcelParser.match(fromPath, comparePath);
		return resultMsg;
	}

	/**
	 * This method used to verify text/csv report content correct
	 * 
	 * @param templatePath
	 * @param comparePath
	 * @return
	 */
	public boolean verifyTextReport(String templatePath, String comparePath) {
		logger.info("Verify Text Format Report Content.");

		boolean isCorrect = true;
		File file = new File(comparePath);
		if (!file.exists()) {
			return false;
		}
		String existFile = templatePath + "\\" + file.getName();
		try {
			List<String> resultMsg = FileUtil.compareFile(existFile,
					comparePath);
			if (resultMsg.size() > 0) {
				logger.error("Text/CSV Format Report Content Not Correct.");
				isCorrect = false;
			} else {
				logger.info("Report content Correct.");
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * This method inspect a vehicle from home page and go back to home page
	 * after the inspctation request
	 * 
	 * @param customer
	 * @param vehicle
	 * @param pay
	 * @param isPrinted
	 * @return
	 */
	public String inspectVehicle(Customer customer, Payment pay,
			Vehicle vehicle, boolean isPrinted) {
		logger.info("Start to inspect a vehicle from home page.");
		this.inspectVehicleToOrderCartFromHomePg(customer, vehicle);
		String inspOrdNum = this.processOrderCartToOrderSummaryPage(pay,
				isPrinted);
		this.finishOrder();
		return inspOrdNum;
	}

	/**
	 * Go to supply order search page from top menu
	 */
	public void gotoSuppliesOrderPgFromTop() {
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		LicMgrSupplyOrderSearchPage supplyOrderSearchPg = LicMgrSupplyOrderSearchPage
				.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		logger.info("Go to supplies order search page from top menue.");
		homePg.clickOrders();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickSuppliesOrdersTab();
		ajax.waitLoading();
		supplyOrderSearchPg.waitLoading();
	}

	/**
	 * This method fulfill all the product in current suppplies order fulfill
	 * page It starts from supplies order search page, and ends at supplies
	 * order search page after fulfill the order in current fulfill page with
	 * special carrier
	 * 
	 * @param carrier
	 */
	public void fulfillOrder(CarrierInfo carrier, StoreInfo store,
			String orderNum) {
		LicMgrSupplyOrderSearchPage supplyOrderSearchPg = LicMgrSupplyOrderSearchPage
				.getInstance();
		LicMgrSuppliesOrdersFulfillPage fulfillPg = LicMgrSuppliesOrdersFulfillPage
				.getInstance();
		logger.info("Fulfill order in current page with carrier: "
				+ carrier.carrier + ".");
		supplyOrderSearchPg.clickFulfillOrder();
		ajax.waitLoading();
		fulfillPg.waitLoading();
		fulfillPg.selectAgent(store.storeName + "(" + store.storeID + ")");
		fulfillPg.clickGo();
		ajax.waitLoading();
		fulfillPg.setupCarrierInfo(carrier);
		if (StringUtil.isEmpty(orderNum)) { // select all
			fulfillPg.selectAllProductOnPage();
		} else { // select the specif one
			fulfillPg.selectSupplyOrderByOrderNum(orderNum);
		}
		fulfillPg.clickFulfillSelectedItems();
		ajax.waitLoading();
		IBrowser newBrowser = Browser.getInstance().getBrowser("title", "Processing Report");
		newBrowser.close();
		// fulfillPg.waitExists();
		fulfillPg.clickSuppliesOrdersLabel();
		ajax.waitLoading();
		supplyOrderSearchPg.waitLoading();
	}

	/**
	 * This method search the fulfillment with some filter and print the
	 * fulfillment saved as a pdf report
	 * 
	 * @param agent
	 * @param fromDate
	 * @param toDate
	 * @param trackNumber
	 * @param fileName
	 */
	public void printPakingSlip(String agent, String fromDate, String toDate,
			String trackNumber, String fileName) {
		LicMgrSupplyOrderSearchPage supplyOrderSearchPg = LicMgrSupplyOrderSearchPage
				.getInstance();
		LicMgrSuppliesOrdersFulfillSearchPage fulfillSearchPg = LicMgrSuppliesOrdersFulfillSearchPage
				.getInstance();
		OrmsOnlineReportProcessingPage processinPg = OrmsOnlineReportProcessingPage
				.getInstance();
		logger.info("Start to print paking slip.");
		supplyOrderSearchPg.clickViewFulfillment();
		ajax.waitLoading();
		fulfillSearchPg.waitLoading();
		fulfillSearchPg.selectAllFulfillmentInList();
		fulfillSearchPg.searchFulfillment(agent, fromDate, toDate, trackNumber);
		fulfillSearchPg.selectFulfillmentInCurrentList();
		logger.info("Save the fulfillment as file " + fileName);
		fulfillSearchPg.clickPrintPackingSlip();
		ajax.waitLoading();
		processinPg.waitLoading();
		downloadReport(fileName);
		// processinPg.close();
		fulfillSearchPg.waitLoading();
	}

	/**
	 * Go to vehicle inspections search/list page from top menu
	 */
	public void gotoVehicleInspectionSearchPage() {
		LicMgrTopMenuPage topMenuPage = LicMgrTopMenuPage.getInstance();
		LicMgrVehiclesSearchPage vehicleSearchPage = LicMgrVehiclesSearchPage
				.getInstance();
		LicMgrVehicleInspectionSearchPage inspectionSearchPage = LicMgrVehicleInspectionSearchPage
				.getInstance();
		logger.info("Go to Vehicle Title search/list page from top menu.");
		topMenuPage.clickVehicles();
		vehicleSearchPage.waitLoading();
		vehicleSearchPage.switchToInspectionsTab();
		ajax.waitLoading();
		inspectionSearchPage.waitLoading();
	}

	/**
	 * Go to vehicle inspection detail page from inspection search page. It
	 * search the inspection according to its id, and go to the detail page by
	 * clicking id link
	 * 
	 * @param inspectionId
	 */
	public void gotoVehicleInspectionDetailPage(String inspectionId) {
		LicMgrVehicleInspectionSearchPage inspectionSearchPage = LicMgrVehicleInspectionSearchPage
				.getInstance();
		LicMgrInspectionDetailsPage inspectionDetailPage = LicMgrInspectionDetailsPage
				.getInstance();
		logger.info("Go to detail page of inspection with Id: " + inspectionId);
		inspectionSearchPage.searchInspectionById(inspectionId);
		inspectionSearchPage.clickInspectionWithId(inspectionId);
		inspectionDetailPage.waitLoading();
	}

	/**
	 * This method assign an officer to the inspcetion in inspection detail page
	 * 
	 * @param officer
	 */
	public void assignOfficerToInspction(String officer, String requestReason) {
		LicMgrInspectionDetailsPage inspectionDetailPage = LicMgrInspectionDetailsPage
				.getInstance();
		LicMgrInspectionAssignOfficerWidget officerWidget = LicMgrInspectionAssignOfficerWidget
				.getInstance();
		LicMgrVehicleInspectionSearchPage inspectionSearchPage = LicMgrVehicleInspectionSearchPage
				.getInstance();
		logger.info("Assign an officer:" + officer + " to inspection.");
		inspectionDetailPage.switchToOfficersTab();
		inspectionDetailPage.clickAddChangeOfficerAssignment();
		officerWidget.waitLoading();
		officerWidget.selectOfficer(officer);
		officerWidget.clickOk();
		ajax.waitLoading();
		inspectionDetailPage.waitLoading();
		inspectionDetailPage.selectRequestReason(requestReason);
		inspectionDetailPage.clickOk();
		ajax.waitLoading();
		inspectionSearchPage.waitLoading();
	}

	/**
	 * Apply EFT Invoice payment in Store detail info page Start from Agent
	 * detail info page End to Agent detail info page-->EFT Invoice Payments
	 * page.
	 * 
	 * @return: payment amount.
	 */
	public String applyInvoicePayment() {
		LicmgrApplyPaymentWidget applyPaymentPg = LicmgrApplyPaymentWidget
				.getInstance();
		LicMgrStoreEFTInvoicePaymentsPage invoicePaymentPg = LicMgrStoreEFTInvoicePaymentsPage
				.getInstance();

		String payAmount;

		logger.info("Apply EFT Invoice payment on Agent detail page");

		this.gotoStoreEFTInvoicePaymentPage();

		// apply EFT payment
		invoicePaymentPg.clickApplyInvoicePayment();
		ajax.waitLoading();
		applyPaymentPg.waitLoading();
		applyPaymentPg.setNote("Apply payment to EFT Invoice.");

		payAmount = applyPaymentPg.getPayAmount();
		applyPaymentPg.clickOK();
		ajax.waitLoading();
		invoicePaymentPg.waitLoading();

		return ((payAmount != null) ? payAmount : StringUtil.EMPTY);
	}

	public void gotoStoreEFTInvoicePaymentPage() {
		LicMgrStoreDetailsPage stroeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreEFTInvoicePaymentsPage invoicePaymentPg = LicMgrStoreEFTInvoicePaymentsPage
				.getInstance();

		logger.info("Go to Store EFT Invoice Payment Page....");

		// go to EFT invoice payment sub page
		stroeDetailPg.clickEFTInvoicePaymentsTab();
		ajax.waitLoading();
		invoicePaymentPg.waitLoading();
	}

	/**
	 * Go to EFT invoice payment apply page from Agent detail page.
	 * 
	 * @param: invoice, invoice number(ID) of which you want to watch the
	 *         payment apply.
	 * 
	 */
	public void gotoEFTInvoicePaymentApplyPage(String invoice) {
		LicMgrStoreEFTInvoiceListPage listPg = LicMgrStoreEFTInvoiceListPage
				.getInstance();
		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();

		logger.info("Go to EFT Invoice Payment Apply page of INVOICE: "
				+ invoice + "...");

		this.gotoStoreInvoiceListPageFromStoreDetailPg();

		listPg.gotoInvoiceDetailByInvoiceNum(invoice);
		ajax.waitLoading();
		detailPg.waitLoading();

		detailPg.clickShowEFTInvoicePaymentApply();
		ajax.waitLoading();
		detailPg.waitLoading();

	}

	/**
	 * Go to Daily EFT summary list page from Agent detail page.
	 * 
	 * @param: invoice, invoice number(ID) of which you want to watch the
	 *         summary.
	 * 
	 */
	public void gotoStoreInvoiceDetailPgFromStoreDetailPg(String invoice) {
		LicMgrStoreEFTInvoiceListPage listPg = LicMgrStoreEFTInvoiceListPage
				.getInstance();
		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();

		logger.info("Go to Daily EFT Summary page of INVOICE: " + invoice
				+ "...");

		this.gotoStoreInvoiceListPageFromStoreDetailPg();

		listPg.gotoInvoiceDetailByInvoiceNum(invoice);
		ajax.waitLoading();
		detailPg.waitLoading();

	}

	/**
	 * Go to store invoice voucher internal GC record page from store invoice
	 * detail page.
	 * 
	 * @param postedDate
	 */
	public void gotoStoreInvoiceVoucherInternalGCRecordPgFromStoreInvoiceDetailPg(
			String postedDate) {
		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();
		LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget voucherInternalGCRecordPg = LicMgrStoreEFTInvoiceVoucherInternalGCRecordWidget
				.getInstance();

		logger
				.info("Go to store invoice voucher internal GC record page from store invoice detail page.");
		detailPg.clickPaymentAllocation(postedDate, "Voucher / Internal GC");
		ajax.waitLoading();
		voucherInternalGCRecordPg.waitLoading();
	}

	/**
	 * Go to vendor invoice list page from vendor detail page.
	 * 
	 * 
	 */
	public void gotoVendorInvoiceListPage() {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrVendorEFTInvoiceListPage invoiceListPg = LicMgrVendorEFTInvoiceListPage
				.getInstance();

		logger
				.info("Go to Vendor invoice list page from Vendor detail page...");
		vendorDetailPg.clickInvoices();
		ajax.waitLoading();

		invoiceListPg.waitLoading();

	}

	/**
	 * Go to store invoice list page from store detail page.
	 * 
	 * 
	 */
	public void gotoStoreInvoiceListPageFromStoreDetailPg() {
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage
				.getInstance();
		LicMgrStoreEFTInvoiceListPage invoiceListPg = LicMgrStoreEFTInvoiceListPage
				.getInstance();

		logger.info("Go to Store invoice list page from Store detail page...");
		storeDetailPg.clickInvoices();
		ajax.waitLoading();

		invoiceListPg.waitLoading();

	}

	/**
	 * Go to Daily EFT Invoice payment allocation page from EFT invoice
	 * detail(summary) page.
	 * 
	 * @param: postedDate, record of which the posted Date is 'postedDate'
	 * @param: type, (Sales/Voids Pending Doc Return/Charged Voids/... this is
	 *         the column name of list table)
	 */
	public void gotoEFTInvoicePaymentAllocationPage(String postedDate,
			String type) {

		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();
		LicMgrStoreEFTInvoicePaymentAllocationWidget paymentAllocation = LicMgrStoreEFTInvoicePaymentAllocationWidget
				.getInstance();

		logger
				.info("Go to EFT invoice payment allocation page of posted Date: "
						+ postedDate + ", type is " + type + "...");

		detailPg.clickPaymentAllocation(postedDate, type);
		ajax.waitLoading();
		paymentAllocation.waitLoading();

	}

	/**
	 * Search invoice payment allocation records on page
	 * LicMgrStoreEFTInvoicePaymentAllocationWidget return back to
	 * LicMgrStoreEFTInvoiceDetailPage
	 * 
	 * @param: searchCondition, (EFTPaymentAllocationRecord)
	 */

	public List<EFTPaymentAllocationRecord> searchAndGetInvoicePaymentAllocationRecords(
			EFTPaymentAllocationRecord searchCondition) {
		LicMgrStoreEFTInvoiceDetailPage detailPg = LicMgrStoreEFTInvoiceDetailPage
				.getInstance();
		LicMgrStoreEFTInvoicePaymentAllocationWidget paymentAllocationPg = LicMgrStoreEFTInvoicePaymentAllocationWidget
				.getInstance();

		logger.info("Search invoice payment allocation records...");

		List<EFTPaymentAllocationRecord> result;

		paymentAllocationPg.searchByCriteria(searchCondition);
		result = paymentAllocationPg.getPaymentAllocationRecordsOnPage();
		paymentAllocationPg.clickClose();

		ajax.waitLoading();
		detailPg.waitLoading();

		return result;

	}

	/**
	 * This method print inspection form in inspection detail page and save the
	 * form as a report file
	 * 
	 * @param reportFile
	 */
	public void printInspectionForm(String fileName) {
		LicMgrInspectionDetailsPage inspectionDetailPage = LicMgrInspectionDetailsPage
				.getInstance();
		OrmsOnlineReportProcessingPage processinPg = OrmsOnlineReportProcessingPage
				.getInstance();
		logger.info("Start to print inspection form.");
		inspectionDetailPage.clickPrintInspectionForm();
		processinPg.waitLoading();
		downloadReport(fileName);
		if (processinPg.exists()) {
			processinPg.close();
		}
		ajax.waitLoading();
		inspectionDetailPage.waitLoading();
	}

	/**
	 * Adjusting Supply Product Inventory. Start from Supplies list page and end
	 * at Supplies list page
	 * 
	 * @param supply
	 * @return
	 * @author Lesley Wang
	 * @date Sep 1, 2012
	 */
	public String adjustSupplyProductInventory(SupplyInfo supply) {
		LicMgrSuppliesListPage suppliesListPg = LicMgrSuppliesListPage
				.getInstance();
		LicMgrAdjustPOSProductInventoryWidget adjustInvDialog = LicMgrAdjustPOSProductInventoryWidget
				.getInstance();
		logger.info("Adjusting Supply Product Inventory for the product code="
				+ supply.code);

		suppliesListPg.clickAdjust(supply.code);
		ajax.waitLoading();
		adjustInvDialog.waitLoading();
		String originalQuantity = adjustInvDialog.getQuantityOnHand();
		adjustInvDialog.setupAdjustmentInfo(supply);
		adjustInvDialog.clickOK();
		ajax.waitLoading();
		suppliesListPg.waitLoading();
		return originalQuantity;
	}

	/**
	 * Go to Supply Inventory Tracking page from supply details page
	 * 
	 * @author Lesley Wang
	 * @date Sep 4, 2012
	 */
	public void gotoSupplyInventoryTrackingPgFromDetailsPg() {
		LicMgrSupplyProductDetailsPage detailPg = LicMgrSupplyProductDetailsPage
				.getInstance();
		LicMgrSupplyInventoryTrackingPage invTrackingPg = LicMgrSupplyInventoryTrackingPage
				.getInstance();
		logger
				.info("Go to Supply Inventory Tracking page from supply details page...");

		detailPg.clickInventoryTracking();
		invTrackingPg.waitLoading();
	}

	/**
	 * Go to order suppliers list page from LM top menu
	 * 
	 * @author Lesley Wang
	 * @date Sep 4, 2012
	 */
	public void gotoOrderSuppliesListPgFromTopMenu() {
		LicMgrOrderSuppliesListPage suppliesPg = LicMgrOrderSuppliesListPage
				.getInstance();
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		String adminValue = topMenu.getAdminValue();

		logger.info("Go to order suppliers list page from LM top menu.");
		if (!suppliesPg.exists()) {
			if (!adminValue.equals("Order Supplies")) {
				topMenu.selectAdminOptions("Order Supplies");
			} else {
				topMenu.clickAdmin();
			}
			ajax.waitLoading();
			suppliesPg.waitLoading();
		}
	}

	/**
	 * Add supplies product to cart. Start from Order Supplies Product list page
	 * to order cart page
	 * 
	 * @param code
	 * @param name
	 * @param qty
	 * @author Lesley Wang
	 * @date Sep 4, 2012
	 */
	public void addSuppliesProductToCart(String code, String name, String qty) {
		LicMgrOrderSuppliesListPage suppliesPg = LicMgrOrderSuppliesListPage
				.getInstance();
		OrmsOrderCartPage orderCart = OrmsOrderCartPage.getInstance();

		logger.info("Add suppplies prodct: " + code + "-" + name
				+ " to cart...");
		suppliesPg.addProductToCart(code, name, qty);
		suppliesPg.clickGoToCart();
		orderCart.waitLoading();
	}

	/**
	 * Approve supply order. Start from Supplies Approve Order page, end at the
	 * same page or the order cart page
	 * 
	 * @param ordNum
	 * @param qty
	 * @param notes
	 * @author Lesley Wang
	 * @date Sep 4, 2012
	 */
	public void approveSupplyOrder(String ordNum, String qty, String notes) {
		LicMgrSupplyOrderSearchPage ordSearchPg = LicMgrSupplyOrderSearchPage
				.getInstance();
		LicMgrSuppliesApproveOrderPage approvePage = LicMgrSuppliesApproveOrderPage
				.getInstance();
		OrmsOrderCartPage orderCart = OrmsOrderCartPage.getInstance();

		logger.info("Approve Supply order: " + ordNum);
		ordSearchPg.clickApproveOrder();
		ajax.waitLoading();
		approvePage.waitLoading();
		approvePage.selectSupplyOrderToApprove(ordNum, notes, qty);
		approvePage.clickApproveSelectedOrders();
		ajax.waitLoading();
		browser.waitExists(orderCart, approvePage);
	}

	/**
	 * Go to supplies order search page from supplies approve order page
	 * 
	 * @author Lesley Wang
	 * @date Sep 5, 2012
	 */
	public void gotoSuppliesOrderSearchPgFromOrderApprovePg() {
		LicMgrSupplyOrderSearchPage ordSearchPg = LicMgrSupplyOrderSearchPage
				.getInstance();
		LicMgrSuppliesApproveOrderPage approvePage = LicMgrSuppliesApproveOrderPage
				.getInstance();

		logger
				.info("Go to supplies order search page from supplies approve order page");
		approvePage.clickSuppliesOrdersTab();
		ajax.waitLoading();
		ordSearchPg.waitLoading();
	}

	public void gotoUserListPageFromTopMenu() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrUserListPage userListPage = LicMgrUserListPage.getInstance();

		logger.info("Goto User list page from top drop down list.");
		topMenu.selectAdminOptions("Users");
		userListPage.waitLoading();
	}

	public void gotoUserDetailsPageFromListPage(String userName) {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();

		logger.info("Goto User(UserName=" + userName
				+ ") details page from list page.");
		listPage.searchUser("User Name", userName);
		listPage.clickUserNameLink(userName);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}

	public void gotoUserListPageFromDetailsPage() {
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();

		logger.info("Go to user list page from details page.");
		detailsPage.clickCancel();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	/**
	 * create user
	 * 
	 * @param user
	 * @return user name
	 */
	public String createUser(User user) {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialog = LicMgrConfirmDialogWidget
				.getInstance(true);

		logger.info("Create User.");
		listPage.clickAddUser();
		detailsPage.waitLoading();
		detailsPage.setupUserInfo(user);
		detailsPage.clickApply();
		ajax.waitLoading();
		confirmDialog.waitLoading();
		confirmDialog.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
		String userName = detailsPage.getUserName();
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();

		return userName;
	}

	public void editUser(User user) {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();

		logger.info("Edit User(UserName" + user.userName + ").");
		this.gotoUserDetailsPageFromListPage(user.userName);
		detailsPage.setupUserInfo(user);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	public void lockUser(User user) {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();

		logger.info("Lock User(UserName" + user.userName + ").");
		this.gotoUserDetailsPageFromListPage(user.userName);
		detailsPage.clickLock();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	public void unlockUser(User user) {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();

		logger.info("Unlock User(UserName" + user.userName + ").");
		this.gotoUserDetailsPageFromListPage(user.userName);
		detailsPage.clickUnlock();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	public void deleteUser(String userName) {
		this.gotoUserDetailsPageFromListPage(userName);
		deleteUser();
	}

	public void deleteUser() {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		LicMgrUserDetailsPage detailsPage = LicMgrUserDetailsPage.getInstance();

		String userName = detailsPage.getUserName();
		logger.info("Delete User(UserName=" + userName + ").");
		detailsPage.clickDelete();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	/**
	 * Assign single role/location to an existing user, this workflow starts
	 * with 'User Roles & Locations' page
	 * 
	 * @param role
	 * @param location
	 */
	public void assignRoleLocation(String role, String location) {
		LicMgrUserRolesLocationsPage roleLocationPage = LicMgrUserRolesLocationsPage
				.getInstance();
		LicMgrAssignRoleLocationWidget roleLocationWidget = LicMgrAssignRoleLocationWidget
				.getInstance();

		logger.info("Assign Role - " + role + ", Location - " + location
				+ " to User - " + roleLocationPage.getUserName());
		roleLocationPage.clickAssignRoleLocation();
		ajax.waitLoading();
		roleLocationWidget.waitLoading();
		roleLocationWidget.selectRoleLocation(role, location);
		roleLocationWidget.clickOK();
		ajax.waitLoading();
		roleLocationPage.waitLoading();
	}

	/**
	 * Assign multiple roles/locations to an existing user, this workflow starts
	 * with 'User Roles & Locations' page
	 * 
	 * @param roles
	 * @param locations
	 */
	public void assignRoleLocation(String roles[], String locations[]) {
		LicMgrUserRolesLocationsPage roleLocationPage = LicMgrUserRolesLocationsPage
				.getInstance();
		LicMgrAssignRoleLocationWidget roleLocationWidget = LicMgrAssignRoleLocationWidget
				.getInstance();

		logger.info("Assign " + roles.length + " Roles, " + locations.length
				+ " Locations to User - " + roleLocationPage.getUserName());
		roleLocationPage.clickAssignRoleLocation();
		ajax.waitLoading();
		roleLocationWidget.waitLoading();
		roleLocationWidget.selectRoleLocation(roles, locations);
		roleLocationWidget.clickOK();
		ajax.waitLoading();
		roleLocationPage.waitLoading();
	}

	/**
	 * This method add a new quota for hunt in add hunt page, it starts from add
	 * hunt page and end at add hunt page
	 * 
	 * @param quota
	 */
	public void addHuntQuotaFromHuntDetailPg(QuotaInfo quota) {
		LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
		LicMgrAddNewHuntQuotaWidget quotaWidget = LicMgrAddNewHuntQuotaWidget
				.getInstance();
		addHuntPg.clickAddNewHuntQuota();
		quotaWidget.waitLoading();
		quotaWidget.addQuota(quota);
		logger.info("Add a new quota for hunt, its description is "
				+ quota.getDescription());
		ajax.waitLoading();
		addHuntPg.waitLoading();
	}

	/**
	 * This method add a new location for hunt in add hunt page, it starts from
	 * add hunt page and end at add hunt page
	 * 
	 * @param location
	 */
	public void addHuntLocationFromHuntDetailPg(LocationInfo location) {
		LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
		LicMgrAddNewHuntLocationWidget locationWidget = LicMgrAddNewHuntLocationWidget
				.getInstance();
		addHuntPg.clickAddNewHuntLocation();
		locationWidget.waitLoading();
		locationWidget.addHuntLocation(location);
		logger.info("Add a new location for hunt, it is:" + location.getCode()
				+ "-" + location.getDescription());
		ajax.waitLoading();
		addHuntPg.waitLoading();
	}

	/**
	 * This method add a new date period for hunt in add hunt page, it starts
	 * from add hunt page and end at add hunt page
	 * 
	 * @param location
	 */
	public void addHuntDatePeriodFromHuntDetailPg(DatePeriodInfo datePeriod) {
		LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
		LicMgrAddNewHuntDatePeriodWidget datePeroidWidget = LicMgrAddNewHuntDatePeriodWidget
				.getInstance();
		addHuntPg.clickAddNewDatePeriod();
		datePeroidWidget.waitLoading();
		datePeroidWidget.addDatePeriod(datePeriod);
		logger.info("Add a new date period for hunt, it is:"
				+ datePeriod.getCode() + "-" + datePeriod.getDescription());
		ajax.waitLoading();
		addHuntPg.waitLoading();
	}

	/**
	 * This method is used to add a new hunt, note that if any of
	 * quota/location/datePeriod is not null, it'll add a new
	 * quota/location/datePeriod first and set quota/location/datePeriod into
	 * hunt info
	 * 
	 * @param quota
	 * @param location
	 * @param datePeriod
	 * @param hunt
	 * @return
	 */
	public void addHuntFromHuntListPage(QuotaInfo quota, LocationInfo location,
			DatePeriodInfo datePeriod, HuntInfo hunt) {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		LicMgrAddHuntSelectSpeciesWidget selectSpecieWiget = LicMgrAddHuntSelectSpeciesWidget
				.getInstance();
		LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
		logger.info("Start to add a new hunt.");
		huntsListPg.clickAddHunt();
		selectSpecieWiget.waitLoading();
		selectSpecieWiget.selectSpecie(hunt.getSpecie());
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
		if (quota != null) {
			this.addHuntQuotaFromHuntDetailPg(quota);
			hunt.setHuntQuotaDescription(quota.getDescription());
		}
		if (location != null) {
			this.addHuntLocationFromHuntDetailPg(location);
		}
		if (datePeriod != null) {
			this.addHuntDatePeriodFromHuntDetailPg(datePeriod);
		}
		ajax.waitLoading();
		addHuntPg.waitLoading();
		addHuntPg.setUpHuntInfoAndClickOk(hunt);
		Object page = browser.waitExists(huntsListPg, addHuntPg);
		if (page == addHuntPg) {
			throw new ErrorOnPageException("Fail to add hunt with code="+hunt.getHuntCode() + " due to " + addHuntPg.getErrorMess());
		}
		logger.info("A new hunt with code:" + hunt.getHuntCode()
				+ " has been added!");
	}

	public void addHuntFromHuntListPage(HuntInfo hunt) {
		this.addHuntFromHuntListPage(null, null, null, hunt);
	}
	
	/**
	 * This method goes to hunt history page from hunt detail page
	 */
	public void gotoHuntHistoryPageFromDetailPage() {
		LicMgrHuntDetailPage detailPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntHistoryPage historyPg = LicMgrHuntHistoryPage.getInstance();
		logger.info("Go to hunt change history page.");
		detailPg.clickChangeHistory();
		historyPg.waitLoading();
	}

	/**
	 * This method goes to hunt detail page from hunt history page
	 */
	public void gotoHuntDetailPageFromHistoryPage() {
		LicMgrHuntDetailPage detailPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntHistoryPage historyPg = LicMgrHuntHistoryPage.getInstance();
		logger.info("Go to hunt detail page.");
		historyPg.clickReturnToHuntDetails();
		detailPg.waitLoading();
	}

	
	
	/**
	 * Edit hunt details from hunts list page
	 * @param hunt
	 */
	public void editHuntDetailsFromHuntsListPg(HuntInfo hunt) {
		LicMgrHuntDetailPage detailsPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		
		logger.info("Edit hunt details info from hunts list page for hunt code=" + hunt.getHuntCode());
		this.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		detailsPg.updateHuntsInfo(hunt);
		detailsPg.clickOK();
		huntsListPg.waitLoading();
	}
	
	/**
	 * Edit hunt status, start from hunt list page, end at hunt list page
	 * 
	 * @param status
	 */
	public void editHuntStatus(String status, String huntCode) {
		LicMgrHuntDetailPage detailPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();

		logger.info("Edit Hunt Status for " + status);
		this.gotoHuntDetailPgFromHuntsListPg(huntCode);
		detailPg.selectStatus(status);
		detailPg.clickOK();
		huntsListPg.waitLoading();
	}

	/**
	 * Active hunt, start from hunt list page, end at hunt list page
	 */
	public void activateHunt(String huntCode) {
		this.editHuntStatus("Active", huntCode);
	}

	/**
	 * Inactive hunt, start from hunt list page, end at hunt list page
	 */
	public void deactivateHunt(String huntCode) {
		this.editHuntStatus("Inactive", huntCode);
	}

	/**
	 * Search hunt by status and species
	 * 
	 * @param status
	 * @param species
	 */
	public void searchHunt(String status, String species) {
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		huntsListPg.setFilter(status, species);
		huntsListPg.clickGo();
		ajax.waitLoading();
		huntsListPg.waitLoading();
	}

	public void gotoHuntParametersListPg(String huntCode) {
		LicMgrHuntDetailPage huntDetailsPg = LicMgrHuntDetailPage.getInstance();
		LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		
		if (!huntsListPg.exists()) {
			this.gotoHuntsListPg();
		}
		this.gotoHuntDetailPgFromHuntsListPg(huntCode);
		logger.info("Go to hunt parameters list page...");
		huntDetailsPg.clickHuntParametersTab();
		paramListPg.waitLoading();
	}
	
	/**
	 * add hunt parameter
	 * 
	 * @param info
	 * @return id OR error message
	 */
	public List<String> addHuntParameter(HuntParameterInfo info) {
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrAddHuntParameterWidget addWidget = LicMgrAddHuntParameterWidget.getInstance();

		logger.info("Add Hunt Parameter Info.");
		paramListPg.clickAddParameter();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setParameterInfo(info);
		addWidget.clickOK();
		ajax.waitLoading();
		List<String> toReturn = new ArrayList<String>();
		Object page = browser.waitExists(addWidget, paramListPg);
		if (page == addWidget) {
			toReturn.add(addWidget.getErrorMsg());
		} else if (page == paramListPg) {
			paramListPg.waitLoading();
			paramListPg.searchHuntParameters(ACTIVE_STATUS);
			if(info.getDescriptAndValue().size() < 1){
				toReturn.add(paramListPg.getHuntParamID(info.getHuntParamDes()));
			}else{
				for(int i=0; i<info.getDescriptAndValue().size(); i++){
					toReturn.add(paramListPg.getHuntParamID(info.getDescriptAndValue().get(i).getDescription()));
				}
			}
		}
		return toReturn;
	}
	
	/**
	 * Add hunt parameters
	 * @param infos
	 */
	public List<String> addHuntParameters(HuntParameterInfo... infos) {
		List<String> ids = new ArrayList<String>();
		for (HuntParameterInfo info : infos) {
			ids.addAll(addHuntParameter(info));
		}
		return ids;
	}
	
	public String editHuntParameters(HuntParameterInfo parameterInfo){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget editWidget = LicMgrEditHuntParameterWidget.getInstance();
		String msg = "";
		logger.info("Edit parameter with id:" + parameterInfo.getHuntParamID());
		paramListPg.clickID(parameterInfo.getHuntParamID());
		ajax.waitLoading();
		editWidget.waitLoading();
		editWidget.editParameterInfoAndClickOk(parameterInfo);
		ajax.waitLoading();
		Object page = browser.waitExists(editWidget, paramListPg);
		if(page == editWidget){
			msg = editWidget.getErrorMsg();
		}
		return msg;
	}

	/**
	 * Deactivate hunt parameters
	 * @param paramDess
	 */
	public void deactivateHuntParameters(String... paramDess) {
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget detailsWidget = LicMgrEditHuntParameterWidget.getInstance();

		logger.info("Deactivate Hunt parameters: " + paramDess.toString());
		for (String paramDes : paramDess) {
			paramListPg.searchHuntParameters(ACTIVE_STATUS);
			String id = paramListPg.getHuntParamID(paramDes);
			if (!StringUtil.isEmpty(id)) {
				paramListPg.clickID(id);
				ajax.waitLoading();
				detailsWidget.waitLoading();
				detailsWidget.selectParamStatus(INACTIVE_STATUS);
				detailsWidget.clickOK();
				ajax.waitLoading();
				paramListPg.waitLoading();
			}
		}
	}
	
	public void deactiveAllActiveHuntParameters(){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget detailsWidget = LicMgrEditHuntParameterWidget.getInstance();
		paramListPg.searchHuntParameters(ACTIVE_STATUS);
		List<String> activeIds = paramListPg.getAllParameterIds();
		logger.info("Deactivate Hunt parameters with ids: " + activeIds.toString());
		for (String id : activeIds) {
			paramListPg.clickID(id);
			ajax.waitLoading();
			detailsWidget.waitLoading();
			detailsWidget.selectParamStatus(INACTIVE_STATUS);
			detailsWidget.clickOK();
			ajax.waitLoading();
			paramListPg.waitLoading();
		}
	}
	
	public void gotoHuntParametersDetailWidget(String paramDess){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget detailsWidget = LicMgrEditHuntParameterWidget.getInstance();
		logger.info("Go to hunt parameter detail widget: " + paramDess.toString());
			paramListPg.searchHuntParameters(ACTIVE_STATUS);
			String id = paramListPg.getHuntParamID(paramDess);
			if (!StringUtil.isEmpty(id)) {
				paramListPg.clickID(id);
				ajax.waitLoading();
				detailsWidget.waitLoading();
			}
	}
	
	public void gotoParameterListPageFromEditWidget(){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrEditHuntParameterWidget detailsWidget = LicMgrEditHuntParameterWidget.getInstance();
		detailsWidget.clickCancel();
		ajax.waitLoading();
		paramListPg.waitLoading();
	}
	
	public void gotoParameterListPageFromAddWidget(){
		LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
		LicMgrAddHuntParameterWidget detailsWidget = LicMgrAddHuntParameterWidget.getInstance();
		detailsWidget.clickCancel();
		ajax.waitLoading();
		paramListPg.waitLoading();
	}

	/**
	 * update privilege details Info.
	 * 
	 * @param privilege
	 */
	public void updatePrivilegeDetailsInfo(PrivilegeInfo privilege) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info("Update privileges details info");
		privilegeDetailsPg.updatePrivilegeDetailsInfo(privilege);
		privilegeDetailsPg.clickOk();
		ajax.waitLoading();
		privilegePage.waitLoading();
	}

	public List<String> addDatePeriodLicenseYears(
			List<DatePeriodLicenseYearInfo> infos) {
		List<String> ids = new ArrayList<String>();
		for (DatePeriodLicenseYearInfo info : infos) {
			ids.add(addDatePeriodLicenseYear(info));
		}

		return ids;
	}

	/**
	 * add date period license year record
	 * 
	 * @param info
	 * @return id OR error message
	 */
	public String addDatePeriodLicenseYear(DatePeriodLicenseYearInfo info) {
		LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();
		LicMgrAddDatePeriodLicenseYearWidget addWidget = LicMgrAddDatePeriodLicenseYearWidget
				.getInstance();

		logger.info("Add Date Period License Year - " + info.getLicenseYear());
		listPage.clickAddLicenseYear();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setupDatePeriodLicenseYearInfo(info);
		addWidget.clickOK();
		ajax.waitLoading();
		String toReturn = "";
		Object page = browser.waitExists(addWidget, listPage);
		if (page == addWidget) {
			toReturn = addWidget.getErrorMsg();
		}
		if (page == listPage) {
			listPage.waitLoading();
			listPage.searchLicenseYear(info.getLicenseYear());
			toReturn = listPage.getLicenseYearID(info.getLicenseYear());
		}
		return toReturn;
	}

	public void deactivateDatePeriodLicenseYear(String... years) {
		logger.info("Deactivate Date Period License Years: "
						+ years.toString());
		for (String year : years) {
			updateDatePeriodLicenseYearStatus(year, INACTIVE_STATUS);
		}
	}
	
	public void updateDatePeriodLicenseYearStatus(String licenseYear, String status){
		LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();
		LicMgrEditDatePeriodLicenseYearWidget detailsWidget = LicMgrEditDatePeriodLicenseYearWidget
				.getInstance();
		listPage.searchLicenseYear(status.equalsIgnoreCase(ACTIVE_STATUS)?INACTIVE_STATUS:ACTIVE_STATUS, licenseYear);
		String id = listPage.getLicenseYearID(licenseYear);
		if (!StringUtil.isEmpty(id)) {
			listPage.clickID(id);
			ajax.waitLoading();
			detailsWidget.waitLoading();
			detailsWidget.selectStatus(status);
			detailsWidget.clickOK();
			ajax.waitLoading();
			listPage.waitLoading();
		}
	}
	
	public void updateDatePeriodLicenseYearInfo(DatePeriodLicenseYearInfo licenseYear, String status, List<Dates> dates){
		LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage
				.getInstance();
		LicMgrEditDatePeriodLicenseYearWidget detailsWidget = LicMgrEditDatePeriodLicenseYearWidget
				.getInstance();
		LicMgrAddDatePeriodCategoryWidget cateWidget = LicMgrAddDatePeriodCategoryWidget
				.getInstance();
		listPage.searchLicenseYear(licenseYear.getStatus(), licenseYear.getLicenseYear());
		String id = "";
		if(StringUtil.notEmpty(licenseYear.getId())){
			id = licenseYear.getId();
		}else{
			id = listPage.getLicenseYearID(licenseYear.getLicenseYear());
		}
		listPage.clickID(id);
		ajax.waitLoading();
		detailsWidget.waitLoading();
		for(Dates date:dates){
			if(!detailsWidget.isCategoryExist(date.getCategory())){
				detailsWidget.clickAddNewCategory();
				ajax.waitLoading();
				cateWidget.waitLoading();
				cateWidget.setCategoryName(date.getCategory());
				cateWidget.clickOK();
				ajax.waitLoading();
				detailsWidget.waitLoading();
			}
		}
		detailsWidget.updateInfoAndClickOK(status, dates);
		ajax.waitLoading();
		listPage.waitLoading();
	}

	/**
	 * get the price appliable to all state if exist.
	 * 
	 * @param schema
	 * @param prdCode
	 * @return
	 */
	public String getCustPriceByPrdCodeAndAppToAllState(String schema,
			String prdCode, String purchaseType) {
		return this.getPriCustPrice(schema, prdCode, purchaseType, "1");
	}

	/**
	 * get the price appliable to specific state if exist.
	 * 
	 * @param schema
	 * @param prdCode
	 * @return
	 */
	public String getCustPriceByPrdCodeAndNotToAllState(String schema,
			String prdCode, String purchaseType) {
		return this.getPriCustPrice(schema, prdCode, purchaseType, "0");
	}

	/**
	 * Search bulletin by headline and go to bulletin detail page by ID.
	 * 
	 * @param headline
	 * @param bulletinID
	 */
	public void gotoBulletinDetailPageByID(String headline, String bulletinID) {
		OrmsBulletinSearchPage bulletinSchPg = OrmsBulletinSearchPage
				.getInstance();
		OrmsBulletinDetailPage bulletinDetailPg = OrmsBulletinDetailPage
				.getInstance();
		logger.info("Go to bulletin detail page from bulletin search page...");

		bulletinSchPg.searchByHeadLine(headline);
		bulletinSchPg.waitLoading();
		bulletinSchPg.clickBulletinId(bulletinID);
		bulletinDetailPg.waitLoading();
	}

	/**
	 * Go to note and alert details page from search page.
	 */
	public void gotoNoteAndAlertDetailsPage(String id) {
		LicMgrNoteAndAlertListPage searchPage = LicMgrNoteAndAlertListPage
				.getInstance();
		LicMgrNoteAlertDetailsWidget detailPage = LicMgrNoteAlertDetailsWidget
				.getInstance();

		logger.info("Go to note and alert details page from search page.");
		searchPage.clickID(id);
		ajax.waitLoading();
		detailPage.waitLoading();
	}

	/**
	 * Select customer for purchase privilege, the customer has an active alert,
	 * get alert text and return it.
	 * 
	 * @param cust
	 * @return
	 */
	public String selectCustForPurchasePri(Customer cust) {
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage
				.getInstance();
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage
				.getInstance();
		LicMgrAlertPopupWidget alertWidget = LicMgrAlertPopupWidget
				.getInstance();

		if (identifyCustPg.exists()) {
			identifyCustPg.identifyCustomer(cust);
			identifyCustPg.selectResidencyStatus(cust.residencyStatus);
			ajax.waitLoading();
			identifyCustPg.clickOK();
			ajax.waitLoading();
		}
		alertWidget.waitLoading();

		String alertInfo = alertWidget.getAlertInfo().trim();
		alertWidget.clickOK();
		ajax.waitLoading();
		confirmPg.waitLoading();

		return alertInfo;
	}

	/**
	 * active or deactive supplier product.
	 * 
	 * @param code
	 * @param status
	 */
	public void activeOrDeactiveSupplier(String code, String status) {
		LicMgrSuppliesListPage supplisetPage = LicMgrSuppliesListPage
				.getInstance();
		LicMgrCreateNewSupplyPage createNewPg = LicMgrCreateNewSupplyPage
				.getInstance();

		logger.info("active supplier by supplier code");
		supplisetPage.clickSupplyCode(code);
		ajax.waitLoading();
		createNewPg.waitLoading();
		createNewPg.selectStauts(status);
		createNewPg.clickOK();
		ajax.waitLoading();
		supplisetPage.waitLoading();
	}

	/**
	 * This method used to close ALL finance session which fit the current login
	 * user and it support either adjustment or non adjustment just controlled
	 * by parameters. The work flow starts from the license Manager home page
	 * and ends at the finance session page
	 * 
	 * @param pinNum
	 * @param location
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 *            it store all non cash payment amount(personal check,money
	 *            order and certified check)
	 * @param note
	 * @return the closed finance session ID
	 */
	public List<String> closeAllFinSession(String pinNum,
			String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		LicMgrTopMenuPage lmTopMenuPg = LicMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();
		OrmsEnterPinNumPopupPage pinPopupPg = OrmsEnterPinNumPopupPage
				.getInstance();

		logger.info("Closing Fin Session.");
		lmTopMenuPg.selectFinancialsOptions("Fin Sessions & Deposits");
		ormsFinSessPg.waitLoading();
		List<String> finSessIDList = ormsFinSessPg.getAllOpenFinSessID();
		Object page = null;
		if (null != finSessIDList && finSessIDList.size() > 0) {
			for (int i = 0; i < finSessIDList.size(); i++) {
				String finID = finSessIDList.get(i);
				this.closeFinSession(finID, cashTotalOnHand,
						nonCashTotalOnHand, note);
				page = browser.waitExists(pinPopupPg, ormsFinSessPg);
				if (page == pinPopupPg) {
					pinPopupPg.enterPIN(pinNum);
					pinPopupPg.clickOK();
					ajax.waitLoading();
					ormsFinSessPg.waitLoading();
				}
				logger.info("Close FinSession ID is " + finID + ".");
			}
		} else {
			logger.info("There isn't any open fin session.");
		}
		return finSessIDList;
	}
	
	/**
	 * check product exit or not from DB.
	 * 
	 * @param code
	 * @param schema
	 */
	public boolean checkPrdExistOrNotFromDB(String code, String schema) {
		logger.info("Check prd exist or not in db");

		String query = "select count(*) as COUNT from p_prd where prd_cd = '"
				+ code + "'";
		db.resetSchema(schema);
		int count = Integer.valueOf(db.executeQuery(query, "COUNT", 0));
		if (count <= 0) {
			return false;
		} else {
			return true;
		}

	}

	public void gotoVehicleDetailsPgFromVehicleOrdDetailsPgByMiNum(String miNum) {
		LicMgrVehicleOrderDetailsPage vehiOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage
				.getInstance();

		logger
				.info("Go to vehicle detail page from vehicle order details page by MiNum:"
						+ miNum);
		vehiOrderDetailsPage.clickVehicleMiNum(miNum);
		ajax.waitLoading();
		vehicleDetailsPg.waitLoading();
	}

	/**
	 * Go to EFT Reporting page from home page. Start: home page. End: EFT
	 * Reporting page.
	 * 
	 * @param vendorNum
	 * @return
	 */
	public void gotoEFTReportingPage(String vendorNum) {
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage
				.getInstance();
		LicMgrViewEFTReportingPage eftReportingPg = LicMgrViewEFTReportingPage
				.getInstance();

		this.gotoVendorDetailsPgFromTopMenu(vendorNum);
		logger.info("Go to EFT Reporting page from vendor detail page.");
		vendorDetailPg.clickEFTReporting();
		eftReportingPg.waitLoading();
	}

	/**
	 * Go to EFT Reporting details page from home page.
	 * 
	 * @param vendorNum
	 * @param transmissionId
	 */
	public void gotoEFTReportingDetailPage(String vendorNum,
			String transmissionId) {
		this.gotoEFTReportingPage(vendorNum);
		LicMgrViewEFTReportingPage eftReportingPg = LicMgrViewEFTReportingPage
				.getInstance();
		LicMgrEFTReportingDetailPage eftReportingDetailPg = LicMgrEFTReportingDetailPage
				.getInstance();

		logger
				.info("Go to EFT Reporting details page from reporting list page.");
		eftReportingPg.clickTransmissionID(transmissionId);
		ajax.waitLoading();
		eftReportingDetailPg.waitLoading();
	}

	/**
	 * Go to officer search page from top menu
	 */
	public void gotoOfficerSearchPgFromTopMenu() {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrOfficersSearchPage officerSearchPg = LicMgrOfficersSearchPage
				.getInstance();
		String adminValue = topMenuPg.getAdminValue();

		logger.info("Go to officer search page.");
		if (!adminValue.equals("Officer MGT")) {
			topMenuPg.selectAdminOptions("Officer MGT");
		} else {
			topMenuPg.clickAdmin();
		}
		officerSearchPg.waitLoading();
	}

	/**
	 * Go to Badge search page, start from officer search page, end at hunts
	 * list page
	 */
	public void gotoBadgeSearchPgFromOfficerSearchPg() {
		LicMgrOfficersSearchPage officerSearchPg = LicMgrOfficersSearchPage
				.getInstance();
		LicMgrBadgeSearchPage badgeSearchPg = LicMgrBadgeSearchPage
				.getInstance();

		logger.info("Go to badge search page from officer search page.");
		officerSearchPg.clickBadgesTab();
		ajax.waitLoading();
		badgeSearchPg.waitLoading();
	}

	/**
	 * This method is used to add a new badge, it starts from badge search page
	 * and end at badge search page or add badge widget for error
	 * 
	 * @param badgeList
	 */
	public void addBadgeFromBadgeSearchPage(List<BadgeInfo> badgeList) {
		LicMgrBadgeSearchPage badgeSearchPg = LicMgrBadgeSearchPage
				.getInstance();
		LicMgrAddBadgesWidget addBadgeWidget = LicMgrAddBadgesWidget
				.getInstance();
		logger.info("Go to add a new badge.");
		badgeSearchPg.clickAddBadges();
		addBadgeWidget.waitLoading();
		addBadgeWidget.setBadgeInfo(badgeList);
		addBadgeWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(addBadgeWidget, badgeSearchPg);
	}

	/**
	 * This method is used to add an officer, it starts from officer search page
	 * and end at officer search page or add officer page for error
	 * 
	 * @param officer
	 */
	public void addOfficerFromOfficerSearchPage(OfficerInfo officer) {
		LicMgrOfficersSearchPage officerSearchPg = LicMgrOfficersSearchPage
				.getInstance();
		LicMgrOfficersAddPage addOfficerPg = LicMgrOfficersAddPage
				.getInstance();
		logger.info("Go to add a new officer.");
		officerSearchPg.clickAddOfficer();
		addOfficerPg.waitLoading();
		addOfficerPg.setOfficerInfo(officer);
		addOfficerPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(addOfficerPg, officerSearchPg);
	}

	/**
	 * This method is used to go to the detail page of an officer by search in
	 * it in officer search page
	 * 
	 * @param officer
	 */
	public String searchOfficerAndGoToDetailPage(OfficerInfo officer) {
		LicMgrOfficersSearchPage officerSearchPg = LicMgrOfficersSearchPage
				.getInstance();
		LicMgrOfficerDetailPage officerDetailPg = LicMgrOfficerDetailPage
				.getInstance();
		officerSearchPg.setSearchOfficerInfo(officer);
		officerSearchPg.clickSearch();
		ajax.waitLoading();
		officerSearchPg.waitLoading();
		List<String> searchResult = officerSearchPg.getFirstSearchResultInfo();
		String officerId = searchResult.get(0);
		officerSearchPg.clickOfficerId(officerId);
		logger.info("Go to the detail page of the first officer after search.");
		officerDetailPg.waitLoading();
		return officerId;
	}

	/**
	 * This method assign a badge to an officer, it starts at officer detail
	 * page
	 * 
	 * @param badge
	 */
	public void assignBadgeToOfficerInOfficerDetailPage(BadgeInfo badge) {
		LicMgrOfficerBadgesAssignPage badgeAssPg = LicMgrOfficerBadgesAssignPage
				.getInstance();
		LicMgrOfficerDetailPage officerDetailPg = LicMgrOfficerDetailPage
				.getInstance();
		LicMgrAddOrChangeBadgeAssignmentWidget assignWidge = LicMgrAddOrChangeBadgeAssignmentWidget
				.getInstance();
		if (!badgeAssPg.exists()) {
			logger.info("Go to badge assign page under officer detail page");
			officerDetailPg.clickBadgesTab();
			ajax.waitLoading();
			badgeAssPg.waitLoading();
		}
		logger.info("Begin to assign a badge to officer");
		badgeAssPg.clickAddOrChangeBadgeAssignment();
		ajax.waitLoading();
		assignWidge.waitLoading();
		assignWidge.setBadgeInfo(badge);
		assignWidge.clickOK();
		ajax.waitLoading();
		browser.waitExists(assignWidge, badgeAssPg);
	}

	public void cancelFromProductPricingWidget(
			ILicMgrProductPricingPage pricingPage) {
		LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget
				.getInstance();

		logger
				.info("Cancel from Product Pricing Add Widget to pricing list page.");
		addWidget.clickCancel();
		ajax.waitLoading();
		pricingPage.waitLoading();
	}

	/**
	 * safe add privilege quantity control.
	 * 
	 * @param quantityControl
	 * @return
	 */
	public List<String> safeAddPrivilegeQuantityControl(
			QuantityControlInfo... quantityControl) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();
		quantityControlPg.search(false, "Active", "");
		List<String> lsit = quantityControlPg.getQuantityControlIDs();
		if (lsit.size() > 0) {
			for (int i = 0; i < lsit.size(); i++)
				this.deactivatePrivilegeQuantityControl(lsit.get(i));
		}
		return this.addPrivilegeQuantityControl(quantityControl);
	}

	/**
	 * This method executes the work flow of going to fee detail page from
	 * consumable order detail page
	 */
	public void gotoFeeDetailPageFromConsumOrderDetailPg() {
		LicMgrConsumableOrderDetailsPage consumOrderDetailsPage = LicMgrConsumableOrderDetailsPage
				.getInstance();
		OrmsFeeDetailsPage fmFeeDetailPg = OrmsFeeDetailsPage.getInstance();
		logger
				.info("Go to fee detail page from consumable orders detail page.");
		consumOrderDetailsPage.clickFees();
		fmFeeDetailPg.waitLoading();
	}

	public void gotoConsumableAgentAssignPgFromTopMenu(String consumableID) {
		this.gotoConsumableSearchListPageFromTopMenu();
		this.gotoConsumableProductDetailsPageFromListPage(consumableID);
		this.gotoConsumableAgentAssignPg();
	}

	public void gotoConsumableAgentAssignPg() {
		LicMgrConsumableProductDetailsPage consumableDetailsPg = LicMgrConsumableProductDetailsPage
				.getInstance();
		LicMgrConsumableProductStoreAssignmentPage consumableAssignPg = LicMgrConsumableProductStoreAssignmentPage
				.getInstance();

		logger
				.info("Go to consumable Agent Assignments page from detail page.");
		consumableDetailsPg.clickAgentAssignmentTab();
		ajax.waitLoading();
		consumableAssignPg.waitLoading();
	}
	
	/**
	 * Go to receipt search page from top menu
	 */
	public void gotoReceiptsSearchFromTop(){
		LicMgrPrivilegeOrderSearchPage privOrderSearchPage = LicMgrPrivilegeOrderSearchPage
				.getInstance();
		OrmsReceiptSearchPage receiptSearchPg = OrmsReceiptSearchPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		homePg.clickOrders();
		ajax.waitLoading();
		privOrderSearchPage.waitLoading();
		privOrderSearchPage.clickReceiptsTab();
		ajax.waitLoading();
		receiptSearchPg.waitLoading();
	}

	/** Reverse/Void Privilege Order from Home page and process Order Cart */
	void cleanUpPrivilegeOrder(String ordNum, String action, String reason, String note, Payment pay) {
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Reverse Privilege Order from Home page to Order Cart page...");
		this.gotoPrivilegeOrderDetailPage(ordNum);
		this.operatePrivilegeOrdToCart(action, reason, note);
		if (orderCartPg.exists()) {
			this.processOrderCart(pay);
		} else {
			this.gotoHomePage();
		}
	}
	
	public void reversePrivilegeOrderToCleanUp(String ordNum, String reason, String note, Payment pay) {
		this.cleanUpPrivilegeOrder(ordNum, "Reverse", reason, note, pay);
	}
	
	public void voidPrivilegeOrderToCleanUp(String ordNum, String reason, String note, Payment pay) {
		this.cleanUpPrivilegeOrder(ordNum, "Void", reason, note, pay);
	}

	/** Add Customer Suspension from top menu to customer suspension page */
	public void addCustSuspensionFromTopMenu(Customer cust, Suspension suspension, PrivilegeInfo pri) {
		this.gotoCustomerDetailFromTopMenu(cust);
		this.gotoSuspensionsFromCustomerDetailsPg();
		this.addCustomerSuspension(suspension, pri);
	}
	/**
	 * Go to Import File page for specific file type
	 * @param fileType
	 */
	public void gotoImportFilePage(String fileType) {
		LicMgrFileImportsListCommonPage fileImportsListPg = LicMgrFileImportsListCommonPage.getInstance();
		LicMgrUnlockedPriImportsPage ulPriImportPg = LicMgrUnlockedPriImportsPage.getInstance();
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrPointImportsPage pointImportPg = LicMgrPointImportsPage.getInstance();
		
		if (!fileImportsListPg.exists()) {
			topMenu.selectAdminOptions("File Imports");
			ajax.waitLoading();
			fileImportsListPg.waitLoading();
		}

		if (fileType.equalsIgnoreCase("Unlocked Privilege")) {
			fileImportsListPg.clickUnlockedPriImportsTab();
			ulPriImportPg.waitLoading();
		} else if (fileType.equalsIgnoreCase("Point")) {
			fileImportsListPg.clickPointImportsTab();
			pointImportPg.waitLoading();
		} else {
			throw new ItemNotFoundException("No such an import file type - " + fileType);
		}
	}
	
	/**
	 * Import file with specific file type
	 * @param fileType
	 * @param filePath
	 * @return
	 */
	public String importFile(String fileType, String filePath) {
		LicMgrFileImportsListCommonPage fileImportsListPg = LicMgrFileImportsListCommonPage.getInstance();
		LicMgrImportFilePage importFilePg = LicMgrImportFilePage.getInstance();
		LicMgrImportConfirmWidget importConfirmWidget = LicMgrImportConfirmWidget.getInstance();
		LicMgrImportFileInfoWidget importInfoWidget = LicMgrImportFileInfoWidget.getInstance();
		LicMgrUnlockedPriImportsPage ulPriImportPg = LicMgrUnlockedPriImportsPage.getInstance();
		LicMgrPointImportsPage pointImportPg = LicMgrPointImportsPage.getInstance();
		
		logger.info("Import file for " + fileType + " with the file path=" + filePath);
		this.gotoImportFilePage(fileType);
		fileImportsListPg.clickImportFileBtn();
		ajax.waitLoading();
		importFilePg.waitLoading();
		importFilePg.setFileName(filePath);
		importFilePg.clickOK();
		ajax.waitLoading();
		importConfirmWidget.waitLoading();
		importConfirmWidget.clickOKAndWait();
		importInfoWidget.waitLoading();
		importInfoWidget.clickOKAndWait();
		fileImportsListPg.waitLoading();
		
		String importID = "";
		if (fileType.equalsIgnoreCase("Unlocked Privilege")) {
			importID = ulPriImportPg.getFirstUnlockedPriImportID();
		} else if (fileType.equalsIgnoreCase("Point")) {
			importID = pointImportPg.getFirstPointImportID();
		} else {
			throw new ItemNotFoundException("No such an import file type - " + fileType);
		}
		logger.info("Import ID=" + importID);
		return importID;
	}
	
	/** Import Unlocked Privileges File */
	public String importUnlockedPriFile(String filePath) {
		return this.importFile("Unlocked Privilege", filePath);
	}
	
	/** Import Point File */
	public String importPointFile(String filePath) {
		return this.importFile("Point", filePath);
	}
	
	/**
	 * Deactivate all unlocked privileges for a customer, starts from top menu, ends at unlocked privilege page
	 * @param cust
	 */
	public void deactivateAllUnlockedPrivilege(Customer cust) {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerUnlockedPrivilegesPage ulPrivsPg = LicMgrCustomerUnlockedPrivilegesPage.getInstance();
		
		logger.info("deactivate all unlocked privilege....");
		this.gotoCustomerDetailFromTopMenu(cust);
		detailsPg.clickUnlockedPrivilegesTab();
		ulPrivsPg.waitLoading();
		ulPrivsPg.deactivateAllUnlockedPriv();
	}
	
	/** Go to Document Fulfillment page */ 
	public void gotoDocumentFulfillmentPg() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrDocFulfillmentPage docFulfillPg = LicMgrDocFulfillmentPage.getInstance();
		
		if (!docFulfillPg.exists()) {
			topMenu.selectAdminOptions("Document Fulfillment");
			ajax.waitLoading();
			docFulfillPg.waitLoading();
		}
	}
	
	/** Print document fulfillment */
	public String printDocumentFulfillment(DocumentFulfillmentInfo docFulfillInfo, String path) {
		LicMgrDocFulfillmentPage docFulfillPg = LicMgrDocFulfillmentPage.getInstance();
		LicMgrDocFulfillInventoryDetailsWidget invDetailsWidget = LicMgrDocFulfillInventoryDetailsWidget.getInstance();
		LicMgrDocFulfillPrintWidget printPg = LicMgrDocFulfillPrintWidget.getInstance();
		LicMgrDocFulfillRetryPrintWidget retryPrintPg = LicMgrDocFulfillRetryPrintWidget.getInstance();
		FileDownloadDialogPage fileDownloadPg = FileDownloadDialogPage.getInstance();
		
		logger.info("Print document fulfillment: " + docFulfillInfo.getReceiptNum());
		docFulfillPg.searchDocFulfillment(docFulfillInfo);
		docFulfillPg.unSelectAll();
		ajax.waitLoading();
		docFulfillPg.selectDocFulfillment(docFulfillInfo.getReceiptNum());
		ajax.waitLoading();
		docFulfillPg.clickPrintDocuments();
		ajax.waitLoading();
		
		invDetailsWidget.waitLoading();	
		invDetailsWidget.setInventoryDetails(docFulfillInfo);
		invDetailsWidget.clickOKAndWait();
		
		printPg.waitLoading();
		printPg.selectDummyPrinter();
		printPg.clickPrint();
		ajax.waitLoading();
		
		fileDownloadPg.setDismissible(false);
		fileDownloadPg.setBeforePageLoading(false);
		fileDownloadPg.waitLoading();
		
		File file = new File(path);
		if (!file.exists()) {
			boolean exists = file.mkdir();
			if (!exists) {
				throw new RuntimeException("Failed to create directory - " + path);
			}
		}

		// download file
		String fullFileName = file.getAbsolutePath() + "\\"
				+ "PrintReceiptDoc_" + docFulfillInfo.getReceiptNum() + ".pdf.do";
		this.downloadFile(fullFileName);
		
		retryPrintPg.waitLoading();
		retryPrintPg.clickSuccess();
		ajax.waitLoading();
		docFulfillPg.waitLoading();
		
		return fullFileName;
	}
	
	public void noChargeOrder(String ordNum) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("No Charge order - " + ordNum);
		cartPage.selectOrderCheckBox(ordNum, true);
		cartPage.clickNoCharge();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		cartPage.waitLoading();
		
	}
	
	public void verifyHuntInventoryAvailableQuantity(String schema, String huntLicenseYearQuotaID, int expectedAvailableQuantity) {
		int quantities[] = getHuntInventoryQuantities(schema, huntLicenseYearQuotaID);
		if(!MiscFunctions.compareResult("Hunt Available Quantity", expectedAvailableQuantity, quantities[1])) throw new ErrorOnDataException("Hunt Available Quantity is not correct.");
	}
	
	public void verifyHuntInventoryStatus(String schema, String huntLicenseYearQuotaID, String status) {
		String actualStatusID = this.getHuntInventoryStatus(schema, huntLicenseYearQuotaID);
		String expectedStatusID = "";
		if(status.equals(INV_USED_STATUS_HOLD)) {
			expectedStatusID = "1001";
		} else if(status.equals(INV_USED_STATUS_BOOKED)) {
			expectedStatusID = "1000";
		}
		
		if(!MiscFunctions.compareResult("Hunt Inventory Status", expectedStatusID, actualStatusID)) throw new ErrorOnDataException("Hunt Inventory Status", expectedStatusID, actualStatusID);
	}
	
	public void verifyHuntInventoryHoldRecordDeleted(String schema, String huntLicenseYearQuotaID) {
		logger.info("Verify if the HOLD hunt inventory record deleted from table i_hunt_inv_used.");
		if(!this.isHuntInventoryRecordExists(schema, huntLicenseYearQuotaID, "1001")) throw new ErrorOnDataException("Hold hunt inventory shall be deleted from table i_hunt_inv_used, but actually not.");
	}
	
	/** Go to Allocation Type List page */
	public void gotoAllocationTypeListPg() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrAllocationTypeListPage allocationTypePg = LicMgrAllocationTypeListPage.getInstance();
		LicMgrOutfitterAllocationsListPage outfitterAllocationsPg = LicMgrOutfitterAllocationsListPage.getInstance();
		
		logger.info("Go to Allocation Type list page...");
		//updated by Summer[2014/07/26],reason:In MS Contract, 'Privilege Allocation' is 'License Allocation' in SK contract and AB contract.
		try{
			topMenu.selectAdminOptions("Licence Allocations");
		} catch(ActionFailedException e){
			topMenu.selectAdminOptions("Privilege Allocations");
		}
		browser.waitExists(allocationTypePg, outfitterAllocationsPg);
	}
	
	/** Add Allocation Type from allocation type list page */
	public void addAllocationType(Data<AllocationType> type) {
		LicMgrAllocationTypeListPage allocationTypePg = LicMgrAllocationTypeListPage.getInstance();
		LicMgrAddAllocationTypeWidget addAllocationTypesWidget = LicMgrAddAllocationTypeWidget.getInstance();
		
		logger.info("Add allocation type with the name=" + AllocationType.AllocationType.getStrValue(type));
		if (!allocationTypePg.exists()) {
			this.gotoAllocationTypeListPg();
		}
		allocationTypePg.clickAddAllocationTypes();
		ajax.waitLoading();
		addAllocationTypesWidget.waitLoading();
		addAllocationTypesWidget.setAllocationTypeInfo(type);
		addAllocationTypesWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(addAllocationTypesWidget, allocationTypePg);
	}
	
	/** Go to Allocation Type License Year page*/
	public void gotoAllocationTypeLicYearPg() {
		LicMgrPrivAllocationsCommonPage allocationTypePg = LicMgrPrivAllocationsCommonPage.getInstance();
		LicMgrAllocationTypeLicYearPage licYearPg = LicMgrAllocationTypeLicYearPage.getInstance();
		
		allocationTypePg.clickAllocationTypeLicenseYearsTab();
		ajax.waitLoading();
		licYearPg.waitLoading();
	}
	
	public void addAllocationTypeLicYear(Data<AllocationTypeLicenseYear> allocTypeLicYear) {
		LicMgrAllocationTypeLicYearPage licYearPg = LicMgrAllocationTypeLicYearPage.getInstance();
		LicMgrAddAllocationTypeLicYearWidget addYearPg = LicMgrAddAllocationTypeLicYearWidget.getInstance();
		
		logger.info("Add Allocation Type License Year...");
		licYearPg.clickAddAllocTypeLicYear();
		ajax.waitLoading();
		addYearPg.waitLoading();
		addYearPg.setAllocationTypeLicYearInfo(allocTypeLicYear);
		addYearPg.clickOKAndWait();
		browser.waitExists(licYearPg, addYearPg);
	}
	
	public void gotoOutfitterAllocationsListPg() {
		LicMgrPrivAllocationsCommonPage allocationsCommonPg = LicMgrPrivAllocationsCommonPage.getInstance();
		LicMgrOutfitterAllocationsListPage outfitterAllocationsPg = LicMgrOutfitterAllocationsListPage.getInstance();
		
		logger.info("Go to outfitter allocations list page...");
		if (!allocationsCommonPg.exists()) {
			this.gotoAllocationTypeListPg();
		}
		if (!outfitterAllocationsPg.exists()) {
			allocationsCommonPg.clickOutfitterAllocationsTab();
			outfitterAllocationsPg.waitLoading();
		}
	}
	
	public void gotoOutfitterAllocationsOrderSearchPg() {
		LicMgrPrivAllocationsCommonPage allocationsCommonPg = LicMgrPrivAllocationsCommonPage.getInstance();
		LicMgrAllocationsOrdersSearchPage alloOrdSearchPg = LicMgrAllocationsOrdersSearchPage.getInstance();
		
		logger.info("Go to outfitter allocations order search page...");
		if (!allocationsCommonPg.exists()) {
			this.gotoAllocationTypeListPg();
		}
		if (!alloOrdSearchPg.exists()) {
			allocationsCommonPg.clickAllocationsOrdersTab();
			alloOrdSearchPg.waitLoading();
		}
	}
	
	public void addOutfitterAllocations(Data<OutfitterAllocation> outfitterAlloc) {
		LicMgrOutfitterAllocationsListPage outfitterAllocationsPg = LicMgrOutfitterAllocationsListPage.getInstance();
		LicMgrAddOutfitterAllocationsWidget addWidget = LicMgrAddOutfitterAllocationsWidget.getInstance();
		
		logger.info("Add Outfitter Allocations for type=" + OutfitterAllocation.AllocationType.getStrValue(outfitterAlloc) + 
				", license year=" + OutfitterAllocation.LicenseYear.getStrValue(outfitterAlloc));
		if (!outfitterAllocationsPg.exists()) {
			this.gotoOutfitterAllocationsListPg();
		}
		outfitterAllocationsPg.clickAddOutfitterAllocations();
		ajax.waitLoading();
		addWidget.waitLoading();
		addWidget.setOutfitterAllocations(outfitterAlloc);
		addWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(addWidget, outfitterAllocationsPg);
	}

	public String addLotteryLicenseYear(LicenseYear ly) {
		LicMgrLotteryLicenseYearsPage licenseYearPg = LicMgrLotteryLicenseYearsPage.getInstance();
		LicMgrLotteryLicenseYearDetailsWidget addLicenseYearWidget = LicMgrLotteryLicenseYearDetailsWidget.getInstance();
		
		logger.info("Add privilege lottery license year.");
		if (!licenseYearPg.exists()) {
			licenseYearPg.clickLicenseYearsTab();
			ajax.waitLoading();
			licenseYearPg.waitLoading();
		}

		String licYearId = licenseYearPg.getLicenseYearId(ly.status, ly.locationClass,ly.licYear);
		if (StringUtil.isEmpty(licYearId)) {
			licenseYearPg.clickAddLicenseYear();
		} else {
			licenseYearPg.clickLicenseYearID(licYearId);
		}
		addLicenseYearWidget.waitLoading();
		addLicenseYearWidget.setupLicenseYear(ly);
		addLicenseYearWidget.clickOK();
		ajax.waitLoading();
		licenseYearPg.waitLoading();

		return licenseYearPg.getLicenseYearId(ly.status, ly.locationClass, ly.licYear);
	}
	
	public String updatePrivilegeLotteryLicenseYear(LicenseYear ly) {
		LicMgrLotteryLicenseYearsPage licenseYearPage = LicMgrLotteryLicenseYearsPage.getInstance();
		LicMgrLotteryLicenseYearDetailsWidget detailsWidget = LicMgrLotteryLicenseYearDetailsWidget.getInstance();
		
		logger.info("update License Year(#ID=" + ly.id + ").");
		if(!licenseYearPage.exists()) {
			licenseYearPage.clickLicenseYearsTab();
			ajax.waitLoading();
			licenseYearPage.waitLoading();
		}
		licenseYearPage.clickLicenseYearID(ly.id);
		ajax.waitLoading();
		detailsWidget.waitLoading();
		detailsWidget.setupLicenseYear(ly);
		detailsWidget.clickOK();
		ajax.waitLoading();
		licenseYearPage.waitLoading();
		return licenseYearPage.getLicenseYearId(ly.status, ly.locationClass, ly.licYear);
	}
	
	public void gotoLotteryExecutionConfigPage() {
		LicMgrLotteriesCommonPage commonPage = LicMgrLotteriesCommonPage.getInstance();
		LicMgrLotteryExecutionConfigListPage exeConfigListPage = LicMgrLotteryExecutionConfigListPage.getInstance();
		
		logger.info("Goto lottery Execution Config list page.");
		commonPage.clickExecutionConfigTab();
		ajax.waitLoading();
		exeConfigListPage.waitLoading();
	}
	
	public String addLotteryExecutionConfig(LotteryExecutionConfigInfo config) {
		LicMgrLotteryExecutionConfigListPage listPage = LicMgrLotteryExecutionConfigListPage.getInstance();
		LicMgrLotteryExecutionConfigSelectAlgorithmWidget algorithmWidget = LicMgrLotteryExecutionConfigSelectAlgorithmWidget.getInstance();
		LicMgrLotteryExecutionConfigDetailsPage detailsPage = LicMgrLotteryExecutionConfigDetailsPage.getInstance();
		
		logger.info("Add lottery Execution Config.");
		listPage.clickAddLotteryExecutionConfig();
		ajax.waitLoading();
		algorithmWidget.waitLoading();
		algorithmWidget.selectAlgorithm(config.getAlgorithm());
		algorithmWidget.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.setupExecutionConfig(config);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		String toReturn = "";
		if(detailsPage.isErrorMessageExists()) {
			toReturn = detailsPage.getErrorMessage();
		} else {
			toReturn = detailsPage.getID();
		}
		detailsPage.clickOK();
		
		return toReturn;
	}
	
	public void gotoLotterySchedulePage() {
		LicMgrLotteriesCommonPage commonPage = LicMgrLotteriesCommonPage.getInstance();
		LicMgrProcessingListPage processingListPage = LicMgrProcessingListPage.getInstance();
		
		logger.info("Goto lottery schedule list page.");
		commonPage.clickProcessingTab();
		ajax.waitLoading();
		processingListPage.waitLoading();
	}
	
	public void gotoLotteryScheduleDetailspage(String description) {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		
		logger.info("Goto lottery schedule(Desc=" + description + ") details page.");
		listPage.clickLotterySchedule(description);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoLotteryScheduleHistoryPg(){
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		LicMgrProcessingHistoryPage historyPage = LicMgrProcessingHistoryPage.getInstance();
		logger.info("Go to change history page from privilege lottery detail page.");
		detailsPage.clickChangeHistory();
		ajax.waitLoading();
		historyPage.waitLoading();
	}
	
	public void gotoPrivilegeLotteryDetailPgFromHistoryPage(){
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		LicMgrProcessingHistoryPage historyPage = LicMgrProcessingHistoryPage.getInstance();
		logger.info("Go to privilege lottery detail page from change history list page.");
		historyPage.clickReturnToLotteryScheduleDetails();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void refreshProcessingResults(){
		LicMgrProcessingResultsPage resultsPage = LicMgrProcessingResultsPage.getInstance();
		LicMgrProcessingConfigurationPage configurationPg = LicMgrProcessingConfigurationPage.getInstance();
		LicMgrProcessingExceptionsPage exceptionsPg = LicMgrProcessingExceptionsPage.getInstance();
		
		resultsPage.clickConfigurationTab();
		ajax.waitLoading();
		configurationPg.waitLoading();
		
		configurationPg.clickExceptionsTab();
		ajax.waitLoading();
		exceptionsPg.waitLoading();
		
		exceptionsPg.clickResultsTab();
		ajax.waitLoading();
		resultsPage.waitLoading();
	}
	
	public void processLotterySchedule() {
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		LicMgrProcessingResultsPage resultsPage = LicMgrProcessingResultsPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Process lottery in lottery schedule details - results page.");
		detailsPage.clickResultsTab();
		ajax.waitLoading();
		resultsPage.waitLoading();
		
		resultsPage.clickProcessLottery();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		
		confirmWidget.clickOK();
		ajax.waitLoading();
		resultsPage.waitLoading();
	}
	
	public void processLotterySchedule(String licenseYear, String description) {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		
		listPage.searchLotterySchedule(OrmsConstants.ACTIVE_STATUS, licenseYear);
		this.gotoLotteryScheduleDetailspage(description);
		this.processLotterySchedule();
	}
	
	public String addLotteryScheduleAndClickOK(PrivilegeLotteryScheduleInfo schedule){
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		String id = this.addLotterySchedule(schedule);
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
		return id;
	}
	
	public String addLotterySchedule(PrivilegeLotteryScheduleInfo schedule) {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		LicMgrLotteryExecutionConfigSelectWidget selectWidget = LicMgrLotteryExecutionConfigSelectWidget.getInstance();
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		
		logger.info("Add Privilege Lottery Schedule.");
		listPage.clickAddLotterySchedule();
		ajax.waitLoading();
		selectWidget.waitLoading();
		selectWidget.selectLotteryExecutionConfig(schedule.getExecutionConfig());
		selectWidget.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.setupLotterySchedule(schedule);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		schedule.setId(detailsPage.getID());
		
		return schedule.getId();
	}
	
	public void deactivateLotterySchedule(String licenseYear, String desc) {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		logger.info("Deactivate lottery schedule(Description=" + desc + ")");
		listPage.searchLotterySchedule(ACTIVE_STATUS, licenseYear);
		String id = "";
		if(listPage.isLotteryScheduleExists(desc)) {
			id = listPage.getLotteryScheduleID(desc);
			deactiveLotteryScheduleById(id);
		}
	}
	
	public void deactiveLotteryScheduleById(String id){
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
		
		logger.info("Deactivate lottery schedule(Lottery Schedule Id=" + id + ")");
		listPage.clickLotteryScheduleID(id);
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.selectStatus(INACTIVE_STATUS);
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	public void deactivateLotteryScheduleByDesc(String desc) {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		logger.info("Deactivate lottery schedule(Description=" + desc + ")");
		listPage.searchLotteryScheduleByStatus(ACTIVE_STATUS);
		if(listPage.isLotteryScheduleExists(desc)) {
			String id = listPage.getLotteryScheduleID(desc);
			deactiveLotteryScheduleById(id);
		}
	}
	
	public void deactiveLotteryScheduleByLicenseYearAndLotteryPrd(String prd, String licenseYear){
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		logger.info("Deactivate lottery schedule(lotteryProduct=" + prd + ",licenseYear=" +licenseYear+")");
		listPage.searchActiveLotteryScheduleByLotteryPrdAndLicenseYear(prd, licenseYear);
		String id = listPage.getFirstLotteryScheduleIDInList();
		if(StringUtil.notEmpty(id)){
			deactiveLotteryScheduleById(id);
		}
	}
	
	/**
	 * Add by Sara, after select "Activity MGT" option from top menu DDL to Activity page
	 */
	public void gotoActivityPgFromHomePg() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		LicMgrActivityListPage activityPg = LicMgrActivityListPage.getInstance();
		logger.info("Go to Activity page from top menu DDL");
		topMenu.selectAdminOptions("Activity MGT");
		ajax.waitLoading();
		activityPg.waitLoading();
	}
	
	public void gotoFacilityListPgFromActivityPg(){
		LicMgrActivityListPage activityPg = LicMgrActivityListPage.getInstance();
		LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
		logger.info("Go to Facility list page from Activity page");
		activityPg.clickFacilityTab();
		ajax.waitLoading();
		faclityListPg.waitLoading();
	}
	
	public void gotoInstructorListPgFromActivityPg(){
		LicMgrActivityListPage activityPg = LicMgrActivityListPage.getInstance();
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		logger.info("Go to Instructor list page from Activity page");
		activityPg.clickInstructorTab();
		ajax.waitLoading();
		instructorListPg.waitLoading();
	}
	
	public void gotoBusinessRuleListPgFromActivityPg(){
		LicMgrActivityListPage activityPg = LicMgrActivityListPage.getInstance();
		LicMgrBusinessRuleListPage businessRuleListPg = LicMgrBusinessRuleListPage.getInstance();
		logger.info("Go to Business list page from Activity page");
		activityPg.clickBusinessRuleTab();
		ajax.waitLoading();
		businessRuleListPg.waitLoading();
	}
	
	public String addNewBusinessRuleForActivity(RuleDataInfo rule){
		LicMgrBusinessRuleListPage businessRuleListPg = LicMgrBusinessRuleListPage.getInstance();
		LicMgrAddNewBusinessRuleWidget addBusinessWidget = LicMgrAddNewBusinessRuleWidget.getInstance();
		logger.info("Start to add a new rule for activity!");
		businessRuleListPg.clickAddBusinessRule();
		ajax.waitLoading();
		addBusinessWidget.waitLoading();
		addBusinessWidget.setUpBusinessRuleAndClickOk(rule);
		ajax.waitLoading();
		Object obj = browser.waitExists(businessRuleListPg, addBusinessWidget);
		String errMsg = "";
		if(obj == addBusinessWidget){
			errMsg = addBusinessWidget.getErrorMsg();
			addBusinessWidget.clickCancel();
			ajax.waitLoading();
		}
		businessRuleListPg.waitLoading();
		return errMsg;
	}
	
	public void gotoFacilityListPgFromHomePg(){
		gotoActivityPgFromHomePg();
		gotoFacilityListPgFromActivityPg();
	}
	
	public void gotoNewFacilitySetupPgFromHomePg(String agency, String region){
		gotoFacilityListPgFromHomePg();
		gotoNewFacilitySetupPgFromFacilityListPg(agency, region);
	}
	
	public void gotoFacilityDetailsPgFromHomePg(Data<SearchFacilityAttr> src){
		gotoFacilityListPgFromHomePg();
		gotoFacilityDetailsPgFromFacilityListPgWithSearch(src);
	}
	
	public void gotoFacilityPrdListFromHome(Data<SearchFacilityAttr> src){
		gotoFacilityDetailsPgFromHomePg(src);
		gotoFacilityPrdPgFromFacilityDetailsPg();
	}
	
	public void gotoNewFacilitySetupPgFromFacilityListPg(String agency, String region){
		LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
		LicMgrAddNewFacilityWidget addNewFacilityWidget = LicMgrAddNewFacilityWidget.getInstance();
		LicMgrNewFacilitySetupPage newFacilitySetupPg = LicMgrNewFacilitySetupPage.getInstance();
		
		faclityListPg.clickAddFacilityButton();
		ajax.waitLoading();
		addNewFacilityWidget.waitLoading();
		addNewFacilityWidget.setupAgencyAndRegionInfo(agency, region);
		addNewFacilityWidget.clickOK();
		ajax.waitLoading();
		newFacilitySetupPg.waitLoading();
	}
	
	public String addFacility(FacilityData fd, boolean clickCancelInAddNewFacilityWidget, boolean clickCancelInNewFacilityPg){
		LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
		LicMgrAddNewFacilityWidget addNewFacilityWidget = LicMgrAddNewFacilityWidget.getInstance();
		LicMgrNewFacilitySetupPage newFacilitySetupPg = LicMgrNewFacilitySetupPage.getInstance();
		LicMgrFacilityDetailsPage facilityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
		
		logger.info("Add facility from facility list page");
		faclityListPg.clickAddFacilityButton();
		ajax.waitLoading();
		addNewFacilityWidget.waitLoading();
		addNewFacilityWidget.setupAgencyAndRegionInfo(fd.agency, fd.region);
		if(clickCancelInAddNewFacilityWidget){
			addNewFacilityWidget.clickCancel();
			ajax.waitLoading();
			faclityListPg.waitLoading();
		}else {
			addNewFacilityWidget.clickOK();
			ajax.waitLoading();
			newFacilitySetupPg.waitLoading();
			newFacilitySetupPg.setupFacilityDetailsData(fd);
			if(clickCancelInNewFacilityPg){
				newFacilitySetupPg.clickCancel();
				faclityListPg.waitLoading();
			}else {
				newFacilitySetupPg.clickApply();
				ajax.waitLoading();
				facilityDetailsPg.waitLoading();
				fd.facilityID = facilityDetailsPg.getFacilityID();
				facilityDetailsPg.clickOK();
				ajax.waitLoading();
				faclityListPg.waitLoading();
			}
		}
		return fd.facilityID;
	}
	
	public String addFacility(FacilityData fd){
		return addFacility(fd, false, false);
	}
	
	public void gotoCreateNewInstructorPgFromInstructorList(String custNum){
		LicMgrAddInstructorWidget addInstructorWidget = LicMgrAddInstructorWidget.getInstance();
		LicMgrAddInstructorPage addInstructorPg = LicMgrAddInstructorPage.getInstance();
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		
		instructorListPg.clickAddInstructor();
		ajax.waitLoading();
		addInstructorWidget.waitLoading();
		addInstructorWidget.setupAddInstructorInfo(custNum);
		addInstructorWidget.clickOK();
		ajax.waitLoading();
		addInstructorPg.waitLoading();
	}
	
	public String addInstructor(Customer cust, boolean clickCancelInAddInstructorWidget, boolean clickCancelInAddInstructorPg){
		LicMgrAddInstructorWidget addInstructorWidget = LicMgrAddInstructorWidget.getInstance();
		LicMgrAddInstructorPage addInstructorPg = LicMgrAddInstructorPage.getInstance();
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		LicMgrAddInstructorTypeWidget addInstructorTypeWidget = LicMgrAddInstructorTypeWidget.getInstance();
		String errMsg = "";
		
		logger.info("Add Instructor from Instructor list page");
		instructorListPg.clickAddInstructor();
		ajax.waitLoading();
		addInstructorWidget.waitLoading();
		addInstructorWidget.setupAddInstructorInfo(cust.custNum);
		if(clickCancelInAddInstructorWidget){
			addInstructorWidget.clickCancel();
			ajax.waitLoading();
			instructorListPg.waitLoading();
		}else {
			addInstructorWidget.clickOK();
			ajax.waitLoading();
			addInstructorPg.waitLoading();
			//Add instructor type
			if(!addInstructorPg.getInstructorTypes().toString().contains(cust.instructorType)){
				addInstructorPg.clickAddNew();
				ajax.waitLoading();
				addInstructorTypeWidget.waitLoading();
				
				addInstructorTypeWidget.addInstructorType(cust.instructorType);
				addInstructorPg.waitLoading();
			}
			addInstructorPg.setupInstructor(cust);
			if(clickCancelInAddInstructorPg){
				addInstructorPg.clickCancel();
				ajax.waitLoading();
				instructorListPg.waitLoading();
			}else {
				addInstructorPg.clickOK();
				ajax.waitLoading();
				Object page = browser.waitExists(instructorListPg, addInstructorPg);
				if(page == addInstructorPg){
					errMsg = addInstructorPg.getErrorMsg();
				}
			}
			
		}
		return errMsg;
	}
	
	public String addInstructor(Customer cust){
		return addInstructor(cust, false, false);
	}
	
	public void addInstructorTypeDuringEditInstructor(String instructorType){
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
		LicMgrAddInstructorTypeWidget addInstructorTypeWidget = LicMgrAddInstructorTypeWidget.getInstance();

		if(!instructorDetailsPg.getInstructorTypes().toString().contains(instructorType)){
			instructorDetailsPg.clickAddNew();
			ajax.waitLoading();
			addInstructorTypeWidget.waitLoading();
			addInstructorTypeWidget.addInstructorType(instructorType);
			instructorDetailsPg.waitLoading();
		}
	}

	public void editInstructor(Customer cust){
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();

		logger.info("Edit Instructor from Instructor list page when instructor number="+cust.custNum);
		instructorListPg.clickInstructorID(cust.instructorNum);
		ajax.waitLoading();
		instructorDetailsPg.waitLoading();

		addInstructorTypeDuringEditInstructor(cust.instructorType);
		instructorDetailsPg.updateInstructor(cust);
		instructorDetailsPg.clickOK();
		ajax.waitLoading();
		instructorListPg.waitLoading();
	}
	
	public void gotoAddFacilityPrdWidget(){
		LicMgrFacilityProductPage facilityPrdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		logger.info("Go to add facility prd widget from prd list page");
		facilityPrdListPg.clickAddFacilityProductButton();
		ajax.waitLoading();
		addFacilityPrdWidget.waitLoading();
	}
	
	public void gotoAddFacilityPrdTypeWidgetFromAddPrdWidget(){
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		LicMgrAddFacilityProductTypeWidget addPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();
		logger.info("Go to add facility prd type widget from add prd widget");
		addFacilityPrdWidget.clickAddNew();
		ajax.waitLoading();
		addPrdTypeWidget.waitLoading();
	}
	
	public void gotoAddFacilityPrdWidgetFromAddPrdTypeWidget(){
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		LicMgrAddFacilityProductTypeWidget addPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();
		logger.info("Go to add facility prd widget from add prd type widget");
		addPrdTypeWidget.clickCancel();
		ajax.waitLoading();
		addFacilityPrdWidget.waitLoading();
	}
	
	public String addFacilityPrd(Data<FacilityPrdAttr> fpd, boolean clickCancelInAddFacilityPrdWidget, boolean clickCancelInAddFacilityPrdTypeWidget){
		LicMgrFacilityProductPage facilityPrdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		LicMgrAddFacilityProductTypeWidget addFacilityPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();
		List<String> prdTypes = new ArrayList<String>();
		logger.info("Add facility product from facility product list page");
		String errMsg = "";
		gotoAddFacilityPrdWidget();
		
		//Add facility product type
		prdTypes = addFacilityPrdWidget.getFacilityPrdTypes();
		boolean prdTypeExisted = false;
		for(int i=0; i<prdTypes.size(); i++){
			if(prdTypes.get(i).equals(fpd.stringValue(FacilityPrdAttr.prdType))){
				prdTypeExisted = true;
				break;
			}
		}
		if(!prdTypeExisted){
			addFacilityPrdWidget.clickAddNew();
			ajax.waitLoading();
		
			addFacilityPrdTypeWidget.setFacilityPrdType(fpd.stringValue(FacilityPrdAttr.prdType));
				if(clickCancelInAddFacilityPrdTypeWidget){
					addFacilityPrdTypeWidget.clickCancel();
				}else addFacilityPrdTypeWidget.clickOK();
				ajax.waitLoading();
				addFacilityPrdWidget.waitLoading();
		}
		
		//Add facility product
		addFacilityPrdWidget.setupFacilityPrdInfo(fpd);
		if(clickCancelInAddFacilityPrdWidget){
			addFacilityPrdWidget.clickCancel();
			ajax.waitLoading();
			facilityPrdListPg.waitLoading();
		}else{ 
			addFacilityPrdWidget.clickOK();
			ajax.waitLoading();
			Object page = browser.waitExists(facilityPrdListPg, addFacilityPrdWidget);
			if(page==addFacilityPrdWidget){
				errMsg = addFacilityPrdWidget.getErrorMsg();
			}
		}
		return errMsg;
	}
	
	public String addFacilityPrd(Data<FacilityPrdAttr> fpd, boolean clickCancelInAddFacilityPrdWidget){
		String errMsg = addFacilityPrd(fpd, clickCancelInAddFacilityPrdWidget, false);
		return errMsg;
	}
	
	public String addFacilityPrd(Data<FacilityPrdAttr> fpd){
		String errMsg = addFacilityPrd(fpd, false);
		return errMsg;
	}
	
	public String addActivity(Data<ActivityAttr> activity){
		LicMgrAddActivityPage activityAddPg = LicMgrAddActivityPage.getInstance();
		LicMgrActivityListPage activityListDetailPg = LicMgrActivityListPage.getInstance();
		LicMgrAddActivityProductGroupWidget prdGrpWidget = LicMgrAddActivityProductGroupWidget.getInstance();
		activityListDetailPg.clickAddActivityProduct();
		ajax.waitLoading();
		activityAddPg.waitLoading();
		List<String> productGrps = activityAddPg.getActivityGroups();
		if(!productGrps.contains(activity.stringValue(ActivityAttr.prdGroup))){
			activityAddPg.clickAddNew();
			ajax.waitLoading();
			prdGrpWidget.waitLoading();
			prdGrpWidget.setGoupNameAndClickOk(activity.stringValue(ActivityAttr.prdGroup));
			ajax.waitLoading();
			activityAddPg.waitLoading();
		}
		activityAddPg.setupActivityInfo(activity);
		activityAddPg.clickApply();
		ajax.waitLoading();
		activityAddPg.waitLoading();
		String ErrMsg = activityAddPg.getErrMsg();
		String id = "";
		if(StringUtil.isEmpty(ErrMsg)){
			id = activityAddPg.getId();
		}
		return id;
	}
	
	public void goToActivityDetailPg(String activityCode){
		LicMgrActivityDetailsPage activityDetailPg = LicMgrActivityDetailsPage.getInstance();
		LicMgrActivityListPage activityListPg = LicMgrActivityListPage.getInstance();
		
		logger.info("Go to activity detail page for activty with code:" + activityCode);
		activityListPg.clickActivityCode(activityCode);
		ajax.waitLoading();
		activityDetailPg.waitLoading();
	}
	
	public void gotoActivityProductAgentAssignmentPage() {
		LicMgrActivityDetailsPage activityDetailPg = LicMgrActivityDetailsPage.getInstance();
		LicMgrActivityStoreAssignmentPage assignmentPage = LicMgrActivityStoreAssignmentPage.getInstance();
		
		logger.info("Goto Activity product - Agent Assignment tab.");
		activityDetailPg.clickAgentAssignmentsTab();
		ajax.waitLoading();
		assignmentPage.waitLoading();
	}
	
	public void editFacilityPrd(Data<FacilityPrdAttr> facilityPrdSearchData, Data<FacilityPrdAttr> facilityPrdEditData, boolean clickCancelInEditFacilityPrdWidget){
		LicMgrFacilityProductPage facilityPrdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrEditFacilityProductWidget editFacilityPrdWidget = LicMgrEditFacilityProductWidget.getInstance();
		LicMgrAddFacilityProductTypeWidget addFacilityPrdTypeWidget = LicMgrAddFacilityProductTypeWidget.getInstance();
		List<String> prdTypes = new ArrayList<String>();
		logger.info("Edit facility product from facility product list page");
		gotoEditFaclityPrdWdigetFromPrdListPgWithSearch(facilityPrdSearchData);
		
		//Edit facility product type
		prdTypes = editFacilityPrdWidget.getFacilityPrdTypes();
		if(prdTypes.size()<1 || !prdTypes.toString().contains(facilityPrdEditData.stringValue(FacilityPrdAttr.prdType))){
			editFacilityPrdWidget.clickAddNew();
			ajax.waitLoading();
		
			addFacilityPrdTypeWidget.setFacilityPrdType(facilityPrdEditData.stringValue(FacilityPrdAttr.prdType));
				addFacilityPrdTypeWidget.clickOK();
				ajax.waitLoading();
				editFacilityPrdWidget.waitLoading();
		}
		
		//Add facility product
		editFacilityPrdWidget.updateFacilityPrdInfo(facilityPrdEditData);
		if(clickCancelInEditFacilityPrdWidget){
			editFacilityPrdWidget.clickCancel();
		}else 
			editFacilityPrdWidget.clickOK();
		
		ajax.waitLoading();
		facilityPrdListPg.waitLoading();
		}
	
	public void editFacilityPrd(Data<FacilityPrdAttr> facilityPrdSearchData, Data<FacilityPrdAttr> facilityPrdEditData){
		editFacilityPrd(facilityPrdSearchData, facilityPrdEditData, false);
	}
	
	public void gotoFacilityDetailsPgFromFacilityListPg(String facilityID){
		LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
		LicMgrFacilityDetailsPage facilityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
		logger.info("Go to facility details page from facility list page via facility id:"+facilityID);
		if(StringUtil.notEmpty(facilityID)){
			faclityListPg.clickFacilityID(facilityID);
		}faclityListPg.clickFirstFacilityID();
		
		facilityDetailsPg.waitLoading();
	}
	
	public void gotoInstructorDetailsPgFromInstructorListPg(String instructorID){
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
		
		logger.info("Go to instructor details page from instructor list page via instruction id:"+instructorID);
		if(StringUtil.notEmpty(instructorID)){
			instructorListPg.clickInstructorID(instructorID);
		}instructorListPg.clickInstructorID();
		
		instructorDetailsPg.waitLoading();
	}
	
	public void gotoInstructorDetailsPgFromInstructorListPg(Customer cust){
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		instructorListPg.searchInstructor(cust);
		gotoInstructorDetailsPgFromInstructorListPg(StringUtil.EMPTY);
	}
	
	public void gotoInstructorIdenListFromInstructorDetailsPg(){
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
		LicMgrInstructorIdentifiersPage instructorIdenPg = LicMgrInstructorIdentifiersPage.getInstance();

		logger.info("Go to instructor identifier page from instructor details page.");
		instructorDetailsPg.clickIdentifiersTab();
		ajax.waitLoading();
		instructorIdenPg.waitLoading();
	}

	public void gotoInstructorChangeHistoryPgFromInstructorDetailsPg(){
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
		LicMgrInstructorChangeHistoryPage instructorChangeHistoryPg = LicMgrInstructorChangeHistoryPage.getInstance();

		logger.info("Go to instructor identifier page from instructor details page.");
		instructorDetailsPg.clickChangeHistory();
		ajax.waitLoading();
		instructorChangeHistoryPg.waitLoading();
	}
	
	public void gotoInstructorActivityListFromInstructorDetailsPg(){
		LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
		LicMgrInstructorActivityListPage activityList = LicMgrInstructorActivityListPage.getInstance();

		logger.info("Go to instructor activity list page from instructor details page.");
		instructorDetailsPg.clickActivityListTab();
		ajax.waitLoading();
		activityList.waitLoading();
	}
	
	public void gotoInstructorActivityListFromHomePg(Customer cust){
		gotoActivityPgFromHomePg();
		gotoInstructorListPgFromActivityPg();
        gotoInstructorDetailsPgFromInstructorListPg(cust);
        gotoInstructorActivityListFromInstructorDetailsPg();
	}
	
	public void gotoInstructorListFromInstructorActivityList(){
		LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
		LicMgrInstructorActivityListPage activityList = LicMgrInstructorActivityListPage.getInstance();
		activityList.clickInstructorTab();
		instructorListPg.waitLoading();
	}
	
	public void gotoInstructorActivityListFromActivityList(Customer cust){
		gotoInstructorListPgFromActivityPg();
        gotoInstructorDetailsPgFromInstructorListPg(cust);
        gotoInstructorActivityListFromInstructorDetailsPg();
	}
	
	public void gotoInstructorActivityListFromInstructorList(Customer cust){
        gotoInstructorDetailsPgFromInstructorListPg(cust);
        gotoInstructorActivityListFromInstructorDetailsPg();
	}
	
	public void gotoFacilityDetailsPgFromFacilityListPgWithSearch(Data<SearchFacilityAttr> src){
		LicMgrFacilityListPage faclityListPg = LicMgrFacilityListPage.getInstance();
		logger.info("Go to facility details page from facility list page via facility id:"+SearchFacilityAttr.facilityId.getStrValue(src));
		faclityListPg.searchFacility(src);
		gotoFacilityDetailsPgFromFacilityListPg(SearchFacilityAttr.facilityId.getStrValue(src));
	}
	
	public void gotoFacilityAttriPgFromAddressesPg(){
		LicMgrAddressesContactsPage addressesContactsPg = LicMgrAddressesContactsPage.getInstance();
		LicMgrFacilityAttributesPage facilityAttriPg = LicMgrFacilityAttributesPage.getInstance();
		addressesContactsPg.clickFacilityAttriTab();
		ajax.waitLoading();
		facilityAttriPg.waitLoading();
	}
	
	public void gotoFacilityPrdPgFromFacilityDetailsPg(){
		LicMgrFacilityDetailsPage facilityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
		LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
		facilityDetailsPg.clickFacilityProTab();
		ajax.waitLoading();
		facilityPrdPg.waitLoading();
	}
	
	public void gotoFacilityPrdPgFromFacilityListPg(Data<SearchFacilityAttr> src){
		gotoFacilityDetailsPgFromFacilityListPgWithSearch(src);
		gotoFacilityPrdPgFromFacilityDetailsPg();
	}
	
	public void gotoFaclityPrdChangeHistoryPgFromPrdListPg(String prdCode, String prdName){
		LicMgrFacilityProductPage prdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrFacilityPrdChangeHistoryPage prdChangeHistoryPg = LicMgrFacilityPrdChangeHistoryPage.getInstance();
		logger.info("Go to prd(Code:"+prdCode+" and Name:"+prdName+") change history page from prd list page.");
		prdListPg.clickPrdChangeHistoryButton(prdCode, prdName);
		ajax.waitLoading();
		prdChangeHistoryPg.waitLoading();
	}
	
	public void gotoFacilityPrdListPgFromPrdChangeHistoryPg(){
		LicMgrFacilityProductPage prdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrFacilityPrdChangeHistoryPage prdChangeHistoryPg = LicMgrFacilityPrdChangeHistoryPage.getInstance();
		logger.info("Go to faclity product list page from prd change history page.");
		prdChangeHistoryPg.clickReturnToFacilityPrdListButton();
		ajax.waitLoading();
		prdListPg.waitLoading();
	}
	
	public void gotoEditFaclityPrdWdigetFromPrdListPg(String prdCode, String prdName){
		LicMgrFacilityProductPage prdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrEditFacilityProductWidget editFacilityPrdWidget = LicMgrEditFacilityProductWidget.getInstance();
		logger.info("Go to edit prd(Code:"+prdCode+" and Name:"+prdName+") widget from prd list page.");
		prdListPg.clickPrdCode(prdCode, prdName);
		ajax.waitLoading();
		editFacilityPrdWidget.waitLoading();
	}
	
	public void gotoEditFaclityPrdWdigetFromPrdListPgWithSearch(Data<FacilityPrdAttr> fpd){
		LicMgrFacilityProductPage facilityPrdListPg = LicMgrFacilityProductPage.getInstance();
		facilityPrdListPg.searchFacilityPrd(fpd);
		gotoEditFaclityPrdWdigetFromPrdListPg(fpd.stringValue(FacilityPrdAttr.prdCode), fpd.stringValue(FacilityPrdAttr.prdName));
	}
	
	public void gotoFacilityPrdListPgFromEditPrdWdiget(){
		LicMgrFacilityProductPage prdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrEditFacilityProductWidget editFacilityPrdWidget = LicMgrEditFacilityProductWidget.getInstance();
		logger.info("Go to Facility Product list page from edit prd widget.");
		editFacilityPrdWidget.clickCancel();
		ajax.waitLoading();
		prdListPg.waitLoading();
	}
	
	public void gotoFacilityPrdListPgFromAddPrdWdiget(){
		LicMgrFacilityProductPage prdListPg = LicMgrFacilityProductPage.getInstance();
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		logger.info("Go to Facility Product list page from add prd widget.");
		addFacilityPrdWidget.clickCancel();
		ajax.waitLoading();
		prdListPg.waitLoading();
	}
	
	public void gotoAddPropertyPgFromPropertyListPg(){
        LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
        LicMgrAddPropertyPage addCustPropertyPg = LicMgrAddPropertyPage.getInstance();
        logger.info("Go to add property page from property list page");
		custPropertyListPg.clickAddProperty();
		 ajax.waitLoading();
		addCustPropertyPg.waitLoading();
	}
	
	public void gotoPropertyListPgFromAddPropertyPg(){
        LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
        LicMgrAddPropertyPage addCustPropertyPg = LicMgrAddPropertyPage.getInstance();
        logger.info("Go to property page from add property page");
        addCustPropertyPg.clickCancel();
        ajax.waitLoading();
		custPropertyListPg.waitLoading();
	}
	
	public String addCustProperty(Data<PropertyAttr> cpa, boolean clickCancel) {
        LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
        LicMgrAddPropertyPage addCustPropertyPg = LicMgrAddPropertyPage.getInstance();
        LicMgrPropertyDetailsPage custPropertyDetaislPg = LicMgrPropertyDetailsPage.getInstance();
		logger.info("Add customer property");

		if (!custPropertyListPg.exists()) {
			gotoCustPropertyPgFromCustDetailsPg();
		}

		gotoAddPropertyPgFromPropertyListPg();
		
		addCustPropertyPg.setupPropertyDetails(cpa);
		if(clickCancel){
			addCustPropertyPg.clickCancel();
		}else {
			addCustPropertyPg.clickApply();
			ajax.waitLoading();
			custPropertyDetaislPg.waitLoading();
			
			cpa.put(PropertyAttr.propertyID, custPropertyDetaislPg.getPropertyID());
			custPropertyDetaislPg.clickOK();
		}
		
		ajax.waitLoading();
		custPropertyListPg.waitLoading();
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.propertyID))){
			return cpa.stringValue(PropertyAttr.propertyID);
		}else return StringUtil.EMPTY;
	}
	
	public String addCustProperty(Data<PropertyAttr> cpa) {
		return addCustProperty(cpa, false);
	}
	
	public void EditCustProperty(String propertyID, Data<PropertyAttr> cpa) {
		LicMgrPropertyDetailsPage custPropertyDetaislPg = LicMgrPropertyDetailsPage.getInstance();
		LicMgrPropertyListPage propertyListPg = LicMgrPropertyListPage.getInstance();
		logger.info("Edit customer property");

		gotoCustPropertyDetailsPg(propertyID);
		custPropertyDetaislPg.updatePropertyDetails(cpa);
		custPropertyDetaislPg.clickApply();
		ajax.waitLoading();
		cpa.put(PropertyAttr.propertyID, custPropertyDetaislPg.getPropertyID());
		
		custPropertyDetaislPg.clickOK();
		ajax.waitLoading();
		propertyListPg.waitLoading();
	}
	
	public void gotoCustPropertyDetailsPg(String propertyID){
        LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
        LicMgrPropertyDetailsPage custPropertyDetaislPg = LicMgrPropertyDetailsPage.getInstance();
        logger.info("Go to property details page from property list page.");
        custPropertyListPg.clickPropertyID(propertyID);
        custPropertyDetaislPg.waitLoading();
	}
	
	public void gotoCustPropertyPgFromCustDetailsPg(){
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
        LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
        logger.info("Go to property details page from property list page.");
        custDetailsPg.clickPropertyTab();
        ajax.waitLoading();
        custPropertyListPg.waitLoading();
	}
	
	public void gotoPropertyPgFromTopHomePg(Customer cust){
		gotoCustomerDetailFromTopMenu(cust);
		gotoCustPropertyPgFromCustDetailsPg();
	}
	
	public void gotoOwnerDetailsPgFromOwnerListPg(String ownerID){
		LicMgrOwnerDetailsWidget ownerDetailsPg = LicMgrOwnerDetailsWidget.getInstance();
		LicMgrOwnersPage ownerListPg = LicMgrOwnersPage.getInstance();
		if(StringUtil.isEmpty(ownerID)){
			ownerListPg.clickAddOwner();
		}else ownerListPg.clickOwnerID(ownerID);
		
		ajax.waitLoading();
		ownerDetailsPg.waitLoading();	
	}
	
	public void gotoOwnerDetailsPgFromOwnerListPg(){
		gotoOwnerDetailsPgFromOwnerListPg(StringUtil.EMPTY);
	}
	
	public void gotoOwnerListFromOwnerDetailsPg(){
		LicMgrOwnerDetailsWidget ownerDetailsPg = LicMgrOwnerDetailsWidget.getInstance();
		LicMgrOwnersPage ownerListPg = LicMgrOwnersPage.getInstance();
		logger.info("Go to Owner list from owner details page");
		ownerDetailsPg.clickCancel();
		ajax.waitLoading();
		ownerListPg.waitLoading();
	}
	
	public void verifyPropertyHistory(List<ChangeHistory> chs){
		  LicMgrPropertyDetailsPage custPropertyDetaislPg = LicMgrPropertyDetailsPage.getInstance();
		  LicMgrPropertyHistoryPage propertyHistoryPg = LicMgrPropertyHistoryPage.getInstance();
		  custPropertyDetaislPg.clickChangeHistory();
		  ajax.waitLoading();
		  propertyHistoryPg.waitLoading();
		  propertyHistoryPg.verifyPropertyHistoryInfo(chs);
		  propertyHistoryPg.clickReturnToPropertyDetailsButton();
		  ajax.waitLoading();
		  custPropertyDetaislPg.waitLoading();
	}
	
	public void gotoAuditListPgFromPropertyDetailsListPg(){
		LicMgrPropertyDetailsPage propertyDetailsPg = LicMgrPropertyDetailsPage.getInstance();
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		logger.info("Go to audit list page from property details page.");
		propertyDetailsPg.clickAuditsTab();
		ajax.waitLoading();
		auditsPg.waitLoading();
	}
	
	public void gotoAuditDetailsWidgetFromAuditListPg(){
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		LicMgrAuditDetailsWidget auditDetailsWidget = LicMgrAuditDetailsWidget.getInstance();
		logger.info("Go to audit details page from audit list page.");
		auditsPg.clickAddAudit();
		ajax.waitLoading();
		auditDetailsWidget.waitLoading();
	}
	
	public void gotoAuditDetailsWidgetFromAuditListPg(String auditID){
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		LicMgrAuditDetailsWidget auditDetailsWidget = LicMgrAuditDetailsWidget.getInstance();
		logger.info("Go to audit details page from audit list page.");
		auditsPg.clickAuditID(auditID);
		ajax.waitLoading();
		auditDetailsWidget.waitLoading();
	}
	
	public void addAudit(Data<AuditAttr> audit){
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		LicMgrAuditDetailsWidget auditDetailsWidget = LicMgrAuditDetailsWidget.getInstance();
		
		gotoAuditDetailsWidgetFromAuditListPg();
		auditDetailsWidget.setAuditData(audit);
		auditDetailsWidget.clickOK();
		ajax.waitLoading();
		auditsPg.waitLoading();
	}
	
	public void addOwner(Customer cust, Data<OwnerAttr> owner){
		LicMgrOwnersPage ownersPg = LicMgrOwnersPage.getInstance();
		LicMgrOwnerDetailsWidget ownerDetailsWidget = LicMgrOwnerDetailsWidget.getInstance();
		gotoOwnerDetailsPgFromOwnerListPg(StringUtil.EMPTY);
		ownerDetailsWidget.findOwner(cust);
		ownerDetailsWidget.selectOwner(cust);
		ownerDetailsWidget.setOwnerDetails(owner);
		ownerDetailsWidget.clickOK();
		ajax.waitLoading();
		ownersPg.waitLoading();
	}
	
	public void editOwner(Data<OwnerAttr> owner){
		LicMgrOwnersPage ownersPg = LicMgrOwnersPage.getInstance();
		LicMgrOwnerDetailsWidget ownerDetailsWidget = LicMgrOwnerDetailsWidget.getInstance();
		gotoOwnerDetailsPgFromOwnerListPg(owner.stringValue(OwnerAttr.ownerID));
		
		ownerDetailsWidget.setOwnerDetails(owner);
		ownerDetailsWidget.clickOK();
		ajax.waitLoading();
		ownersPg.waitLoading();
	}
	
	public void editAudit(Data<AuditAttr> audit){
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		LicMgrAuditDetailsWidget auditDetailsWidget = LicMgrAuditDetailsWidget.getInstance();
		
		gotoAuditDetailsWidgetFromAuditListPg(audit.stringValue(AuditAttr.auditID));
		auditDetailsWidget.setAuditData(audit);
		auditDetailsWidget.clickOK();
		ajax.waitLoading();
		auditsPg.waitLoading();
	}
	
	public void gotoAuditListPgFromPropertyPg(String propertyID){
		gotoCustPropertyDetailsPg(propertyID);
		gotoAuditListPgFromPropertyDetailsListPg();
	}
	
	public void gotoAuditDetailsWidgetFromPropertyPg(String propertyID){
		gotoAuditListPgFromPropertyPg(propertyID);
		gotoAuditDetailsWidgetFromAuditListPg();
	}
	
	public void gotoAuditListPgFromAuditDetailsWidget(){
		LicMgrAuditsPage auditsPg = LicMgrAuditsPage.getInstance();
		LicMgrAuditDetailsWidget auditDetailsWidget = LicMgrAuditDetailsWidget.getInstance();
		logger.info("Go to audit list page from audit details widget");
		auditDetailsWidget.clickCancel();
		ajax.waitLoading();
		auditsPg.waitLoading();
	}
	
	public String addLicenseYearQuota(QuotaInfo licenseYearQuota){
		String err = "";
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage
				.getInstance();
		LicMgrQuotaLicenseYearWidget addWg = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Add a new quota license year.");
		listPage.clickAddLicenseYearQuota();
		ajax.waitLoading();
		addWg.waitLoading();
		addWg.setUpInfoAndClickOk(licenseYearQuota);
		ajax.waitLoading();
		browser.waitExists(listPage, addWg);
		if(addWg.exists()){
			err = addWg.getErrorMsg();
		}
		return err;
	}
	
	public String editQuotaLicenseYear(QuotaInfo updatedQuotaLY){
		String err = "";
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage
				.getInstance();
		LicMgrQuotaLicenseYearWidget editWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		if(!editWidget.exists()){
			this.gotoQuotaLicenseYearDetailsWidget(updatedQuotaLY);
		}
		editWidget.setUpInfoAndClickOk(updatedQuotaLY);
		ajax.waitLoading();
		browser.waitExists(listPage, editWidget);
		if(editWidget.exists()){
			err = editWidget.getErrorMsg();
		}
		return err;
	}
	
	public void updateQuotaLicenseYearStatus(String year, String status){
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage
				.getInstance();
		LicMgrQuotaLicenseYearWidget editWg = LicMgrQuotaLicenseYearWidget.getInstance();
		listPage.searchByYear(year);
		String id = listPage.getLicenseYearId(year);
		if(StringUtil.isEmpty(id)){
			throw new ErrorOnPageException("Can not found license year:"+year);
		}
		logger.info("Change status of license year:" + year + " to:" + status);
		listPage.clickIdLink(id);
		ajax.waitLoading();
		editWg.waitLoading();
		editWg.selectlicenseYearStatus(status);
		editWg.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	public void gotoQuotaLicenseYearDetailsWidget(QuotaInfo quotaLiceYear){
		LicMgrQuotaLicenseYearsListPage listPage = LicMgrQuotaLicenseYearsListPage
				.getInstance();
		LicMgrQuotaLicenseYearWidget detailWidget = LicMgrQuotaLicenseYearWidget.getInstance();
		logger.info("Go to Quota License Year(LicenseYear=" + quotaLiceYear.getLicenseYear()
				+ ") details widget.");
		listPage.searchLicenseYear(quotaLiceYear.getLicenseYearQuotaStatus(), quotaLiceYear.getLicenseYear());
		listPage.clickIdLink(listPage.getLicenseYearId(quotaLiceYear.getLicenseYear()));
		ajax.waitLoading();
		detailWidget.waitLoading();
	}
	
	public void gotoQuotaLicenseYearListPageFromDetailsWidget() {
		logger.info("Go to Quota License Year list page from details page.");
		LicMgrQuotaLicenseYearWidget.getInstance().clickCancel();
		ajax.waitLoading();
		LicMgrQuotaLicenseYearsListPage.getInstance().waitLoading();
	}
	
	public void gotoQuotaHuntAssignPgFromQuotaDetailPg(){
		logger.info("Go to quota hunt assign page from detail page");
		LicMgrQuotaDetailsPage detailPg = LicMgrQuotaDetailsPage.getInstance();
		LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();
		detailPg.clickQuotaHuntsTab();
		ajax.waitLoading();
		huntAssignPg.waitLoading();
	}
	
	public void gotoQuotaTransferListPage(){
		LicMgrQuotaDetailsPage detailPg = LicMgrQuotaDetailsPage.getInstance();
		LicMgrQuotaTransferListPage listPg = LicMgrQuotaTransferListPage.getInstance();
		
		logger.info("Goto Quota Transfer List Page.");
		
		detailPg.clickQuotaTransfer();
		ajax.waitLoading();
		listPg.waitLoading();
	}
	
	public void cleanupAllActiveQuotaTransfers(){
		LicMgrQuotaTransferListPage listPg = LicMgrQuotaTransferListPage.getInstance();
		
		logger.info("Inactive All Active Quota Transfers.");
		
		listPg.filterByStatus(ACTIVE_STATUS);
		List<String> ids = listPg.getAllQuotaTransferIds();
		for(String id:ids){
			inactiveQuotaTransfer(id);
		}
		
	}
	
	public void gotoQuotaTransferDetailPage(String id){
		LicMgrQuotaTransferListPage listPg = LicMgrQuotaTransferListPage.getInstance();
		LicMgrQuotaTransferWidget detailPg = LicMgrQuotaTransferWidget.getInstance();
		
		logger.info("Goto Quota Transfer#"+id+" details page.");
		
		listPg.clickIdLink(id);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	public void inactiveQuotaTransfer(String id){
		LicMgrQuotaTransferWidget detailPg = LicMgrQuotaTransferWidget.getInstance();
		LicMgrQuotaTransferListPage listPg = LicMgrQuotaTransferListPage.getInstance();
		
		gotoQuotaTransferDetailPage(id);
		detailPg.selectStatus(INACTIVE_STATUS);
		detailPg.clickOKAndWait();
		listPg.waitLoading();
		logger.info("Inactive Quota Transfer#"+id);
	}
	
	public String addQuotaTransfer(QuotaTransfer qt){
		LicMgrQuotaTransferListPage listPg = LicMgrQuotaTransferListPage.getInstance();
		LicMgrQuotaTransferWidget detailPg = LicMgrQuotaTransferWidget.getInstance();
		
		logger.info("Add Quota Transfer.");
		
		listPg.clickAddQuotaTransfer();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setupQuotaTransfer(qt);
		listPg.waitLoading();
		return listPg.getQuotaTransferID(qt.getDescription());
	}
	
	
	/**
	 * Filter Quota hunt assignment info at date period hunt assignment
	 * list page
	 * 
	 * @param status
	 */
	public void filterQuotaHuntAssignmentInfoAtQuotaHuntAssignmentListPg(
			String status) {
		LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();

		logger.info("Filter Date Period Hunt Assignment info as status:" + status);
		huntAssignPg.selectFilterStatus(status);
		huntAssignPg.clickGo();
		ajax.waitLoading();
		huntAssignPg.waitLoading();
	}
	
	/**
	 * Assign hunt to quota Start from quota hunt
	 * assignment list page End at quota hunt assignment list page
	 * @param huntInfo
	 */
	public void assignHuntToQuotaAtQuotaHuntAssignmentListPg(String huntInfo) {
		LicMgrQuotaHuntsAssignmentListPage huntAssignPg = LicMgrQuotaHuntsAssignmentListPage.getInstance();
		logger.info("Assign hunt to quota. Hunt info = "
				+ huntInfo);
		huntAssignPg.selectHuntInfoCheckBox(huntInfo);
		huntAssignPg.clickAssign();
		ajax.waitLoading();
		huntAssignPg.waitLoading();
	}
	
	public void gotoQuotaHuntAssignmentPgFromQuotaListPg(String quotaId){
		this.gotoQuotaDetailsPgFromQuotaListPg(quotaId);
		this.gotoQuotaHuntAssignPgFromQuotaDetailPg();
	}
	
	public void gotoAddItemPgFromPrivilegeQuickSale(Customer customer) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		logger.info("Goto add privilege item page from privilege quick sale.");
		homePg.identifyCustomer(customer.customerClass, customer.dateOfBirth, 
				customer.identifier.identifierType,
				customer.identifier.identifierNum,
				customer.identifier.country, 
				customer.identifier.state);
		homePg.clickPrivilegeQuickSale();
		ajax.waitLoading();
		confirmCustPg.waitLoading();
		
		confirmCustPg.clickOK();
		ajax.waitLoading();
		
		Object pages = browser.waitExists(residencyWidget, addItemPg);
		if (pages == residencyWidget) {
			 if (customer.residencyStatus.equalsIgnoreCase("Resident")) {
				residencyWidget.selectResident();
				ajax.waitLoading();
				if (residencyWidget.isAdditionalProofDropdownListExists()) {
					residencyWidget.selectAdditionalProof(customer.additionalProofOfResidency);
				}
			} else if(StringUtil.isEmpty(customer.residencyStatus) || customer.residencyStatus.equalsIgnoreCase("Non Resident")){
				residencyWidget.selectNonResident();
				ajax.waitLoading();
			} else{
				residencyWidget.selectResident(customer.residencyStatus);
				ajax.waitLoading();
			}
			residencyWidget.clickOK();
			ajax.waitLoading();
			addItemPg.waitLoading();
		}
	}

	public List<String> makeInventoryPrivilegeToCart(PrivilegeInfo... privileges) {
		this.purchaseInventoryPrivilegeFromHomePage();
		List<String> inventoryNums = this.addPrivilegeItem(privileges);
		this.goToCart();
		
		return inventoryNums;
	}
	
	public void purchaseInventoryPrivilegeFromHomePage() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		logger.info("Purchase inventory privilege from home page.");
		homePg.clickPurchaseInventory();
		addItemPg.waitLoading();
	}
	
	public void updateCustomerStatus(Customer customer, String fromStatus, String toStatus) {
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();

		logger.info("Update customer status from "+fromStatus+" to "+toStatus);
		topMenuPg.clickCustomers();
		custSearchPg.waitLoading();
		custSearchPg.selectStatus(fromStatus);
		custSearchPg.searchCustomerByIdnetifier(customer.identifier.identifierType, customer.identifier.identifierNum, customer.customerClass);
		Object page = browser.waitExists(custSearchPg, custDetailPg);
		if (page == custSearchPg) {
			int count = custSearchPg.getCusttableRowNum();
			if(count-1<1)
				throw new ItemNotFoundException("---No customer found!!!");
			logger.info("Choose the first customer for your search result.");
			custSearchPg.clickCustomerFirstNumer();
			ajax.waitLoading();
		}
		custDetailPg.waitLoading();
		custDetailPg.editCustomerStatus(toStatus);
	}
	
	public void gotoCustomerPurchaseAuthorizationPage() {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerPurchaseAuthorizationPage purchaseAuthPg = LicMgrCustomerPurchaseAuthorizationPage.getInstance();
		logger.info("Go to customer purchase authorization sub table from customer detail page.");
		detailsPg.clickPurchaseAuthorizationTab();
		ajax.waitLoading();
		purchaseAuthPg.waitLoading();
	}
	
	public String addPrivilegePurchaseAuthorization(Data<PrivilegePurchaseAuthorization> purchaseAuth) {
		LicMgrCustomerPurchaseAuthorizationPage purchaseAuthPg = LicMgrCustomerPurchaseAuthorizationPage.getInstance();
		LicMgrPrivilegePurchaseAuthorizationWidget purchaseAuthWidget = LicMgrPrivilegePurchaseAuthorizationWidget.getInstance();
		
		logger.info("Add privilege purchase authorization...");
		purchaseAuthPg.clickAddPurchaseAuthorization();
		ajax.waitLoading();
		purchaseAuthWidget.waitLoading();
		purchaseAuthWidget.setPurchaseAuthorizationInfo(purchaseAuth);
		purchaseAuthWidget.clickOKAndWait();
		browser.waitExists(purchaseAuthWidget, purchaseAuthPg);
		
		String errorMsg = StringUtil.EMPTY;
		if(purchaseAuthWidget.exists()){
			errorMsg = purchaseAuthWidget.getErrorMsg();
		}
		return errorMsg;
	}
	
	public String printCustomerRecords(String licenseYearCount,String path,String fileName,String format){
		LicMgrCustomerDetailsPage detailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerLicenseYearWidget lyWidget = LicMgrCustomerLicenseYearWidget.getInstance();
		OrmsOnlineReportProcessingPage onlineRptPg = OrmsOnlineReportProcessingPage
				.getInstance();
		
		logger.info("Print Customer Records");
		
		detailPg.clickPrintCustomerRecord();
		ajax.waitLoading();
		lyWidget.waitLoading();
		lyWidget.selectLicenseYearCount(licenseYearCount);
		lyWidget.clickOK();
		onlineRptPg.waitLoading();
		return downloadFile(path,fileName,format);
	}
	/**
	 * Goto Application order fees detail page from Application order detail page
	 * 
	 */
	public void gotoApplicationOrderFeesDetailPage() {
		LicMgrApplicationOrderDetailsPage appOrderDetailsPage = LicMgrApplicationOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage appFeesPage = LicMgrOrderFeesDetailsPage
				.getInstance();
		logger.info("Goto Application order fees detail page from Application order detail page");
		appOrderDetailsPage.clickFees();
		ajax.waitLoading();
		appFeesPage.waitLoading();
	}
	
	public void voidActivityRegistrationOrderToCart(String ordNum, String voidReason, String voidNote) {
		logger.info("Void activity registration order "+ordNum);
		gotoActivityRegistrationOrderDetailPageFromTopMenu(ordNum);
		voidActivityRegistrationOrderToCart(voidReason, voidNote);
	}
	
	public void voidActivityRegistrationOrderToCart(String voidReason, String voidNote) {
		LicMgrActivityRegistrationOrderDetailPage activityRegDetailsPg = LicMgrActivityRegistrationOrderDetailPage.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		activityRegDetailsPg.clickVoid();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(voidReason, voidNote);
		orderCartPg.waitLoading();
	}
	
	public void gotoCartFromTopMenu() {
		LicMgrTopMenuPage topMenu = LicMgrTopMenuPage.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Go to cart from top menu.");
		topMenu.clickCart();
		cartPg.waitLoading();
	}
	
	public void voidActivityOrderItemByName(String name, String voidReason, String voidNote) {
		LicMgrActivityRegistrationOrderDetailPage orderItemsPg = LicMgrActivityRegistrationOrderDetailPage.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Void activity order item by name "+name+" in order");
		orderItemsPg.clickVoidByActivityName(name);
		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(voidReason, voidNote);
		cartPg.waitLoading();
	}
	
	public void makeActivityToCartFromOrderCartPage(Customer cust, Data<ActivityAttr> activity){
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrActivityRegistrationSalePage activitySalePg = LicMgrActivityRegistrationSalePage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
		
		logger.info("Make Activity order to cart from order cart page.");
		orderCartPg.clickActivityRegistration();
		ajax.waitLoading();
		activitySalePg.waitLoading();
		
		activitySalePg.searchAvailableActivityByName(activity.stringValue(ActivityAttr.activityName)); 
		activitySalePg.clickAddToCart(activity.stringValue(ActivityAttr.activityID) + " - " + activity.stringValue(ActivityAttr.activityName));
		custSearchPg.waitLoading();
		
		this.searchCustomer(cust);
		custSearchPg.selectFirstCustomer();
		ajax.waitLoading();
		orderCartPg.waitLoading();
	}
	//added by Summer
	public void gotoPrivHistoryPageFromOrderItemPage(String privNum){
		LicMgrPrivilegeOrderItemsPage ordItemPg = LicMgrPrivilegeOrderItemsPage.getInstance();
		LicMgrPrivilegeHistoryPage privHistoryPg = LicMgrPrivilegeHistoryPage.getInstance();
		logger.info("Goto privilege history page from order item page");
		ordItemPg.clickLicenseNum(privNum);
		privHistoryPg.waitLoading();
	}
	//added by tchen
	public String getTranlationFromDB(String label, String schema){
		
		String sql = "select LABEL_VALUE from X_TRANSLATION where LABEL_KEY like" + "'%"+label+"'";
		
		logger.info("get translation from db for "+label);
		db.resetSchema(schema);
		return db.executeQuery(sql, "LABEL_VALUE").get(0);		

	}
}
