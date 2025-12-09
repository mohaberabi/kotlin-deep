package variancemodifiers

interface Message
interface OrderManagerMessage : Message
interface Sender<in T : Message> {
    fun send(message: T)
}

class AddOrder(val order: String) : OrderManagerMessage
class CancelOrder(val order: String) : OrderManagerMessage
interface InvoiceManagerMessage : Message
class MakeInvoice(val order: String) : OrderManagerMessage

class GeneralSender(
    url: String,
) : Sender<Message> {
    override fun send(message: Message) {}
}

val orderManagerSender: Sender<OrderManagerMessage> = GeneralSender("google.com")