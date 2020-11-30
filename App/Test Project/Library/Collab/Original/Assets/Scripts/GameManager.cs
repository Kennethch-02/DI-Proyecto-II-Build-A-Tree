using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System.Threading;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    public GameObject menuPrincipal;
    public GameObject menuGameOver;
    public GameObject plataforma1;
    public GameObject plataforma2;
    public GameObject plataforma3;
    public GameObject plataforma4;
    public GameObject plataforma5;
    public GameObject plataforma6;
    public GameObject plataforma7;
    public GameObject plataforma8;
    public GameObject rombo;
    public GameObject triangulo;
    public GameObject cuadrado;
    public GameObject circulo;
    public GameObject reyInicio;
    public GameObject frogInicio;
    public GameObject pinkInicio;
    public GameObject blueInicio;
    public GameObject PInicio1;
    public GameObject PInicio2;
    public GameObject PInicio3;
    public GameObject PInicio4;
    public Text Rey;
    public Text Blue;
    public Text Pink;
    public Text Frog;
    public Text PuntajeRey;
    public Text PuntajeBlue;
    public Text PuntajePink;
    public Text PuntajeFrog;
    public int IntpuntajeRey;
    public int IntpuntajeBlue;
    public int IntpuntajePink;
    public int IntpuntajeFrog;
    public Renderer fondo;
    public bool gameOver = false;
    public bool start = false;
    public List<GameObject> plataformas;
    public List<GameObject> personajesinicio;
    public JBlue jblue;     Client client = new Client();
    // Start is called before the first frame update
    void Start()
    {
        //Inicia el cliente
        client.Connect();
        Thread t_client = new Thread(new ThreadStart(client.ReceiveMessage));
        t_client.Start();
        
        // Crear Plataformas
        plataformas.Add(Instantiate(plataforma1, new Vector2((float)-13,(float)4.5),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma2, new Vector2((float)-5.5,(float)4.5),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma3, new Vector2((float)-9.5,(float)1),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma4,new Vector2((float)-1.5,(float)1),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma5,new Vector2((float)-13.5,(float)-2.5),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma6,new Vector2((float)-6,(float)-2.5),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma7,new Vector2((float)-12.5,(float)-6),Quaternion.identity));
        plataformas.Add(Instantiate(plataforma8,new Vector2((float)-1,(float)-6),Quaternion.identity));
        
        PInicio1 = Instantiate(reyInicio, new Vector2(-11, 6),Quaternion.identity);
        PInicio2 = Instantiate(blueInicio, new Vector2(11, 6), Quaternion.identity);
        PInicio3 = Instantiate(pinkInicio, new Vector2(-11, -6), Quaternion.identity);
        PInicio4 = Instantiate(frogInicio, new Vector2(11, -6), Quaternion.identity);
    }

    // Update is called once per frame
    void Update()
    {
        string message;
        if (client.GetMessage() != null)
        {
            message = client.GetMessage();
            client.setMessage(null);
        }
        if (start == false)
        {
            if (Input.GetKeyDown(KeyCode.X))
            {
                start = true;
                Destroy(PInicio1.gameObject);
                Destroy(PInicio2.gameObject);
                Destroy(PInicio3.gameObject);
                Destroy(PInicio4.gameObject);
            }
        }
        if (start == true && gameOver == true)
        {
            menuGameOver.SetActive(true);
            
            if (Input.GetKeyDown(KeyCode.X))
            {
                SceneManager.LoadScene(SceneManager.GetActiveScene().name);
            }
        }
        if (start == true && gameOver == false)
        {
            menuPrincipal.SetActive(false);
            fondo.material.mainTextureOffset = fondo.material.mainTextureOffset + new Vector2(0.02f, 0) * Time.deltaTime;
        }
        IntpuntajeBlue = jblue.puntaje;
        PuntajeBlue.text = IntpuntajeBlue.ToString();
    }
    private void OnCollisionEnter2D(Collision2D other)
    {
        throw new NotImplementedException();
    }
}