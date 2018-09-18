/* Generated File */
package services.commerce

@javax.inject.Singleton
class CommerceServiceRegistry @javax.inject.Inject() (
    val customerService: services.commerce.CustomerService,
    val employeeService: services.commerce.EmployeeService,
    val invoiceLineService: services.commerce.InvoiceLineService,
    val invoiceService: services.commerce.InvoiceService
)
