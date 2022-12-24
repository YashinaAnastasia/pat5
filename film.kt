package facade

interface FilmMaker {
    fun makeFilm()
}

class MyMaker : FilmMaker {
    private val scriptWriter = Scriptwriter()
    private val actor = Actor()
    private val filmDirector = FilmDirector()
    private val videoEditor = VideoEditor()
    private val producer = Producer()
    override fun makeFilm() {
        val script = scriptWriter.writeScript()
        val scenes = script.map { actor.actWithScript(it) }
        val videoParts = scenes.map { filmDirector.filmVideoPart(it) }
        val videoPartsEdited = videoParts.map { videoEditor.editViewPart(it) }
        producer.publishFilm(videoPartsEdited)
    }
}

data class ScriptPart(val role: String) { override fun toString(): String = role }
data class Scene(val scene: String) { override fun toString(): String = scene }
data class VideoPart(val videoPart: String) { override fun toString(): String = videoPart }
data class VideoPartEdited(val videoPartEdited: String)  { override fun toString(): String = videoPartEdited }

class Scriptwriter() {
    fun writeScript() = listOf(ScriptPart("First role"), ScriptPart("Second role"))
}

class Actor {
    fun actWithScript(script: ScriptPart) = Scene("Actor acting $script")
}

class FilmDirector {
    fun filmVideoPart(scene: Scene) = VideoPart("Director filming $scene")
}

class VideoEditor {
    fun editViewPart(videoPart: VideoPart) = VideoPartEdited("$videoPart edited")
}

class Producer {
    fun publishFilm(partsEdited: List<VideoPartEdited>) {
        partsEdited.forEach {
            println(it)
        }
    }
}

//Director filming Actor acting First role edited
//Director filming Actor acting Second role edited
fun main() {
    val maker: FilmMaker = MyMaker()
    maker.makeFilm()
}