package q3.commands

import q3.Q3ServerClient
import q3.VelocityUtil

object Q3ServerStatusCommand : Command {

    override fun execute(args: String): String {
        val serverStatus = Q3ServerClient().getServerStatus(args)
        return VelocityUtil.renderServerStatus(serverStatus)
    }

}