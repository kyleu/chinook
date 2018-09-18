package services.audit

import play.api.mvc.Call
import services.audit.AuditArgs._
import util.Logging

object AuditRoutes extends Logging {
  def getViewRoute(key: String, id: IndexedSeq[String]) = routeFor(key, getArg(id, _))

  private[this] def routeFor(key: String, arg: Int => String): Call = key.toLowerCase match {
    /* Start audit calls */
    case "album" => controllers.admin.media.routes.AlbumController.view(longArg(arg(0)))
    case "artist" => controllers.admin.media.routes.ArtistController.view(longArg(arg(0)))
    case "audit" => controllers.admin.audit.routes.AuditController.view(uuidArg(arg(0)))
    case "auditrecord" => controllers.admin.audit.routes.AuditRecordController.view(uuidArg(arg(0)))
    case "customer" => controllers.admin.commerce.routes.CustomerController.view(longArg(arg(0)))
    case "employee" => controllers.admin.commerce.routes.EmployeeController.view(longArg(arg(0)))
    case "flywayschemahistory" => controllers.admin.ddl.routes.FlywaySchemaHistoryController.view(longArg(arg(0)))
    case "genre" => controllers.admin.media.routes.GenreController.view(longArg(arg(0)))
    case "invoice" => controllers.admin.commerce.routes.InvoiceController.view(longArg(arg(0)))
    case "invoiceline" => controllers.admin.commerce.routes.InvoiceLineController.view(longArg(arg(0)))
    case "mediatype" => controllers.admin.media.routes.MediaTypeController.view(longArg(arg(0)))
    case "note" => controllers.admin.note.routes.NoteController.view(uuidArg(arg(0)))
    case "playlist" => controllers.admin.media.routes.PlaylistController.view(longArg(arg(0)))
    case "playlisttrack" => controllers.admin.media.routes.PlaylistTrackController.view(longArg(arg(0)), longArg(arg(1)))
    case "scheduledtaskrun" => controllers.admin.task.routes.ScheduledTaskRunController.view(uuidArg(arg(0)))
    case "syncprogress" => controllers.admin.sync.routes.SyncProgressController.view(stringArg(arg(0)))
    case "systemuser" => controllers.admin.user.routes.SystemUserController.view(uuidArg(arg(0)))
    case "track" => controllers.admin.media.routes.TrackController.view(longArg(arg(0)))
    /* End audit calls */

    case _ =>
      log.warn(s"Invalid model key [$key].")
      controllers.admin.system.routes.AdminController.explore()
  }
}
