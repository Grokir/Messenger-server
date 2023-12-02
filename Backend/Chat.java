public class Chat {
    private String m_id_chat;
    private String m_title_chat;

    public Chat() {
        m_id_chat = "";
        m_title_chat = "";
    }

    public Chat(String chat_id, String title_chat){
        m_id_chat = chat_id;
        m_title_chat = title_chat;
    }

    public String getTitle(){
        return m_title_chat;
    }
    public void setTitle(String title_chat) {
        m_title_chat = title_chat;
    }

    public String getID() {
        return m_id_chat;
    }
}
